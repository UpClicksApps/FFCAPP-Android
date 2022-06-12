package com.upclicks.ffc.ui.checkout

import android.content.Intent
import android.view.View
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityCheckoutSuccessBinding
import com.upclicks.ffc.ui.main.MainActivity

class CheckoutSuccessActivity : BaseActivity() {
    lateinit var binding: ActivityCheckoutSuccessBinding

    override fun getLayoutResourceId(): View {
        binding = ActivityCheckoutSuccessBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        binding.continueShoppingBtn.setOnClickListener {
            sessionHelper.saveCartCount(0)
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }

    override fun onBackPressed() {

    }

}