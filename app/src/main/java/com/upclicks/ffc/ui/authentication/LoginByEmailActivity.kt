package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityLoginByEmailBinding
import com.upclicks.ffc.ui.MainActivity
import com.upclicks.ffc.ui.authentication.model.request.LoginRequest
import com.upclicks.ffc.ui.general.component.Validator
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel

class LoginByEmailActivity : BaseActivity() {
    lateinit var binding: ActivityLoginByEmailBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivityLoginByEmailBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpPageUi()
        setUpPageActions()
        setUpObserver()
    }

    // set up page ui
    private fun setUpPageUi() {
    }


    // set up page listeners and callback
    private fun setUpPageActions() {
        binding.skip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.done.setOnClickListener {
            login()
        }
        binding.forgetPasswordTv.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpFirstActivity::class.java))
        }
    }

    private fun setUpObserver() {
        //Observe authentication
        accountViewModel.authResponse.observe(this, Observer {
            sessionHelper.userSession = it
            //Verify Session
            accountViewModel.callVerifySession()
        })
        //Observe session verification
        accountViewModel.observeVerifySession.observe(this) {
            if (it.profile !== null)
                sessionHelper.userProfile = it.profile!!
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun login() {
        var email = binding.emailInput.editText!!.text.toString()
        var password = binding.passwordInput.editText!!.text.toString()
        if (TextUtils.isEmpty(email) || !Validator.isEmailValid(email)) {
            binding.emailInput.editText!!.startAnimation(Validator.shakeError())
            return
        }
        if (TextUtils.isEmpty(password)) {
            binding.passwordInput.editText!!.startAnimation(Validator.shakeError())
            return
        }
        val memberShipRequest = LoginRequest()
        memberShipRequest.usernameOrEmailAddress = email
        memberShipRequest.password = password
        accountViewModel.auth(email, password)
    }
}
