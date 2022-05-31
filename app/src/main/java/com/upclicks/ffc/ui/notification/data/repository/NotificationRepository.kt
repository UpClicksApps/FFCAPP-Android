package com.upclicks.ffc.ui.notification.data.repository

import com.upclicks.ffc.data.remote.ApiService
import com.upclicks.ffc.ui.notification.data.model.Notification
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result

@ExperimentalCoroutinesApi
class NotificationRepository @Inject constructor(private val apiService: ApiService) {
    fun callNotificationsList(skip: Int, take: Int): Observable<Result<List<Notification>>> {
        return apiService.callNotificationsList(skip, take)
    }
    fun callReadNotification(id: Any): Observable<Result<String>> {
        return apiService.callReadNotification(id)
    }
    fun callReadAllNotifications(): Observable<Result<String>> {
        return apiService.callReadAllNotifications()
    }
    fun callDeleteAllNotifications(): Observable<Result<String>> {
        return apiService.callDeleteAllNotifications()
    }
    fun callDeleteNotification(id: String): Observable<Result<String>> {
        return apiService.callDeleteNotification(id)
    }
}