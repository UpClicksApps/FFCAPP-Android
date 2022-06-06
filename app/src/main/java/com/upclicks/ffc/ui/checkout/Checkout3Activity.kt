package com.upclicks.ffc.ui.checkout

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityCheckout3Binding
import com.upclicks.ffc.ui.checkout.dialog.PaymentResponseDialog
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.checkout.model.CheckoutResponse
import com.upclicks.ffc.ui.checkout.viewmodel.CheckoutViewModel
import com.upclicks.ffc.ui.main.MainActivity

class Checkout3Activity : BaseActivity() {
    lateinit var binding: ActivityCheckout3Binding

    var checkoutRequest = CheckoutRequest()
    private val checkoutViewModel: CheckoutViewModel by viewModels()
    var checkoutResponse = CheckoutResponse()
    private val PAYMENT_REQUEST = 1250

    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout3Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpPageActions()
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.CHECKOUT) != null) {
            checkoutRequest = Gson().fromJson(
                intent.getStringExtra(Keys.Intent_Constants.CHECKOUT),
                CheckoutRequest::class.java
            )
            checkoutRequest.checkoutOrder!!.paymentWay = 1
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.delivery_time)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.checkoutBtn.setOnClickListener {
            checkoutViewModel.checkout(checkoutRequest, onResult = { checkoutResponse ->
                this.checkoutResponse = checkoutResponse     //Do online payment process
//                if (!checkoutResponse.onlinePaymenLink.isNullOrEmpty())
//                    startActivityForResult(
//                        Intent(this, OnlinePaymentActivity::class.java)
//                            .putExtra("url", checkoutResponse.onlinePaymenLink.toString() + "")
//                            .putExtra("callbackUrl", checkoutResponse.callbackUrl.toString() + "")
//                            .putExtra("sec", checkoutResponse.resultPageTimeoutInSec),
//                        PAYMENT_REQUEST
//                    )
//                else
                startActivity(
                    Intent(
                        this,
                        CheckoutSuccessActivity::class.java
                    ).putExtra(
                        Keys.Intent_Constants.CHECKOUT_MESSAGE,
                        checkoutResponse.resultMessage
                    )
                )
                finish()
            })
        }



        binding.onlinePaymentLy.setOnClickListener {
            binding.onlinePaymentRb.isChecked = true
            binding.cashRb.isChecked = false
            binding.fromWalletRb.isChecked = false
            checkoutRequest.checkoutOrder!!.paymentWay = 1
        }
        binding.cashLy.setOnClickListener {
            binding.onlinePaymentRb.isChecked = false
            binding.cashRb.isChecked = true
            binding.fromWalletRb.isChecked = false
            checkoutRequest.checkoutOrder!!.paymentWay = 1
        }
        binding.fromWalletLy.setOnClickListener {
            binding.onlinePaymentRb.isChecked = false
            binding.cashRb.isChecked = false
            binding.fromWalletRb.isChecked = true
            checkoutRequest.checkoutOrder!!.paymentWay = 1
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PAYMENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                checkoutViewModel.checkPaymentOnline(
                    checkoutResponse.code!!,
                    onResult = { paymentResponse ->
                        if (paymentResponse.isSuccess!!) {
                            startActivity(
                                Intent(
                                    this,
                                    CheckoutSuccessActivity::class.java
                                ).putExtra(
                                    Keys.Intent_Constants.CHECKOUT_MESSAGE,
                                    checkoutResponse.resultMessage
                                )
                            )
                        } else
                            PaymentResponseDialog(this, paymentResponse.message!!, onOkBtnClick = {
                                startActivity(Intent(this, MainActivity::class.java))
                                finishAffinity()
                            }).show()
                    })
            }
        }
    }
}