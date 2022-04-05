package com.upclicks.ffc.ui.general.language

import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityChangeLanguageBinding

class ChangeLanguageActivity : BaseActivity() , SessionHelper.OnSessionUpdate{

    lateinit var binding: ActivityChangeLanguageBinding

    override fun getLayoutResourceId(): View {
        binding = ActivityChangeLanguageBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }
    // main function for this page
    private fun initPage() {
        setUpToolbar()
        setUpPageUi()
        setUpPageActions()
        binding.lifecycleOwner = this
    }
    private fun setUpPageUi() {
        binding.arabicCheck.isChecked = sessionHelper.isArabic(this)
        binding.englishCheck.isChecked = sessionHelper.isEnglish(this)
    }
    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.change_language)
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }
    // set up page listeners and callback
    private fun setUpPageActions() {
        binding.arabicFrameLayout.setOnClickListener {
            binding.arabicCheck.isChecked = true
            binding.englishCheck.isChecked = false
            sessionHelper.setLanguageArabic(this)
        }
        binding.englishFrameLayout.setOnClickListener {
            binding.englishCheck.isChecked = true
            binding.arabicCheck.isChecked = false
            sessionHelper.setLanguageEnglish(this)
        }
        binding.arabicCheck.setOnClickListener { binding.arabicFrameLayout.performClick() }
        binding.englishCheck.setOnClickListener { binding.englishFrameLayout.performClick() }

    }
    override fun refreshActivity() {
//        startActivity(Intent(this, WelcomeActivity::class.java))
        finishAffinity()
    }
}