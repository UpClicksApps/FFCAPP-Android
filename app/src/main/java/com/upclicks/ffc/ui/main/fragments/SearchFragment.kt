package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.FragmentSearchBinding
import com.upclicks.ffc.ui.cart.ShoppingCartActivity
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.ProductRequest
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import q.rorbin.badgeview.QBadgeView
import www.sanju.motiontoast.MotionToast

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    lateinit var binding: FragmentSearchBinding

    private val cartViewModel: CartViewModel by viewModels()

    var productsList = ArrayList<Product>()
    lateinit var productAdapter: ProductGridAdapter
    private val productViewModel: ProductViewModel by viewModels()
    private var productRequest = ProductRequest()

    override fun setUpLayout() {
        binding = FragmentSearchBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpUiList()
        setUpObservers()
    }

    private fun setUpObservers() {
        productViewModel.getProducts(productRequest)
        productViewModel.observeProductList.observe(this, Observer { productResponse ->
            if (productResponse != null && !productResponse.products.isNullOrEmpty()) {
                productsList.clear()
                productsList.addAll(productResponse.products!!)
                productAdapter.notifyDataSetChanged()
            }
        })

        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
        })
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.search)
        binding.toolbar.backIv.visibility = View.GONE
    }


    private fun setUpUiList() {
        productAdapter = ProductGridAdapter(requireContext(), productsList,
            onFavoriteClicked = {position->
                productViewModel.assign(productsList[position].id!!, onAddToFavorite = {message->
//                    callProducts()
                    shoMsg(message, MotionToast.TOAST_SUCCESS)
                })
            }, onCartClicked = {
                cartViewModel.addProductToCart(productsList[it].id!!,productsList[it].currentPrice!!)
            }, onItemClicked = {
                startActivity(
                    Intent(
                        requireContext(),
                        ProductDetailsActivity::class.java
                    ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].id)
                )
            })
        binding.recycler.adapter = productAdapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}