package com.upclicks.ffc.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityCheckout2Binding
import com.upclicks.ffc.databinding.ActivityCheckout3Binding
import com.upclicks.ffc.ui.main.MainActivity

class Checkout3Activity: BaseActivity() {
    lateinit var binding : ActivityCheckout3Binding


    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout3Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpPageActions()
    }

    private fun setUpPageActions() {

        binding.checkoutBtn.setOnClickListener {
            startActivity(Intent(this,CheckoutSuccessActivity::class.java))
        }
    }
}