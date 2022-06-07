package com.upclicks.ffc.rx

import androidx.annotation.NonNull
import com.upclicks.ffc.architecture.BaseViewModel
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.data.event.MessageEvent
import com.upclicks.ffc.data.event.UnAuthorizedEvent
import io.reactivex.rxjava3.observers.DefaultObserver
import retrofit2.HttpException
import retrofit2.Response


abstract class CustomRxObserver<T>(var baseViewModel: BaseViewModel) : DefaultObserver<Any?>() {

    init {
        baseViewModel.isLoading.postValue(true)
    }


    override fun onNext(t: Any?) {
        onResponse(t as T)
        baseViewModel.isLoading.postValue(false)
    }

    override fun onError(@NonNull e: Throwable) {
        baseViewModel.isLoading.postValue(false)
        if (e is HttpException) {
            val httpException: HttpException = e as HttpException
            if (httpException.code() === UNAUTHORIZED) {
                var errorEvent = Utils.parseResponse(e.response()!! as Response<Any>)
                var unAuthorizedEvent  =  UnAuthorizedEvent(errorEvent!!.code!!,
                    errorEvent!!.message!!,
                    errorEvent!!.details!!)
                RxBus.publish(unAuthorizedEvent)
            } else if (httpException.code() === INTERNAL_SERVER_ERROR || httpException.code() === INVALID_INPUT ) {
                var errorEvent = Utils.parseResponse(e.response()!! as Response<Any>)
                var messageEvent  =  MessageEvent(errorEvent!!.code!!,
                    errorEvent!!.message!!,
                    errorEvent!!.details!!)
                RxBus.publish(messageEvent)
            }
        } else {
            e.printStackTrace()
        }
    }

    override fun onComplete() {}
    abstract fun onResponse(response: T)

    companion object {
        //Network errors
        const val UNAUTHORIZED = 401
        const val INTERNAL_SERVER_ERROR = 500
        const val INVALID_INPUT = 400
    }
}