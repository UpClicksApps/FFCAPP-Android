package com.upclicks.ffc.ui.general

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityAboutUsBinding
import com.upclicks.ffc.databinding.DialogLoginBinding
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel

class AboutUsActivity : BaseActivity() {

    lateinit var binding: ActivityAboutUsBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpObservers()
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpObservers() {
        accountViewModel.getAboutUs("AboutUs", onGetAboutUs = {
            binding.textTv.text = it
        })
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.about_us)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }
}