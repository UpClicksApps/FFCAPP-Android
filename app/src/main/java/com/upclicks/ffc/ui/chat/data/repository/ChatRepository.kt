package com.upclicks.ffc.ui.chat.data.repository

import com.upclicks.ffc.data.remote.ApiService
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.chat.data.model.*
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@ExperimentalCoroutinesApi
class ChatRepository @Inject constructor(private val apiService: ApiService) {
    //Get Conversation
    fun getConversation(receiverId: String, convId: String): Observable<Result<Chat.Conversation>> {
        return apiService.getConversation(receiverId, convId)
    }

    //get LinkPreview
    fun getLinkPreview(url: String): Observable<Result<ConversationResponse>> {
        return apiService.getLinkPreview(url)
    }

    //get LinkPreview
    fun sendMediaFileToChat(
        partMap: Map<String, RequestBody>,
        files: List<MultipartBody.Part>
    ): Observable<Result<UploadFilesMessage>> {
        return apiService.sendMediaFileToChat(partMap, files)
    }

    //Get Chat Messages
    fun getChatMessages(
        memberId: String,
        messageId: String,
        take: Int
    ): Observable<Result<List<Message>>> {
        return apiService.getChatMessages(memberId,messageId, take)
    }

    //Get Chats
    fun getChats(skip: Int, take: Int): Observable<Result<List<Chat>>> {
        return apiService.getChats(skip, take)
    }

    //Get Ads Conversations
    fun getAdsConversations(skip: Int, take: Int): Observable<Result<List<AdConversation>>> {
        return apiService.getAdsConversations(skip, take)
    }
}