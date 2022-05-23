package com.upclicks.ffc.ui.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.base.BaseViewModel
import com.upclicks.ffc.rx.CustomRxObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.cart.model.CartActionResponse
import com.upclicks.ffc.ui.cart.model.CartDetails
import com.upclicks.ffc.ui.cart.repositories.CartRepository
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.ProductDetails
import com.upclicks.ffc.ui.products.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@ExperimentalCoroutinesApi
@HiltViewModel
class CartViewModel
@Inject constructor(
    private val cartRepository: CartRepository,
) : BaseViewModel() {
    private val cartDetails: MutableLiveData<CartDetails> = MutableLiveData()
    private val cartActionResponse: MutableLiveData<CartActionResponse> = MutableLiveData()
    val observeCartDetails: LiveData<CartDetails>
        get() = cartDetails

    val observeCartActionResponse: LiveData<CartActionResponse>
        get() = cartActionResponse


    //getCurrentCartDetails
    fun getCurrentCartDetails() {
        cartRepository.getCurrentCartDetails()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<CartDetails>>(this@CartViewModel) {
                override fun onResponse(response: Result<CartDetails>) {
                    cartDetails.postValue(response.result!!)
                }
            })
    }

    //addProductToCart
    fun addProductToCart(productId: String, price: Double, quantity: Int) {
        var body = HashMap<Any,Any>()
        body["productId"] = productId
        body["price"] = price
        body["quantity"] = quantity
        cartRepository.addProductToCart(body)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<CartActionResponse>>(this@CartViewModel) {
                override fun onResponse(response: Result<CartActionResponse>) {
                    cartActionResponse.postValue(response.result!!)
                }
            })
    }

    //updateProductQuantity
    fun updateProductQuantity(productId: String, quantity: Int) {
        var body = HashMap<Any,Any>()
        body["productId"] = productId
        body["quantity"] = quantity
        cartRepository.updateProductQuantity(body)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<CartActionResponse>>(this@CartViewModel) {
                override fun onResponse(response: Result<CartActionResponse>) {
                    cartActionResponse.postValue(response.result!!)
                }
            })
    }

    //removeProductFromCart
    fun removeProductFromCart(productId: String) {
        cartRepository.removeProductFromCart(productId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<CartActionResponse>>(this@CartViewModel) {
                override fun onResponse(response: Result<CartActionResponse>) {
                    cartActionResponse.postValue(response.result!!)
                }
            })
    }

    //deleteShoppingCart
    fun deleteShoppingCart(onResult: (String) -> Unit) {
        cartRepository.deleteShoppingCart()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@CartViewModel) {
                override fun onResponse(response: Result<String>) {
                    onResult(response.result!!)}
            })
    }

}