package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivitySignUpFirstBinding
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.main.MainActivity

class SignUpFirstActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpFirstBinding
    private val accountViewModel : AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivitySignUpFirstBinding.inflate(layoutInflater)
        binding.createAccountBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.loginTv.setOnClickListener {
            onBackPressed()
        }
//        initPage()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpPageUi()
//        setUpPageActions()
    }

    // set up page ui
    private fun setUpPageUi() {
    }

//
//    // set up page listeners and callback
//    private fun setUpPageActions() {
//        binding.done.setOnClickListener {
//            createMemberRequest()
//        }
//        binding.signInTv.setOnClickListener {
//           onBackPressed()
//        }
//    }
//
//    private fun createMemberRequest() {
//        var fName = binding.nameInput.editText!!.text.toString()
//        var lName = binding.surnameInput.editText!!.text.toString()
//        var email = binding.emailInput.editText!!.text.toString()
//        var phone = binding.phoneInput.editText!!.text.toString()
//        var password = binding.passwordInput.editText!!.text.toString()
//        var rePassword = binding.confirmPasswordInput.editText!!.text.toString()
//
//        if(TextUtils.isEmpty(fName)){
//            binding.nameInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        if(TextUtils.isEmpty(lName)){
//            binding.surnameInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        if(TextUtils.isEmpty(email) || !Validator.isEmailValid(email)){
//            binding.emailInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        if(TextUtils.isEmpty(phone) || !Validator.isValidPhoneNumber(phone)){
//            binding.phoneInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        if(TextUtils.isEmpty(password)){
//            binding.passwordInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        if(TextUtils.isEmpty(rePassword)){
//            binding.confirmPasswordInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        if(password != rePassword){
//            binding.passwordInput.editText!!.startAnimation(Validator.shakeError())
//            binding.confirmPasswordInput.editText!!.startAnimation(Validator.shakeError())
//            return
//        }
//
//        val memberShipRequest = CreateMemberShipRequest()
//        memberShipRequest.name = fName
//        memberShipRequest.surname = lName
//        memberShipRequest.emailAddress = email
//        memberShipRequest.phoneNumber = phone
//        memberShipRequest.password = password
//        accountViewModel.createMembershipRequest(memberShipRequest,onMembershipRequestCreated = {
//            val gson = Gson()
//            startActivity(Intent(this, VerifyMembershipActivity ::class.java)
//                .putExtra(MEMBER_REQUEST,gson.toJson(memberShipRequest))
//                .putExtra(MEMBER_RESPONSE,gson.toJson(it)))
//        })
//    }

}