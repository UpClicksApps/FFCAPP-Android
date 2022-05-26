package com.upclicks.ffc.ui.products.repositories

import com.upclicks.ffc.data.remote.ApiService
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.model.*

@ExperimentalCoroutinesApi
class ProductRepository @Inject constructor(private val apiService: ApiService) {


    //Get Categories
    fun getCategories() : Observable<Result<List<Category>>> {
        return apiService.getCategories()
    }

    //Get MyWishlist
    fun getMyWishlist(skip: Int,take: Int) : Observable<Result<List<Product>>> {
        return apiService.getMyWishlist(skip,take)
    }


    //Assign
    fun assign(body:Any) : Observable<Result<String>> {
        return apiService.assign(body)
    }

    //Get All Categories
    fun getAllCategories(skip:Int,take:Int) : Observable<Result<List<Category>>> {
        return apiService.getAllCategories(skip, take)
    }
    //Get TopSales
    fun getTopSales() : Observable<Result<List<Product>>> {
        return apiService.getTopSales()
    }
    //Get Home Categories
    fun getHomeCategories() : Observable<Result<List<HomeProduct>>> {
        return apiService.getHomeCategories()
    }
    //Get Products
    fun getProducts(productRequest: ProductRequest) : Observable<Result<ProductsResponse>> {
        return apiService.getProducts(productRequest.categoryId!!,productRequest.productName!!, productRequest.sortProductBy!!,productRequest.minPrice!!
        ,productRequest.maxPrice!!,productRequest.skip!!,productRequest.take!!)
    }
    //Get Products
    fun getProductDetails(id:String) : Observable<Result<ProductDetails>> {
        return apiService.getProductDetails(id)
    }

}