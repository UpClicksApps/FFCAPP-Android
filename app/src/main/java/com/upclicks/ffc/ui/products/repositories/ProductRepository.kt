package com.upclicks.ffc.ui.products.repositories

import com.upclicks.ffc.data.remote.ApiService
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.ProductDetails

@ExperimentalCoroutinesApi
class ProductRepository @Inject constructor(private val apiService: ApiService) {


    //Get Categories
    fun getCategories() : Observable<Result<List<Category>>> {
        return apiService.getCategories()
    }

    //Get All Categories
    fun getAllCategories(skip:Int,take:Int) : Observable<Result<List<Category>>> {
        return apiService.getAllCategories(skip, take)
    }
    //Get TopSales
    fun getTopSales() : Observable<Result<List<Product>>> {
        return apiService.getTopSales()
    }
    //Get Products
    fun getProducts() : Observable<Result<List<Product>>> {
        return apiService.getProducts()
    }
    //Get Products
    fun getProductDetails(id:String) : Observable<Result<ProductDetails>> {
        return apiService.getProductDetails(id)
    }

}