package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys.Intent_Constants.MEMBER_REQUEST
import com.upclicks.ffc.commons.Keys.Intent_Constants.MEMBER_RESPONSE
import com.upclicks.ffc.databinding.ActivityVerifyMembershipBinding
import com.upclicks.ffc.ui.authentication.model.request.CreateMemberShipRequest
import com.upclicks.ffc.ui.authentication.model.response.MembershipResponse
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.Validator
import com.upclicks.ffc.ui.main.MainActivity
import www.sanju.motiontoast.MotionToast

class VerifyMembershipActivity : BaseActivity() {
    lateinit var binding: ActivityVerifyMembershipBinding
    private val accountViewModel: AccountViewModel by viewModels()
    private var memberRequest: CreateMemberShipRequest? = null
    private var membershipResponse: MembershipResponse? = null

    override fun getLayoutResourceId(): View {
        binding = ActivityVerifyMembershipBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpReceivedIntent()
        setUpPageUi()
        setUpPageActions()
        setUpObservers()
    }

    //Receive data from login screen
    private fun setUpReceivedIntent() {
        val gson = Gson()
        memberRequest = gson.fromJson(
            intent.getStringExtra(MEMBER_REQUEST),
            CreateMemberShipRequest::class.java
        )
        membershipResponse =
            gson.fromJson(intent.getStringExtra(MEMBER_RESPONSE), MembershipResponse::class.java)
    }

    // set up page ui
    private fun setUpPageUi() {
    }


    // set up page listeners and callback
    private fun setUpPageActions() {
        binding.done.setOnClickListener {
            verifyMemberRequest()
        }
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

    //Verify member request
    private fun verifyMemberRequest() {
        val sentCode = binding.otpView.text.toString()
        if (TextUtils.isEmpty(sentCode)) {
            shoMsg(getString(R.string.please_insert_sms_code) + " !", MotionToast.TOAST_ERROR)
            binding.otpView.startAnimation(Validator.shakeError())
            return
        }
        accountViewModel.verifyMembership(
            membershipResponse?.membershipIdentity!!,
            sentCode,
            onMembershipVerified = {
                if (it) {
                    //Auto Login
                    autoLogin()
                }
            })
    }

    //Auto login after verify member request
    private fun autoLogin() {
        accountViewModel.auth(memberRequest?.emailAddress!!, memberRequest?.password!!)
    }

    fun resendCode(view: View) {
        accountViewModel.createMembershipRequest(memberRequest!!, onMembershipRequestCreated = {
            binding.msg.text = it.message
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