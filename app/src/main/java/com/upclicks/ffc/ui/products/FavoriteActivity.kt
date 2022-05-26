package com.upclicks.ffc.ui.products

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityProductsListBinding
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.products.adapter.FavoriteAdapter
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast

class FavoriteActivity : BaseActivity() {
    lateinit var binding: ActivityFavoriteBinding

    var productsList = ArrayList<Product>()
    lateinit var favoriteAdapter: FavoriteAdapter

    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    //load on scroll properties
    lateinit var linearLayoutManager: LinearLayoutManager
    var onScrollChangeListener: NestedScrollView.OnScrollChangeListener? = null
    var skip = 0
    var take: Int = 10
    var stopScroll: Boolean = false
    override fun getLayoutResourceId(): View {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setPageActions()
        setUpUiList()
        setUpObserver()
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this
    }

    private fun setPageActions() {
        setUpLoadOnScrollListener()
        binding.swipeRefresh.setOnRefreshListener {
            skip = 0
            productsList.clear()
            favoriteAdapter.notifyDataSetChanged()
            productViewModel.getMyWishlist(skip, take)
        }
    }

    private fun setUpObserver() {
        productViewModel.getMyWishlist(skip, take)
        productViewModel.observeMyWishlistList.observe(this, Observer { products ->
            if (!products.isNullOrEmpty()) {
                binding.emptyProductsTv.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE
                productsList.addAll(products)
                stopScrollIfDataEnded(binding.nestedScrollView, products.size)
                favoriteAdapter.notifyDataSetChanged()
            } else {
                binding.emptyProductsTv.visibility = View.VISIBLE
                binding.recycler.visibility = View.GONE
            }
        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
        })
    }

    // Set Up Load On Scroll Listener
    private fun setUpLoadOnScrollListener() {
        onScrollChangeListener =
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        if (!stopScroll) {
                            productViewModel.getMyWishlist(skip, take)
                        }
                    }
                }
            }
        binding.nestedScrollView.setOnScrollChangeListener(onScrollChangeListener)
    }

    // Stop Scroll If Data Ended
    private fun stopScrollIfDataEnded(scrollView: NestedScrollView, size: Int) {
        stopScroll = size < take
        skip += take
        scrollView.setOnScrollChangeListener(onScrollChangeListener)
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.favorites)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpUiList() {
        favoriteAdapter = FavoriteAdapter(this, productsList, onRemovedClicked = { product ->
            productViewModel.assign(product.id!!, onAddToFavorite = {message->
                shoMsg(message, MotionToast.TOAST_SUCCESS)
                productViewModel.getMyWishlist(skip, take)
            })
        }, onCartClicked = {product->
            cartViewModel.addProductToCart(product.id!!,product.currentPrice!!)
        }, onItemClicked = {
            startActivity(
                Intent(
                    this,
                    ProductDetailsActivity::class.java
                ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].id)
            )
        })
        binding.recycler.adapter = favoriteAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}