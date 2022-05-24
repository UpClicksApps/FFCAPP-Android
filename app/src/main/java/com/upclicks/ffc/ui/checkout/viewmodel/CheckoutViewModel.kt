package com.upclicks.ffc.ui.checkout.viewmodel

import com.upclicks.ffc.base.BaseViewModel
import com.upclicks.ffc.rx.CustomRxObserver
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.checkout.model.CheckoutResponse
import com.upclicks.ffc.ui.checkout.model.PaymentResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.ui.checkout.repositories.CheckoutRepository
import com.upclicks.ffc.data.remote.Result


@ExperimentalCoroutinesApi
@HiltViewModel
class CheckoutViewModel
@Inject constructor(
    private val checkoutRepository: CheckoutRepository,
) : BaseViewModel() {



    //make checkout
    fun checkout(checkoutRequest: CheckoutRequest, onResult: (CheckoutResponse) -> Unit) {
        isLoading.postValue(true)
        checkoutRepository.checkout(checkoutRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<CheckoutResponse>>(this@CheckoutViewModel) {
                override fun onResponse(response: Result<CheckoutResponse>) {
                    isLoading.postValue(false)
                    onResult(response?.result!!)
                }
            })
    }
    //check payment online
    fun checkPaymentOnline(code:String, onResult: (PaymentResponse) -> Unit) {
        isLoading.postValue(true)
        checkoutRepository.checkPaymentOnline(code)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<PaymentResponse>>(this@CheckoutViewModel) {
                override fun onResponse(response: Result<PaymentResponse>) {
                    isLoading.postValue(false)
                    onResult(response?.result!!)
                }
            })
    }
}