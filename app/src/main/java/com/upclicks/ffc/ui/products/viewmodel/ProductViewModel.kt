package com.upclicks.ffc.ui.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.base.BaseViewModel
import com.upclicks.ffc.rx.CustomRxObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.model.*
import com.upclicks.ffc.ui.products.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel


@ExperimentalCoroutinesApi
@HiltViewModel
class ProductViewModel
@Inject constructor(
    private val productRepository: ProductRepository,
) : BaseViewModel() {
    private val allCategoriesList: MutableLiveData<List<Category>> = MutableLiveData()
    private val homeCategoriesList: MutableLiveData<List<HomeProduct>> = MutableLiveData()
    private val topSalesList: MutableLiveData<List<Product>> = MutableLiveData()
    private val wishlistList: MutableLiveData<List<Product>> = MutableLiveData()
    private val productsList: MutableLiveData<ProductsResponse> = MutableLiveData()
    private val productDetails: MutableLiveData<ProductDetails> = MutableLiveData()

    val observeAllCategoriesList: LiveData<List<Category>>
        get() = allCategoriesList
    val observeTopSalesList: LiveData<List<Product>>
        get() = topSalesList
    val observeMyWishlistList: LiveData<List<Product>>
        get() = wishlistList
    val observeHomeCategoriesList: LiveData<List<HomeProduct>>
        get() = homeCategoriesList
    val observeProductList: LiveData<ProductsResponse>
        get() = productsList
    val observeProductDetails: LiveData<ProductDetails>
        get() = productDetails


    //Get Categories
    fun getCategories(onGetCategories: (List<Category>) -> Unit) {
        productRepository.getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Category>>>(this@ProductViewModel) {
                override fun onResponse(response: Result<List<Category>>) {
                    onGetCategories(response.result!!)
                }
            })
    }
    //assign
    fun assign(productId:String,onAddToFavorite: (String) -> Unit) {
        var body = HashMap<Any,Any>()
        body["productId"] = productId
        productRepository.assign(body)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@ProductViewModel) {
                override fun onResponse(response: Result<String>) {
                    onAddToFavorite(response.result!!)
                }
            })
    }
    //Get Categories
    fun getMyWishlist(skip: Int, take: Int) {
        productRepository.getMyWishlist(skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Product>>>(this@ProductViewModel) {
                override fun onResponse(response: Result<List<Product>>) {
                    wishlistList.postValue(response.result!!)
                }
            })
    }


    //Get Categories
    fun getAllCategories(skip: Int, take: Int) {
        productRepository.getAllCategories(skip, take)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Category>>>(this@ProductViewModel) {
                override fun onResponse(response: Result<List<Category>>) {
                    allCategoriesList.postValue(response.result!!)
                }
            })
    }


    //Get Top sales
    fun getTopSales() {
        productRepository.getTopSales()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Product>>>(this@ProductViewModel) {
                override fun onResponse(response: Result<List<Product>>) {
                    topSalesList.postValue(response.result)
                }
            })
    }

    //GetHomeCategories
    fun getHomeCategories() {
        productRepository.getHomeCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<HomeProduct>>>(this@ProductViewModel) {
                override fun onResponse(response: Result<List<HomeProduct>>) {
                    homeCategoriesList.postValue(response.result)
                }
            })
    }


    //Get Products
    fun getProducts(productRequest: ProductRequest) {
        productRepository.getProducts(productRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<ProductsResponse>>(this@ProductViewModel) {
                override fun onResponse(response: Result<ProductsResponse>) {
                    productsList.postValue(response.result)
                }
            })
    }

    //Get Product
    fun getProductDetails(id: String) {
        productRepository.getProductDetails(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<ProductDetails>>(this@ProductViewModel) {
                override fun onResponse(response: Result<ProductDetails>) {
                    productDetails.postValue(response.result)
                }
            })
    }


}