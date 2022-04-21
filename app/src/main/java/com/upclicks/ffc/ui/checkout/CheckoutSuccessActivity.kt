package com.upclicks.ffc.ui.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityCheckoutSuccessBinding
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.ui.main.MainActivity
import com.upclicks.ffc.ui.products.adapter.WalletAdapter
import com.upclicks.ffc.ui.products.model.Wallet

class CheckoutSuccessActivity : BaseActivity() {
    lateinit var binding: ActivityCheckoutSuccessBinding

    override fun getLayoutResourceId(): View {
        binding = ActivityCheckoutSuccessBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        binding.continueShoppingBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }

}