package com.upclicks.ffc.ui.orders

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityMyOrdersBinding
import com.upclicks.ffc.ui.orders.model.Order
import com.upclicks.ffc.ui.products.OrderDetailsActivity
import com.upclicks.ffc.ui.products.adapter.OrderAdapter
import com.upclicks.ffc.ui.products.adapter.OrderStatusSelectionAdapter

class MyOrdersActivity  : BaseActivity() {
    lateinit var binding: ActivityMyOrdersBinding

    private var orderList = ArrayList<Order>()
    private lateinit var orderAdapter : OrderAdapter


    var orderStatusSelectionList = ArrayList<String>()
    lateinit var orderStatusSelectionAdapter : OrderStatusSelectionAdapter


    override fun getLayoutResourceId(): View {
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpStatusUiList()
        setUpOrdersUiList()
    }
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.my_orders)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpStatusUiList() {
        orderStatusSelectionList.add("Request")
        orderStatusSelectionList.add("Confirmed")
        orderStatusSelectionList.add("On the Way")
        orderStatusSelectionList.add("Delivered")
        orderStatusSelectionList.add("Returned")
        orderStatusSelectionAdapter = OrderStatusSelectionAdapter(this,orderStatusSelectionList, onItemClicked = {})
        binding.statusRv.adapter = orderStatusSelectionAdapter
        binding.statusRv.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
    }
    private fun setUpOrdersUiList() {
        orderAdapter = OrderAdapter(this,orderList, onItemClicked = {
            startActivity(Intent(this, OrderDetailsActivity::class.java))
        })
        binding.ordersRv.adapter = orderAdapter
        binding.ordersRv.layoutManager = LinearLayoutManager(this)
    }
}