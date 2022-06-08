package com.upclicks.ffc.ui.checkout

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityCheckout1Binding
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.general.component.CustomInputHelper
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection
import com.upclicks.ffc.ui.general.component.spinner.CustomSpinner

class Checkout1Activity : BaseActivity() {
    lateinit var binding: ActivityCheckout1Binding
    var checkoutRequest = CheckoutRequest()

    private val accountViewModel: AccountViewModel by viewModels()
    var deliveryFees = 0.0
    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout1Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpPageActions()
        setUpObservers()
    }

    private fun setUpObservers() {
        getGovernorates()
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.CHECKOUT) != null)
            checkoutRequest = Gson().fromJson(
                intent.getStringExtra(Keys.Intent_Constants.CHECKOUT),
                CheckoutRequest::class.java
            )
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.delivery_address)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.nextBtn.setOnClickListener {
            next()
        }
        CustomInputHelper.setUpInputsTypingCallback(createInputViewsList())
        binding.governorateSP.setCustomSelectionCallback(object : CustomSpinner.SelectionCallback {
            override fun onItemSelected(baseSelection: BaseSelection) {
                getCities(baseSelection.id!!)
            }
        })
        binding.citySP.setCustomSelectionCallback(object : CustomSpinner.SelectionCallback {
            override fun onItemSelected(baseSelection: BaseSelection) {
                binding.deliveryFeesInput.setText(
                    "" + baseSelection.deliveryFees + " " + getString(
                        R.string.currency_code
                    )
                )
                deliveryFees = baseSelection.deliveryFees!!
            }
        })
    }

    private fun next() {
        if (CustomInputHelper.checkIfInputsIsValid(this, createInputViewsList())) {
            checkoutRequest.checkoutOrder!!.emailAddress = binding.emailInput.text.toString()
            checkoutRequest.checkoutOrder!!.memberName = binding.nameInput.text.toString()
            checkoutRequest.checkoutOrder!!.phoneNumber = binding.phoneInput.text.toString()
            checkoutRequest.checkoutOrder!!.notes = binding.notesInput.text.toString()
            checkoutRequest.checkoutOrder!!.house = binding.houseNumberInput.text.toString()
            checkoutRequest.checkoutOrder!!.flatNo = binding.flatNumberInput.text.toString()
            checkoutRequest.checkoutOrder!!.street = binding.streetInput.text.toString()
            checkoutRequest.checkoutOrder!!.address = binding.addressInput.text.toString()
            checkoutRequest.checkoutOrder!!.deliveryFees = deliveryFees
            checkoutRequest.checkoutOrder!!.cityId = binding.citySP.baseSelection.id
            startActivity(
                Intent(
                    this,
                    Checkout2Activity::class.java
                ).putExtra(Keys.Intent_Constants.CHECKOUT, Gson().toJson(checkoutRequest))
            )
        }
    }

    private fun getCities(governorateId: String) {
        accountViewModel.getCities(governorateId) { cities ->
            if (!cities.isNullOrEmpty()) {
                binding.citySP.setSelectionList(cities)
            }
        }
    }

    private fun getGovernorates() {
        accountViewModel.getGovernorates { governorates ->
            if (!governorates.isNullOrEmpty()) {
                binding.governorateSP.setSelectionList(governorates)
                getCities(governorates[0].id!!)
            }
        }
    }

    // create lis of inputs view
    private fun createInputViewsList(): ArrayList<BaseInput> {
        var inputsList = ArrayList<BaseInput>()
        inputsList.add(binding.nameInput)
        inputsList.add(binding.emailInput)
        inputsList.add(binding.phoneInput)
        inputsList.add(binding.streetInput)
        inputsList.add(binding.houseNumberInput)
        inputsList.add(binding.flatNumberInput)
        inputsList.add(binding.addressInput)
        return inputsList
    }
}