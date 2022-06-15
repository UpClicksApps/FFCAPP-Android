package com.upclicks.ffc.ui.orders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityTrackOrderBinding
import com.upclicks.ffc.session.SessionHelper
import com.upclicks.ffc.ui.orders.adapter.OrderStatusLogAdapter
import com.upclicks.ffc.ui.orders.model.OrderStatusLog
import com.upclicks.ffc.ui.orders.viewmodel.OrderViewModel
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TrackOrderActivity : BaseActivity() {

    lateinit var binding: ActivityTrackOrderBinding
    var orderStatusLogsList = ArrayList<OrderStatusLog>()
    lateinit var orderStatusLogAdapter: OrderStatusLogAdapter
    private val orderViewModel: OrderViewModel by viewModels()
    var orderId = ""
    override fun getLayoutResourceId(): View {
        binding = ActivityTrackOrderBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpUiList()
        setUpObserver()
        binding.viewModel = orderViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.ORDER_ID) != null)
            orderId = intent.getStringExtra(Keys.Intent_Constants.ORDER_ID)!!
        else {
            shoMsg(getString(R.string.error_in_orderId), MotionToast.TOAST_ERROR)
            finish()
        }
    }

    private fun setUpObserver() {
        orderViewModel.getOrder(orderId, "")
        orderViewModel.observeOrderDetails.observe(this, Observer { orderDetails ->
            if (orderDetails != null) {
                setUpOrderDate(orderDetails.creationTime)
                if (!orderDetails.orderStatusLogs.isNullOrEmpty()) {
                    orderStatusLogsList.clear()
                    orderStatusLogsList.addAll(orderDetails.orderStatusLogs!!)
                    orderStatusLogAdapter.notifyDataSetChanged()
                    binding.recycler.visibility = View.VISIBLE
                    binding.emptyFlag.visibility = View.GONE
                } else {
                    binding.emptyFlag.visibility = View.VISIBLE
                    binding.recycler.visibility = View.GONE
                }
            }
        })
    }

    private fun setUpOrderDate(dateString: String?) {
        var dateStr = ""
        if (dateString != null) {
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val sessionHelper = SessionHelper(this)
            var date: Date? =
                null //You will get date object relative to server/client timezone wherever it is parsed
            try {
                //  Sat 10 November 2018
                date = dateFormat.parse(dateString)
                val formatter: DateFormat = SimpleDateFormat(
                    "EEE d MMM yyyy / HH:mm",
                    Locale(sessionHelper.userLanguageCode)
                ) //If you need time just put specific format for time like 'HH:mm:ss'
                dateStr = formatter.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        binding.dateTv.text = dateStr
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.track_order)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpUiList() {
        orderStatusLogAdapter = OrderStatusLogAdapter(this, orderStatusLogsList)
        binding.recycler.adapter = orderStatusLogAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}