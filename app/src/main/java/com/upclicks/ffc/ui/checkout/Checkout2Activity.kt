package com.upclicks.ffc.ui.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityCheckout1Binding
import com.upclicks.ffc.databinding.ActivityCheckout2Binding
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.component.material.CustomMaterialInputLayout

class Checkout2Activity : BaseActivity() {
    lateinit var binding : ActivityCheckout2Binding

    var checkoutRequest = CheckoutRequest()

    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout2Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpPageActions()
    }
    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.CHECKOUT)!= null)
            checkoutRequest = Gson().fromJson(intent.getStringExtra(Keys.Intent_Constants.CHECKOUT),CheckoutRequest::class.java)
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.delivery_time)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpPageActions() {
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this,Checkout3Activity::class.java).putExtra(Keys.Intent_Constants.CHECKOUT, Gson().toJson(checkoutRequest)))
        }
    }



}