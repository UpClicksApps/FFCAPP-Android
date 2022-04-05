package com.upclicks.ffc.ui.notification.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.base.BaseViewModel
import com.upclicks.ffc.ui.notification.data.model.Notification
import com.upclicks.ffc.ui.notification.data.repository.NotificationRepository
import com.upclicks.ffc.rx.CustomRxObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
@ExperimentalCoroutinesApi
@HiltViewModel
class NotificationViewModel
@Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {

    //properties
    var notificationsListResult: MutableLiveData<List<Notification>> =
        MutableLiveData<List<Notification>>()
    var deleteNotificationResult = MutableLiveData<String>()
    var readNotificationResult = MutableLiveData<String>()


    //functions
    fun observeNotificationsListResult(): LiveData<List<Notification>> {
        return notificationsListResult
    }
    fun observeDeleteNotificationResult(): LiveData<String> {
        return deleteNotificationResult
    }
    fun observeReadNotificationResult(): LiveData<String> {
        return readNotificationResult
    }
//
    //Get notification list
    fun callNotificationsList(
        skip: Int, take: Int
    ) {
        notificationRepository.callNotificationsList(skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Notification>>>(this@NotificationViewModel){
                override fun onResponse(response: Result<List<Notification>>) {
                    notificationsListResult.postValue(response.result)
                }
            })
    }

    //Read notification
    fun callReadNotification(
        id: String
    ) {
        notificationRepository.callReadNotification(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@NotificationViewModel){
                override fun onResponse(response: Result<String>) {
                    readNotificationResult.postValue(response.result)
                }
            })
    }
    //Read all notification
    fun callReadAllNotifications() {
        notificationRepository.callReadAllNotifications()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@NotificationViewModel){
                override fun onResponse(response: Result<String>) {
                    readNotificationResult.postValue(response.result)
                }
            })
    }
    //Delete notification
    fun callDeleteNotification(
        id: String
    ) {
        notificationRepository.callDeleteNotification(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@NotificationViewModel){
                override fun onResponse(response: Result<String>) {
                    deleteNotificationResult.postValue(response.result)
                }
            })
    }
    //Delete all notification
    fun callDeleteAllNotifications() {
        notificationRepository.callDeleteAllNotifications()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@NotificationViewModel){
                override fun onResponse(response: Result<String>) {
                    deleteNotificationResult.postValue(response.result)
                }
            })
    }


}