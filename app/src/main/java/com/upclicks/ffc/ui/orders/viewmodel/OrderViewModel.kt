package com.upclicks.ffc.ui.orders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.base.BaseViewModel
import com.upclicks.ffc.rx.CustomRxObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.orders.OrderDetails
import com.upclicks.ffc.ui.orders.model.CheckoutOrder
import com.upclicks.ffc.ui.orders.model.Order
import com.upclicks.ffc.ui.orders.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel


@ExperimentalCoroutinesApi
@HiltViewModel
class OrderViewModel
@Inject constructor(
    private val orderRepository: OrderRepository,
) : BaseViewModel() {
    private val ordersList: MutableLiveData<List<Order>> = MutableLiveData()
    private val orderDetails: MutableLiveData<OrderDetails> = MutableLiveData()

    val observeOrdersList: LiveData<List<Order>>
        get() = ordersList

    val observeOrderDetails: LiveData<OrderDetails>
        get() = orderDetails

    //Get MyOrders
    fun getMyOrders(orderStatus: Int, skip: Int, take: Int) {
        orderRepository.getMyOrders(orderStatus, skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Order>>>(this@OrderViewModel) {
                override fun onResponse(response: Result<List<Order>>) {
                    ordersList.postValue(response.result!!)
                }
            })
    }
    //Get MyOrders
    fun getOrder(orderId: String) {
        orderRepository.getOrder(orderId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<OrderDetails>>(this@OrderViewModel) {
                override fun onResponse(response: Result<OrderDetails>) {
                    orderDetails.postValue(response.result!!)
                }
            })
    }

}