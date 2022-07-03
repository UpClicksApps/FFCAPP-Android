package com.upclicks.ffc.ui.chat.data.repository

import com.upclicks.ffc.data.remote.ApiService
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.data.remote.Result
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ChatRepository @Inject constructor(private val apiService: ApiService) {

    //Get Chat Messages
    fun getChatMessages(
        memberId: String,
        lastMessageId: String,
        take: Int
    ): Observable<Result<List<Message>>> {
        return apiService.getChatMessages(memberId,lastMessageId, take)
    }



}