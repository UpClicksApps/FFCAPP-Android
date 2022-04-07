package com.upclicks.ffc.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityProductDetailsBinding
import com.upclicks.ffc.ui.products.adapter.WalletAdapter
import com.upclicks.ffc.ui.products.model.Wallet

class ProductDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailsBinding
    override fun getLayoutResourceId(): View {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpPageActions()
    }

    private fun setUpPageActions() {
        binding.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.addToCartBtn.setOnClickListener {

        }
    }
}