package com.upclicks.ffc.ui.chat.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.architecture.BaseViewModel
import com.upclicks.ffc.rx.CustomRxObserver
import com.upclicks.ffc.ui.chat.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result


@ExperimentalCoroutinesApi
@HiltViewModel
class ChatViewModel
@Inject constructor(
    private val chatRepository: ChatRepository
) : BaseViewModel() {

    // messages
    private val _messages: MutableLiveData<List<Message>> = MutableLiveData()
    val observeMessages: LiveData<List<Message>>
        get() = _messages

    //Get Chat Messages
    fun getChatMessages(
        memberId: String,
        lastMessageId: String, take: Int
    ) {
        chatRepository.getChatMessages(memberId,lastMessageId, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Message>>>(this@ChatViewModel) {
                override fun onResponse(response: Result<List<Message>>) {
                    _messages.postValue(response.result)
                }
            })
    }

}