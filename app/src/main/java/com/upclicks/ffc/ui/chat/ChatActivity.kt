package com.upclicks.ffc.ui.chat

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Ack
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Manager
import com.github.nkzawa.socketio.client.Socket
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.messages.MessageHolders
import com.stfalcon.chatkit.messages.MessageInput.*
import com.stfalcon.chatkit.messages.MessagesListAdapter
import com.stfalcon.chatkit.messages.MessagesListAdapter.OnMessageLongClickListener
import com.stfalcon.chatkit.messages.MessagesListAdapter.SelectionListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import java.lang.reflect.Type
import android.location.*
import androidx.activity.viewModels
import com.upclicks.ffc.ui.chat.data.model.Chat
import com.upclicks.ffc.ui.chat.data.model.MediaFile
import com.upclicks.ffc.ui.chat.data.model.Message
import com.upclicks.ffc.ui.chat.data.model.UploadFilesMessage
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.commons.Keys.IS_MY_OWN
import com.upclicks.ffc.commons.Keys.Intent_Constants.CONV_ID
import com.upclicks.ffc.helpers.imageBinding
import com.upclicks.ffc.databinding.ActivityChatBinding
import com.upclicks.ffc.socket.api.SocketController
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.ui.chat.data.viewmodel.ChatViewModel
import com.upclicks.ffc.ui.chat.holders.CustomMessageViewHolder
import com.upclicks.ffc.ui.chat.holders.CustomMessageViewHolder.*
import org.json.JSONArray


