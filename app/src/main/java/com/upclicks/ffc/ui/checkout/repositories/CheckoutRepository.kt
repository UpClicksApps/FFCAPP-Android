package com.upclicks.ffc.ui.checkout.repositories

import com.upclicks.ffc.data.remote.ApiService
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.checkout.model.CheckoutResponse
import com.upclicks.ffc.ui.checkout.model.PaymentResponse
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result

@ExperimentalCoroutinesApi
class CheckoutRepository @Inject constructor(private val apiService: ApiService) {


    //make checkout
    fun checkout(checkoutRequest: CheckoutRequest) : Observable<Result<CheckoutResponse>> {
        return apiService.checkout(checkoutRequest)
    }

    //check payment online
    fun checkPaymentOnline(code: String) : Observable<Result<PaymentResponse>> {
        return apiService.checkPaymentOnline(code)
    }

}