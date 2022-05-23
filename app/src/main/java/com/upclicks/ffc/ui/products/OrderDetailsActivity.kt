package com.upclicks.ffc.ui.products

import android.view.View
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityOrderDetailsBinding

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