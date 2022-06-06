package com.upclicks.ffc.ui.orders.repositories

import com.upclicks.ffc.data.remote.ApiService
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.orders.model.OrderDetails
import com.upclicks.ffc.ui.orders.model.Order

@ExperimentalCoroutinesApi
class OrderRepository @Inject constructor(private val apiService: ApiService) {

    //Get MyOrders
    fun getMyOrders(orderStatus: Int, skip: Int, take: Int): Observable<Result<List<Order>>> {
        return apiService.getMyOrders(orderStatus, skip, take)
    }
    //Get Orders
    fun getOrder(orderId: String,notifyId: String): Observable<Result<OrderDetails>> {
        return apiService.getOrder(orderId,notifyId)
    }
    //Cancel Order
    fun cancelOrder(id: String): Observable<Result<String>> {
        return apiService.cancelOrder(id)
    }
}