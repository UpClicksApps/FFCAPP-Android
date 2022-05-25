package com.upclicks.ffc.ui.orders.repositories

import com.upclicks.ffc.data.remote.ApiService
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.orders.OrderDetails
import com.upclicks.ffc.ui.orders.model.Order
import com.upclicks.ffc.ui.products.model.*

@ExperimentalCoroutinesApi
class OrderRepository @Inject constructor(private val apiService: ApiService) {

    //Get MyOrders
    fun getMyOrders(orderStatus: Int, skip: Int, take: Int): Observable<Result<List<Order>>> {
        return apiService.getMyOrders(orderStatus, skip, take)
    }
    //Get Orders
    fun getOrder(orderId: String): Observable<Result<OrderDetails>> {
        return apiService.getOrder(orderId)
    }
}