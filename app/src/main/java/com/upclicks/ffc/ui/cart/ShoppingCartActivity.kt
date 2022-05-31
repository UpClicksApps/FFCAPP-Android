package com.upclicks.ffc.ui.cart

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityShoppingCartBinding
import com.upclicks.ffc.ui.checkout.Checkout1Activity
import com.upclicks.ffc.ui.products.adapter.CartAdapter
import com.upclicks.ffc.ui.cart.model.Cart
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.checkout.model.CheckoutOrder
import www.sanju.motiontoast.MotionToast

class ShoppingCartActivity : BaseActivity() {
    lateinit var binding: ActivityShoppingCartBinding
    var cartList = ArrayList<Cart>()
    lateinit var cartAdapter: CartAdapter
    private val cartViewModel: CartViewModel by viewModels()

    var checkoutRequest = CheckoutRequest()

    override fun getLayoutResourceId(): View {
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        checkoutRequest.orderProducts = ArrayList()
        checkoutRequest.checkoutOrder = CheckoutOrder()
        setUpToolbar()
        setUpPageActions()
        setUpUiList()
        setUpObserver()
        binding.viewModel = cartViewModel
        binding.lifecycleOwner = this
    }

    private fun showEmptyCart() {
        binding.emptyCartLayout.root.visibility = View.VISIBLE
        binding.toolbar.root.visibility = View.GONE
        binding.emptyCartLayout.btnClick.setOnClickListener {
            finish()
        }
    }
    private fun setUpObserver() {
        cartViewModel.getCurrentCartDetails()
        cartViewModel.observeCartDetails.observe(this, Observer { cartDetails ->
            if (cartDetails != null) {
                binding.cartDetails = cartDetails
                if (!cartDetails.orderProducts.isNullOrEmpty()) {
                    checkoutRequest.orderProducts!!.clear()
                    checkoutRequest.orderProducts!!.addAll(cartDetails.orderProducts!!)
                    cartList.clear()
                    cartList.addAll(cartDetails.orderProducts!!)
                    cartAdapter.notifyDataSetChanged()
                } else {
                    showEmptyCart()
                }
            }
        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
            cartViewModel.getCurrentCartDetails()
        })
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.shopping_cart)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.checkoutBtn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Checkout1Activity::class.java
                ).putExtra(Keys.Intent_Constants.CHECKOUT, Gson().toJson(checkoutRequest))
            )
        }
    }

    private fun setUpUiList() {
        cartAdapter = CartAdapter(this, cartList, onQuantityChanged = { cart, quantity ->
            cartViewModel.updateProductQuantity(cart.productId!!, quantity)
        }, onItemRemoved = { cart ->
            cartViewModel.removeProductFromCart(cart.productId!!)
        })
        binding.recycler.adapter = cartAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}