class ChatActivity : BaseActivity(), OnMessageLongClickListener<Message>,
    InputListener,
    AttachmentsListener,
    SelectionListener,
    MessagesListAdapter.OnLoadMoreListener,
    LongPress,
    TypingListener {
    lateinit var binding: ActivityChatBinding
    private var messagesListAdapter: MessagesListAdapter<Message>? = null
    private var listOfMessages: ArrayList<Message> = ArrayList()
    private var mSocket: Socket? = null
    private val chatViewModel: ChatViewModel by viewModels()
    var receiverId: Int = 0
    var advertisementId = ""
    var convId = ""
    lateinit var textInputMessage: String
    private var messagesTake: Int = 5
    private var lastMessageTime: String = ""
    private var stopLoadingMoreMessages: Boolean = false
    private var isDataChange: Boolean = false
    private var currentShareLocation: Location? = null
    private var files: ArrayList<MultipartBody.Part> = ArrayList()

    // chatKit
    var imageLoader: ImageLoader? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromIntent()
        initSocket()
        setUpUi()
        observerMessagesData()
    }


    private fun getDataFromIntent() {
        convId = intent.getStringExtra(CONV_ID).toString()
    }

    override fun getLayoutResourceId(): View {
        binding = ActivityChatBinding.inflate(layoutInflater)
        return binding.root
    }


    private fun setUpUi() {
        setUpToolbar()
        initAdapter()
        binding.input.setInputListener(this)
        binding.input.setAttachmentsListener(this)
    }


    private fun setUpToolbar() {
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.toolbar.titleTv.text = "System"
    }


    private fun initAdapter() {
        imageLoader = ImageLoader { imageView: ImageView?, url: String?, payload: Any? ->
            Glide.with(this)
                .load(url)
                .placeholder(getDrawable(R.drawable.ic_user))
                .into(imageView!!)
        }
        initLongPressAction(this)
        val holdersConfig = MessageHolders()
            .registerContentType(
                1.toByte(),
                CustomMessageViewHolder::class.java,
                R.layout.item_custom_incoming_message,
                CustomMessageViewHolder::class.java,
                R.layout.item_custom_outcoming_message,
                object : MessageHolders.ContentChecker<Message> {
                    override fun hasContentFor(message: Message?, type: Byte): Boolean {
                        if (1.toByte() == type)
                            return true
                        return false
                    }
                }
            )

        messagesListAdapter = MessagesListAdapter(IS_MY_OWN, holdersConfig, imageLoader);
        messagesListAdapter!!.addToEnd(listOfMessages, false)
        messagesListAdapter!!.setOnMessageLongClickListener(this)
        messagesListAdapter!!.setLoadMoreListener(this)
        binding.input.setTypingListener(this)
        binding.messagesList.setAdapter<Message>(messagesListAdapter!!)
    }

    //observe chat messages
    private fun observerMessagesData() {
        binding.viewModel = chatViewModel
        binding.lifecycleOwner = this
        emitJoinChat()
        //Get messages
        chatViewModel.getChatMessages(
            sessionHelper.userProfile.id!!,
            lastMessageTime!!,
            messagesTake!!
        )
        chatViewModel.observeMessages?.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                messagesListAdapter?.clear()
                listOfMessages.addAll(it)
                listOfMessages = updateMessageList(listOfMessages)
                messagesListAdapter!!.addToEnd(listOfMessages, false)
                lastMessageTime = it[it.size - 1].creationTime!!
                stopLoadingMoreMessages = false
                var ids: ArrayList<String> = ArrayList()
                for (message in listOfMessages) {
                    if (message.senderUserId != sessionHelper.userProfile.id && !message.isSeen!!) {
                        if (message.id!! != null)
                            ids.add(message.messageId!!)
                    }
                }
                if (ids.isNotEmpty())
                    emitSeenMessage(ids)
            } else
                stopLoadingMoreMessages = true
            messagesListAdapter?.notifyDataSetChanged()
        })
    }


    private fun updateMessageList(it: List<Message>): ArrayList<Message> {
        var messages = ArrayList<Message>()
        for (message in listOfMessages) {
            messages.add(message)
        }
        return messages
    }

    //join to chat
    private fun emitJoinChat() {
        SocketController.request<String>(
            mSocket!!,
            Keys.Chat_Socket.JOIN_CHAT,
            JSONObject(),
            onResult = {
                val gson = Gson()
                Log.e("emit JoinChat response", gson.toJson(it[0]))
            })
    }

    //join to chat
    private fun emitRemoveMessageFromChat(message: Message) {
        val jsonOb = JSONObject()
        jsonOb.put("message_id", message.messageId)
        SocketController.request<SocketMessageRemovedResponse>(
            mSocket!!,
            Keys.Chat_Socket.REMOVE_MESSAGE,
            jsonOb,
            onResult = { socketMessageRemovedResponse ->
                Observable.just(true)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (socketMessageRemovedResponse != null) {
                            val message = Message(
                                socketMessageRemovedResponse.nameValuePairs.message_id,
                                sessionHelper.userProfile.id
                            )
                            listOfMessages.remove(message)
                            messagesListAdapter?.delete(message)
                            messagesListAdapter?.notifyDataSetChanged()
                        }
                    }
            })
    }

    //Message Removed From Chat
    private fun messageRemovedFromChat() {
        SocketController.receive<SocketMessageRemovedResponse>(
            mSocket!!,
            Keys.Chat_Socket.MESSAGE_REMOVED,
            onResult = { socketMessageRemovedResponse ->
                Observable.just(true)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (socketMessageRemovedResponse != null) {
                            if (socketMessageRemovedResponse.nameValuePairs.success!!) {
                                val message = Message(
                                    socketMessageRemovedResponse.nameValuePairs.message_id,
                                    sessionHelper.userProfile.id
                                )
                                listOfMessages.remove(message)
                                messagesListAdapter?.delete(message)
                                messagesListAdapter?.notifyDataSetChanged()
                            }
                        }
                    }

            })
    }

    //Send message to user
    private fun emitSendMessage() {
        val jsonOb = JSONObject()
        jsonOb.put("message", textInputMessage)
        SocketController.request<SocketMessageResponse>(
            mSocket!!,
            Keys.Chat_Socket.SEND_MESSAGE,
            jsonOb,
            onResult = { socketMessageResponse ->
                if (socketMessageResponse != null)
                    emitJoinChat()
                Observable.just(true)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val gson = Gson()
                        val itemType: Type =
                            object : TypeToken<ArrayList<MediaFile?>?>() {}.getType()
                        val mediaFiles: List<MediaFile> = ArrayList<MediaFile>()

                        messagesListAdapter!!.addToStart(
                            Message(
                                socketMessageResponse.nameValuePairs.messageId,
                                sessionHelper.userProfile.id,
                                socketMessageResponse.nameValuePairs.messageType,
                                socketMessageResponse.nameValuePairs.content,
                                socketMessageResponse.nameValuePairs.creationTime,
                                true,
                                socketMessageResponse.nameValuePairs.senderAvatar,
                                mediaFiles,
                                gson.fromJson(
                                    socketMessageResponse.nameValuePairs.additionalData.toString(),
                                    Any::class.java
                                ),
                            ), true
                        )
                    }
            })

    }

    //Listen message from another user
    private fun emitReceiveMessage() {
        SocketController.receive<SocketMessageResponse>(
            mSocket!!,
            Keys.Chat_Socket.RECEIVE_MESSAGE,
            onResult = { socketMessageResponse ->
                Observable.just(true)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        var ids: ArrayList<String> = ArrayList()
                        ids.add(socketMessageResponse.nameValuePairs.messageId!!)
                        Message(
                            socketMessageResponse.nameValuePairs.messageId,
                            socketMessageResponse.nameValuePairs.senderUserId.toString(),
                            socketMessageResponse.nameValuePairs.messageType,
                            socketMessageResponse.nameValuePairs.content,
                            socketMessageResponse.nameValuePairs.creationTime,
                            false,
                            socketMessageResponse.nameValuePairs.senderAvatar,
                            ArrayList(),
                            Gson().fromJson(
                                socketMessageResponse.nameValuePairs.additionalData.toString(),
                                Any::class.java
                            ),
                        )
                    }

            })
    }


    //Send message to user
    private fun emitSeenMessage(ids: ArrayList<String>) {
        val jsonOb = JSONObject()
        jsonOb.put("messagesIds", JSONArray(ids))
        SocketController.request<String>(
            mSocket!!,
            Keys.Chat_Socket.SEEN_MESSAGE,
            jsonOb,
            onResult = {
                val gson = Gson()
                Log.e("emit SEEN response", gson.toJson(it[0]))
            })
    }


    //leave chat
    private fun emitLeaveChat() {
        SocketController.request<String>(
            mSocket!!,
            Keys.Chat_Socket.LEAVE_CHAT,
            JSONObject(),
            onResult = {
                Log.e("Leave success", "success")
            })
    }


    override fun onDestroy() {
        super.onDestroy()
        emitLeaveChat()
        mSocket?.off()
        mSocket?.disconnect()
    }

    private fun initSocket() {
        if (mSocket == null) {
            val opts: IO.Options = IO.Options()
            opts.timeout = 864000 * 1000
            opts.query = "token=" + sessionHelper.userToken()
            mSocket = IO.socket(Keys.Chat_Socket.SOCKET_URL, opts)
            mSocket?.io()?.on(Manager.EVENT_TRANSPORT, initSocketHeaders());
            mSocket?.on(Socket.EVENT_CONNECT, Emitter.Listener {
                emitJoinChat()
                emitReceiveMessage()
                messageRemovedFromChat()
            })
            mSocket?.connect()
        }
    }

    /**
     * Fires when message is long clicked.
     *
     * @param message clicked message.
     */
    override fun onMessageLongClick(message: Message?) {
        // delete message from socket
        if (message?.senderUserId?.equals(sessionHelper.userProfile.id)!!)
            RemoveChatMessageDialog(this, message) { view, message ->
                emitRemoveMessageFromChat(message)
            }.show()
    }

    /**
     * Fires when user presses 'send' button.
     *
     * @param input input entered by user
     * @return if input text is valid, you must return `true` and input will be cleared, otherwise return false.
     */
    override fun onSubmit(input: CharSequence?): Boolean {
        textInputMessage = input.toString().trim()
        emitSendMessage()
        return true
    }

    /**
     * Fires when user presses 'add' button.
     */
    override fun onAddAttachments() {

    }

    /**
     * Fires when selected items count is changed.
     *
     * @param count count of selected items.
     */
    override fun onSelectionChanged(count: Int) {
    }

    /**
     * Fires when user scrolled to the end of list.
     *
     * @param page            next page to download.
     * @param totalItemsCount current items count.
     */
    override fun onLoadMore(page: Int, totalItemsCount: Int) {
        if (!stopLoadingMoreMessages!!)
            chatViewModel.getChatMessages(
                sessionHelper.userProfile.id!!,
                lastMessageTime!!,
                messagesTake!!
            )
    }

    /**
     * Fires when user presses start typing
     */
    override fun onStartTyping() {
//            binding.userTypingLayout.visibility = View.VISIBLE
    }

    /**
     * Fires when user presses stop typing
     */
    override fun onStopTyping() {
//        binding.userTypingLayout.visibility = View.GONE
    }

    override fun onLongClick(message: Message) {
        if (message?.senderUserId?.equals(sessionHelper.userProfile.id)!!)
            RemoveChatMessageDialog(this, message) { view, message ->
                emitRemoveMessageFromChat(message)
            }.show()
    }

    fun goAdDetails(view: View) {

    }
}