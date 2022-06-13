package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityLoginByEmailBinding
import com.upclicks.ffc.ui.authentication.model.request.LoginRequest
import com.upclicks.ffc.ui.general.component.Validator
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.CustomMaterialInputHelper
import com.upclicks.ffc.ui.general.component.material.BaseMaterialEditText
import com.upclicks.ffc.ui.general.component.material.CustomMaterialInputLayout
import com.upclicks.ffc.ui.main.MainActivity

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
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this
    }

    // set up page ui
    private fun setUpPageUi() {
    }


    // set up page listeners and callback
    private fun setUpPageActions() {
        binding.skip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.loginBtn.setOnClickListener {
            login()
        }
        binding.forgetPasswordTv.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
        binding.signUpTv.setOnClickListener {
            startActivity(Intent(this, SignUpFirstActivity::class.java))
        }
        CustomMaterialInputHelper.setUpInputsTypingCallback(createInputViewsList())
    }

    // create lis of inputs view
    private fun createInputViewsList(): ArrayList<CustomMaterialInputLayout> {
        var inputsList = ArrayList<CustomMaterialInputLayout>()
        inputsList.add(binding.emailInput)
        inputsList.add(binding.passwordInput)
        return inputsList
    }

    //set up observers
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
    // make login request
    private fun login() {
        if (CustomMaterialInputHelper.checkIfInputsIsValid(this, createInputViewsList())) {
            val loginRequest = LoginRequest()
            loginRequest.usernameOrEmailAddress = binding.emailInput.editText!!.text.toString()
            loginRequest.password = binding.passwordInput.editText!!.text.toString()
            accountViewModel.auth(loginRequest)
        }
    }
}
