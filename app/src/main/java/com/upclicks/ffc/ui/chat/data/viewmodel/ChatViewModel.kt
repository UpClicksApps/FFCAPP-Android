package com.upclicks.ffc.ui.chat.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.architecture.BaseViewModel
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.rx.CustomRxObserver
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.ui.chat.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result


@ExperimentalCoroutinesApi
@HiltViewModel
class ChatViewModel
@Inject constructor(
    private val chatRepository: ChatRepository
) : BaseViewModel() {
    private val _adConversations: MutableLiveData<List<AdConversation>> = MutableLiveData()
    val observeAdConversation: LiveData<List<AdConversation>>
        get() = _adConversations

    // chat messages
    private val _chatConversation: MutableLiveData<Chat.Conversation> = MutableLiveData()
    val observeChatConversation: LiveData<Chat.Conversation>
        get() = _chatConversation


    // chats
    private val _chats: MutableLiveData<List<Chat>> = MutableLiveData()
    val observeChats: LiveData<List<Chat>>
        get() = _chats

    // messages
    private val _messages: MutableLiveData<List<Message>> = MutableLiveData()
    val observeMessages: LiveData<List<Message>>
        get() = _messages


    // get link preview
    private val _linkPreview: MutableLiveData<LinkPreview> = MutableLiveData()
    val observeLinkPreview: LiveData<LinkPreview>
        get() = _linkPreview


    // send media files
    private val _sendMediaFiles: MutableLiveData<UploadFilesMessage> = MutableLiveData()
    val observeSendMediaFiles: LiveData<UploadFilesMessage>
        get() = _sendMediaFiles


    //Get Chat Messages
    fun getConversation(receiverId: String, convId: String) {
        chatRepository.getConversation(receiverId, convId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<Chat.Conversation>>(this@ChatViewModel) {
                override fun onResponse(response: Result<Chat.Conversation>) {
                    _chatConversation.postValue(response.result)
                }

            })
    }

    //Get Chat Messages
    fun getLinkPreview(url: String) {
        chatRepository.getLinkPreview(url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<LinkPreview>>(this@ChatViewModel) {
                override fun onResponse(response: Result<LinkPreview>) {
                    _linkPreview.postValue(response.result)
                }

            })
    }


    //Get Chat Messages
    fun getChatMessages(
        memberId: String,
        lastMessageTime: String, take: Int
    ) {
        chatRepository.getChatMessages(memberId,lastMessageTime, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Message>>>(this@ChatViewModel) {
                override fun onResponse(response: Result<List<Message>>) {
                    _messages.postValue(response.result)
                }
            })
    }


    //Get Chats
    fun sendMediaFileToChat(
        conversationId: String, receiverId: Int, advertisementId: String, content: String,
        files: List<MultipartBody.Part>
    ) {
        val requestBodyPart = HashMap<String, RequestBody>()
        requestBodyPart["ConversationId"] = Utils.createPartFromString(conversationId)
        requestBodyPart["ReceiverId"] = Utils.createPartFromString("" + receiverId)
        requestBodyPart["Content"] = Utils.createPartFromString(content)
        requestBodyPart["advertisementId"] = Utils.createPartFromString(advertisementId)
        chatRepository.sendMediaFileToChat(requestBodyPart, files)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<UploadFilesMessage>>(this@ChatViewModel) {
                override fun onResponse(response: Result<UploadFilesMessage>) {
                    _sendMediaFiles.postValue(response.result)
                }
            })
    }

    //Get Chats
    fun getChats(skip: Int, take: Int) {
        chatRepository.getChats(skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Chat>>>(this@ChatViewModel) {
                override fun onResponse(response: Result<List<Chat>>) {
                    _chats.postValue(response.result)
                }
            })
    }

    //Get Ads Conversations
    fun getAdsConversations(skip: Int, take: Int) {
        chatRepository.getAdsConversations(skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<AdConversation>>>(this@ChatViewModel) {
                override fun onResponse(response: Result<List<AdConversation>>) {
                    _adConversations.postValue(response.result!!)
                }
            })
    }


    //Get Chats for each Group in my ads Adapter
    fun getChatsInAdChatsGroupAdapter(skip: Int, take: Int, onGetChats: (List<Chat>) -> Unit) {
        chatRepository.getChats(skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Chat>>>(this@ChatViewModel) {
                override fun onResponse(response: Result<List<Chat>>) {
                    onGetChats(response.result!!)
                }
            })
    }
}