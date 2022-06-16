package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityCheckoutSuccessBinding
import com.upclicks.ffc.databinding.ActivityResetPasswordSuccessBinding
import com.upclicks.ffc.ui.authentication.model.request.LoginRequest
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.main.MainActivity

class ResetPasswordSuccessActivity : BaseActivity() {
    lateinit var binding: ActivityResetPasswordSuccessBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivityResetPasswordSuccessBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpObservers()
        binding.doneBtn.setOnClickListener {
            autoLogin()
        }
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this
    }
    //Auto login after verify member request
    private fun autoLogin() {
        var loginRequest = Gson().fromJson(intent.getStringExtra("login"),LoginRequest::class.java)
        accountViewModel.auth(loginRequest)
    }

    //Setup observers
    private fun setUpObservers() {
        //Observe authentication
        accountViewModel.authResponse.observe(this, Observer {
            sessionHelper.userSession = it
            //Verify Session
            accountViewModel.callVerifySession()
        })
        //Observe session verification
        accountViewModel.observeVerifySession.observe(this) {
            sessionHelper.userProfile = it.profile!!
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }

}