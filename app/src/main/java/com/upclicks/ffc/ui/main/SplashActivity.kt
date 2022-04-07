package com.upclicks.ffc.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivitySplashBinding
import com.upclicks.ffc.ui.authentication.LoginByEmailActivity

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun getLayoutResourceId(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginByEmailActivity::class.java))
        }, Keys.SPLASH_DISPLAY_LENGTH)
    }
}