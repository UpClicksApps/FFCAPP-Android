package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityResetPasswordBinding
import com.upclicks.ffc.ui.authentication.model.request.ResetPasswordRequest
import com.upclicks.ffc.ui.authentication.model.response.ForgotPasswordResponse
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.Validator
import com.upclicks.ffc.ui.main.MainActivity
import www.sanju.motiontoast.MotionToast

class ResetPasswordActivity : BaseActivity() {
    lateinit var binding: ActivityResetPasswordBinding
    private val accountViewModel: AccountViewModel by viewModels()
    private var forgotPasswordResponse: ForgotPasswordResponse? = null

    override fun getLayoutResourceId(): View {
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setUpPageActions()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpPageUi()
        setUpPageActions()
    }

    // set up page ui
    private fun setUpPageUi() {
    }


    // set up page listeners and callback
    private fun setUpPageActions() {
        binding.done.setOnClickListener {
            verify()
        }
        binding.request.setOnClickListener {
            request()
        }
    }

    fun request() {
        var emailAddress = binding.emailInput.editText?.text.toString()
        if (TextUtils.isEmpty(emailAddress) || !Validator.isEmailValid(emailAddress)) {
            binding.emailInput.startAnimation(Validator.shakeError())
            return
        }
        accountViewModel.forgotPassword(emailAddress, onForgotPasswordRequested = {
            forgotPasswordResponse = it
            binding.emailView.visibility = View.GONE
            binding.verifyView.visibility = View.VISIBLE
        })
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
            finish()
        }
    }

    private fun verify() {
        var otpCode = binding.otpView.text.toString()
        var newPassword = binding.newPasswordInput.editText?.text.toString()
        var confirmNewPassword = binding.confirmNewPasswordInput.editText?.text.toString()

        if (TextUtils.isEmpty(otpCode)) {
            binding.otpView.startAnimation(Validator.shakeError())
            return
        }

        if (TextUtils.isEmpty(newPassword) || !Validator.isPasswordValid(newPassword)) {
            binding.newPasswordInput.startAnimation(Validator.shakeError())
            return
        }

        if (TextUtils.isEmpty(confirmNewPassword) || !Validator.isPasswordValid(confirmNewPassword)) {
            binding.confirmNewPasswordInput.startAnimation(Validator.shakeError())
            return
        }

        if (newPassword != confirmNewPassword) {
            binding.newPasswordInput.startAnimation(Validator.shakeError())
            binding.confirmNewPasswordInput.startAnimation(Validator.shakeError())
            shoMsg(getString(R.string.passwordsnotmatches) + " !", MotionToast.TOAST_ERROR)
            return
        }

        var resetPasswordRequest = ResetPasswordRequest()
        resetPasswordRequest.accountIdentity = forgotPasswordResponse?.accountIdentity
        resetPasswordRequest.newPassword = newPassword
        resetPasswordRequest.passwordResetCode = otpCode

        accountViewModel.resetPassword(resetPasswordRequest, onPasswordReseated = {
            if (it) {
                shoMsg(getString(R.string.passwordchanged), MotionToast.TOAST_SUCCESS)
                autoLogin()
            }
        })
    }

    //Auto login after verify member request
    private fun autoLogin() {
        accountViewModel.auth(
            binding.emailInput.editText!!.text.toString(),
            binding.newPasswordInput.editText!!.text.toString()
        )
    }

    fun resendCode(view: View) {
        var emailAddress = binding.emailInput.editText?.text.toString()
        if (TextUtils.isEmpty(emailAddress) || !Validator.isEmailValid(emailAddress)) {
            binding.emailInput.startAnimation(Validator.shakeError())
            return
        }
        accountViewModel.forgotPassword(emailAddress, onForgotPasswordRequested = {
            forgotPasswordResponse = it
            binding.emailView.visibility = View.GONE
            binding.verifyView.visibility = View.VISIBLE
            showCountDownResendButton()
        })
    }

    private fun showCountDownResendButton() {
        binding.resend.isEnabled = false
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.resend.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.resend.isEnabled = true
                binding.resend.text = getString(R.string.resend_a_new_code)
            }
        }.start()
    }
}