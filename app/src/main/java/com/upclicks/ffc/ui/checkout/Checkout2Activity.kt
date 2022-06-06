package com.upclicks.ffc.ui.checkout

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityCheckout2Binding
import com.upclicks.ffc.ui.checkout.adapter.DeliveryTimeAdapter
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.checkout.viewmodel.CheckoutViewModel
import www.sanju.motiontoast.MotionToast

class Checkout2Activity : BaseActivity() {
    lateinit var binding: ActivityCheckout2Binding

    var checkoutRequest = CheckoutRequest()
    private val checkoutViewModel: CheckoutViewModel by viewModels()

    var todayTimesList = ArrayList<String>()
    lateinit var todayAdapter: DeliveryTimeAdapter

    var tomorrowTimesList = ArrayList<String>()
    lateinit var tomorrowAdapter: DeliveryTimeAdapter

    override fun getLayoutResourceId(): View {
        binding = ActivityCheckout2Binding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpPageActions()
        setUpTodayTimesList()
        setUpTomorrowTimesList()
        setUpObservers()
    }

    private fun setUpObservers() {
        checkoutViewModel.getAvailableDeliveryTimes { deliveryTimeResponse ->
            if (deliveryTimeResponse != null) {
                if (!deliveryTimeResponse.today.isNullOrEmpty()) {
                    todayTimesList.clear()
                    todayTimesList.addAll(deliveryTimeResponse.today!!)
                    todayAdapter.notifyDataSetChanged()
                }
                if (!deliveryTimeResponse.tomorrow.isNullOrEmpty()) {
                    tomorrowTimesList.clear()
                    tomorrowTimesList.addAll(deliveryTimeResponse.tomorrow!!)
                    tomorrowAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.CHECKOUT) != null)
            checkoutRequest = Gson().fromJson(
                intent.getStringExtra(Keys.Intent_Constants.CHECKOUT),
                CheckoutRequest::class.java
            )
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.delivery_time)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.nextBtn.setOnClickListener {
            if (!checkoutRequest.checkoutOrder!!.deliveryTime.isNullOrEmpty())
                startActivity(
                    Intent(
                        this,
                        Checkout3Activity::class.java
                    ).putExtra(Keys.Intent_Constants.CHECKOUT, Gson().toJson(checkoutRequest))
                )
            else{
                shoMsg(getString(R.string.delivery_time_must_be_selected),MotionToast.TOAST_ERROR)
            }
        }
    }

    private fun setUpTodayTimesList() {
        todayAdapter = DeliveryTimeAdapter(this, todayTimesList, onItemClicked = { dateTime ->
            checkoutRequest.checkoutOrder!!.deliveryTime = dateTime
            tomorrowAdapter.resetUi()
            tomorrowAdapter.notifyDataSetChanged()
        }, onItemRemoved = { isRemoved ->
            if (isRemoved) {
                checkoutRequest.checkoutOrder!!.deliveryTime = ""
            }
        })
        binding.todayRv.adapter = todayAdapter
        binding.todayRv.layoutManager = GridLayoutManager(this, 3)
    }

    private fun setUpTomorrowTimesList() {
        tomorrowAdapter = DeliveryTimeAdapter(this, tomorrowTimesList, onItemClicked = { dateTime ->
            checkoutRequest.checkoutOrder!!.deliveryTime = dateTime
            todayAdapter.resetUi()
            todayAdapter.notifyDataSetChanged()
        }, onItemRemoved = { isRemoved ->
            if (isRemoved) {
                checkoutRequest.checkoutOrder!!.deliveryTime = ""
            }
        })
        binding.tomorrowRv.adapter = tomorrowAdapter
        binding.tomorrowRv.layoutManager = GridLayoutManager(this, 3)
    }

}