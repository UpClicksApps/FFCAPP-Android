package com.upclicks.ffc.ui.checkout

import android.content.Intent
import android.view.View
import com.google.gson.Gson
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityCheckout1Binding
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput

class Checkout1Activity : BaseActivity() {
    lateinit var binding :ActivityCheckout1Binding
    var checkoutRequest = CheckoutRequest()
    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout1Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }
    private fun initPage() {
        setUpIntent()
        setUpPageActions()
    }
    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.CHECKOUT)!= null)
            checkoutRequest = Gson().fromJson(intent.getStringExtra(Keys.Intent_Constants.CHECKOUT),CheckoutRequest::class.java)
    }
    private fun setUpPageActions() {
        binding.nextBtn.setOnClickListener {
            next()
        }

        setUpInputsTypingCallback(createInputViewsList())
    }
    private fun next() {
//        checkoutRequest.order!!.emailAddress = binding.emailInput.text.toString()
        checkoutRequest.order!!.emailAddress = "aa@aa.com"
//        checkoutRequest.order!!.memberName = binding.nameInput.text.toString()
        checkoutRequest.order!!.memberName = "Ahmed"
//        checkoutRequest.order!!.phoneNumber = binding.nameInput.text.toString()
        checkoutRequest.order!!.phoneNumber = "1234564654"
//        checkoutRequest.order!!.notes = binding.notesInput.text.toString()
        checkoutRequest.order!!.notes = "Test notes"
        startActivity(Intent(this,Checkout2Activity::class.java).putExtra(Keys.Intent_Constants.CHECKOUT, Gson().toJson(checkoutRequest)))
    }

    // create lis of inputs view
    private fun createInputViewsList(): ArrayList<BaseInput> {
        var inputsList = ArrayList<BaseInput>()
        inputsList.add(binding.nameInput)
        inputsList.add(binding.emailInput)
        inputsList.add(binding.notesInput)
        inputsList.add(binding.phoneInput)
        inputsList.add(binding.cityInput)
        inputsList.add(binding.addressInput)
        inputsList.add(binding.houseNumberInput)
        inputsList.add(binding.shippingFeesInput)
        inputsList.add(binding.streetInput)
        return inputsList
    }
    // set up typingCallback for inputs views
    private fun setUpInputsTypingCallback(inputViews: ArrayList<BaseInput>) {
        inputViews.forEach { inputView ->
            inputView!!.setOnTextTyping(
                object : BaseInput.TypingCallback {
                    override fun onTyping(text: String) {
                    }
                })
        }
    }

}