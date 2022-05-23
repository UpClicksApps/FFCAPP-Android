package com.upclicks.ffc.ui.cart

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityShoppingCartBinding
import com.upclicks.ffc.ui.checkout.Checkout1Activity
import com.upclicks.ffc.ui.products.adapter.CartAdapter
import com.upclicks.ffc.ui.cart.model.Cart
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel

class ShoppingCartActivity : BaseActivity() {
    lateinit var binding: ActivityShoppingCartBinding


    var cartList = ArrayList<Cart>()
    lateinit var cartAdapter: CartAdapter

    private val cartViewModel: CartViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
        setUpUiList()
        setUpObserver()
    }

    private fun setUpObserver() {
        cartViewModel.observeCartDetails.observe(this, Observer { cartDetails ->
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
            startActivity(Intent(this, Checkout1Activity::class.java))
        }
    }

    private fun setUpUiList() {
        cartAdapter = CartAdapter(this, cartList, onItemClicked = {})
        binding.recycler.adapter = cartAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}