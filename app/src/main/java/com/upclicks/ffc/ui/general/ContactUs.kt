package com.upclicks.ffc.ui.general

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityContactUsBinding
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.model.FeedbackRequest
import www.sanju.motiontoast.MotionToast

class ContactUs : BaseActivity() {

    lateinit var binding: ActivityContactUsBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
        binding.lifecycleOwner = this
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.send_feedback)
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }


    // set up page listeners and callback
    private fun setUpPageActions() {
        binding.sendFeedbackButton.setOnClickListener {
            sendFeedback()
        }
        setUpInputsTypingCallback(createInputViewsList())
    }

    // create lis of inputs view
    private fun createInputViewsList(): ArrayList<BaseInput> {
        var inputsList = ArrayList<BaseInput>()
        inputsList.add(binding.nameEt)
        inputsList.add(binding.emailEt)
        inputsList.add(binding.phoneEt)
        inputsList.add(binding.messageEt)
        return inputsList
    }

    // set up typingCallback for inputs views
    private fun setUpInputsTypingCallback(inputViews: ArrayList<BaseInput>) {
        inputViews.forEach { inputView ->
            inputView.typingCallback = object : BaseInput.TypingCallback {
                override fun onTyping(text: String) {
                    binding.sendFeedbackButton.isEnabled = checkIfInputsIsValid()
                }
            }
        }
    }

    private fun checkIfInputsIsValid(): Boolean {
        var inputsViews = createInputViewsList()
        inputsViews.forEach { input ->
            if (!input.isValid)
                return false
        }
        return true
    }
    private fun sendFeedback() {
        Log.e("isValid",""+checkIfInputsIsValid())
        accountViewModel.sendFeedback(createFeedbackRequest(), onResult = { message ->
                shoMsg(message, MotionToast.TOAST_SUCCESS)
            })
    }

    private fun createFeedbackRequest(): FeedbackRequest {
        var feedbackRequest = FeedbackRequest()
        feedbackRequest.emailAddress = binding.emailEt.text.toString()
        feedbackRequest.phoneNumber = binding.phoneEt.text.toString()
        feedbackRequest.name = binding.nameEt.text.toString()
        feedbackRequest.message = binding.messageEt.text.toString()
        return feedbackRequest
    }

}