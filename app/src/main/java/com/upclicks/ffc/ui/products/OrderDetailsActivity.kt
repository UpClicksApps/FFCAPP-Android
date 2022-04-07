package com.upclicks.ffc.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityOrderDetailsBinding
import com.upclicks.ffc.databinding.ActivityShoppingCartBinding
import com.upclicks.ffc.ui.products.adapter.CartAdapter
import com.upclicks.ffc.ui.products.model.Cart

class OrderDetailsActivity: BaseActivity() {
    lateinit var binding: ActivityOrderDetailsBinding

    override fun getLayoutResourceId(): View {
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
    }
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = "Order number: #15457"
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpPageActions() {
    }


}