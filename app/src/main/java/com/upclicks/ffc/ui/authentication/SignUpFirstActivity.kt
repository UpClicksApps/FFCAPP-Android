package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.google.gson.Gson
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys.Intent_Constants.MEMBER_REQUEST
import com.upclicks.ffc.commons.Keys.Intent_Constants.MEMBER_RESPONSE
import com.upclicks.ffc.databinding.ActivitySignUpFirstBinding
import com.upclicks.ffc.ui.authentication.model.request.CreateMemberShipRequest
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.CustomMaterialInputHelper
import com.upclicks.ffc.ui.general.component.material.CustomMaterialInputLayout
import com.upclicks.ffc.ui.main.MainActivity

class SignUpFirstActivity : BaseActivity() {
    lateinit var binding: ActivitySignUpFirstBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivitySignUpFirstBinding.inflate(layoutInflater)
        binding.createAccountBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        initPage()
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
        binding.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.createAccountBtn.setOnClickListener {
            createMemberRequest()
        }
        binding.loginTv.setOnClickListener {
            onBackPressed()
        }
        CustomMaterialInputHelper.setUpInputsTypingCallback(createInputViewsList())
    }

    // create lis of inputs view
    private fun createInputViewsList(): ArrayList<CustomMaterialInputLayout> {
        var inputsList = ArrayList<CustomMaterialInputLayout>()
        inputsList.add(binding.nameInput)
        inputsList.add(binding.surNameInput)
        inputsList.add(binding.phoneInput)
        inputsList.add(binding.emailInput)
        inputsList.add(binding.passwordInput)
        return inputsList
    }

    private fun createMemberRequest() {
        if (CustomMaterialInputHelper.checkIfInputsIsValid(this, createInputViewsList())) {
            val memberShipRequest = CreateMemberShipRequest()
            memberShipRequest.name = binding.nameInput.editText!!.text.toString()
            memberShipRequest.surname = binding.surNameInput.editText!!.text.toString()
            memberShipRequest.emailAddress = binding.emailInput.editText!!.text.toString()
            memberShipRequest.phoneNumber = binding.phoneInput.editText!!.text.toString()
            memberShipRequest.password = binding.passwordInput.editText!!.text.toString()
            accountViewModel.createMembershipRequest(
                memberShipRequest,
                onMembershipRequestCreated = {
                    val gson = Gson()
                    startActivity(
                        Intent(this, VerifyMembershipActivity::class.java)
                            .putExtra(MEMBER_REQUEST, gson.toJson(memberShipRequest))
                            .putExtra(MEMBER_RESPONSE, gson.toJson(it))
                    )
                })
        }
    }
}
