package com.upclicks.ffc.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityProductDetailsBinding
import com.upclicks.ffc.databinding.ActivityShoppingCartBinding
import com.upclicks.ffc.ui.products.adapter.CartAdapter
import com.upclicks.ffc.ui.products.adapter.WalletAdapter
import com.upclicks.ffc.ui.products.model.Cart
import com.upclicks.ffc.ui.products.model.Wallet

class ShoppingCartActivity  : BaseActivity() {
    lateinit var binding: ActivityShoppingCartBinding


    var cartList = ArrayList<Cart>()
    lateinit var cartAdapter : CartAdapter


    override fun getLayoutResourceId(): View {
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
        setUpUiList()
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

        }
    }

    private fun setUpUiList() {
        cartAdapter = CartAdapter(this,cartList, onItemClicked = {})
        binding.recycler.adapter = cartAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}