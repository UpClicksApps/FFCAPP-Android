package com.upclicks.ffc.ui.cart.repositories

import com.upclicks.ffc.data.remote.ApiService
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.cart.model.CartActionResponse
import com.upclicks.ffc.ui.cart.model.CartDetails
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.ProductDetails
import retrofit2.http.Body

@ExperimentalCoroutinesApi
class CartRepository @Inject constructor(private val apiService: ApiService) {

    //getCurrentCartDetails
    fun getCurrentCartDetails() : Observable<Result<CartDetails>> {
        return apiService.getCurrentCartDetails()
    }

    //Get Products
    fun deleteShoppingCart() : Observable<Result<String>> {
        return apiService.deleteShoppingCart()
    }

    //addProductToCart
    fun addProductToCart(body: Any) : Observable<Result<CartActionResponse>> {
        return apiService.addProductToCart(body)
    }
    //updateProductQuantity
    fun updateProductQuantity(body: Any) : Observable<Result<CartActionResponse>> {
        return apiService.updateProductQuantity(body)
    }
    //removeProductFromCart
    fun removeProductFromCart(productId: String) : Observable<Result<CartActionResponse>> {
        return apiService.removeProductFromCart(productId)
    }


}