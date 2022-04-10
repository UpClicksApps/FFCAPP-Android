package com.upclicks.ffc.ui.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityCheckout1Binding
import com.upclicks.ffc.databinding.ActivityCheckout2Binding

class Checkout2Activity : BaseActivity() {
    lateinit var binding : ActivityCheckout2Binding


    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout2Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpPageActions()
    }

    private fun setUpPageActions() {

        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this,Checkout2Activity::class.java))
        }

    }
}