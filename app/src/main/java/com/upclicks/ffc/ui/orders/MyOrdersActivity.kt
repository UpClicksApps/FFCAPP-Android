package com.upclicks.ffc.ui.orders

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.commons.OrderStatus
import com.upclicks.ffc.databinding.ActivityMyOrdersBinding
import com.upclicks.ffc.ui.general.dialog.ConfirmDialog
import com.upclicks.ffc.ui.orders.adapter.OrderAdapter
import com.upclicks.ffc.ui.orders.adapter.OrderStatusSelectionAdapter
import com.upclicks.ffc.ui.orders.model.Order
import com.upclicks.ffc.ui.orders.model.OrderStatusModel
import com.upclicks.ffc.ui.orders.viewmodel.OrderViewModel
import www.sanju.motiontoast.MotionToast

class MyOrdersActivity : BaseActivity() {
    lateinit var binding: ActivityMyOrdersBinding

    private var orderList = ArrayList<Order>()
    private lateinit var orderAdapter: OrderAdapter

    var orderStatusSelectionList = ArrayList<OrderStatusModel>()
    lateinit var orderStatusSelectionAdapter: OrderStatusSelectionAdapter

    private val orderViewModel: OrderViewModel by viewModels()

    //load on scroll properties
    lateinit var linearLayoutManager: LinearLayoutManager
    var onScrollChangeListener: NestedScrollView.OnScrollChangeListener? = null
    var skip = 0
    var take: Int = 10
    var stopScroll: Boolean = false

    var orderStatus = 0

    override fun getLayoutResourceId(): View {
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageAction()
        setUpStatusUiList()
        setUpOrdersUiList()
        setUpObservers()
        binding.viewModel = orderViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpPageAction() {
        setUpLoadOnScrollListener()
        binding.swipeRefresh.setOnRefreshListener {
            resetList()
            orderViewModel.getMyOrders(orderStatus, skip, take)
        }
    }

    private fun resetList() {
        skip = 0
        orderList.clear()
        orderAdapter.notifyDataSetChanged()
    }

    private fun setUpObservers() {
        orderViewModel.getMyOrders(orderStatus, skip, take)
        orderViewModel.observeOrdersList.observe(this, Observer { orders ->
            binding.swipeRefresh.isRefreshing = false
            if (!orders.isNullOrEmpty()) {
                binding.emptyProductsTv.visibility = View.GONE
                binding.ordersRv.visibility = View.VISIBLE
                orderList.addAll(orders)
                stopScrollIfDataEnded(binding.nestedScrollView, orders.size)
                orderAdapter.notifyDataSetChanged()
            } else {
                stopScroll = true
                if (orderList.isNullOrEmpty()) {
                    binding.emptyProductsTv.visibility = View.VISIBLE
                    binding.ordersRv.visibility = View.GONE
                }
            }
        })
    }

    // Set Up Load On Scroll Listener
    private fun setUpLoadOnScrollListener() {
        onScrollChangeListener =
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        if (!stopScroll) {
                            orderViewModel.getMyOrders(orderStatus, skip, take)
                        }
                    }
                }
            }
        binding.nestedScrollView.setOnScrollChangeListener(onScrollChangeListener)
    }

    // Stop Scroll If Data Ended
    private fun stopScrollIfDataEnded(scrollView: NestedScrollView, size: Int) {
        stopScroll = size < take
        skip += take
        scrollView.setOnScrollChangeListener(onScrollChangeListener)
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.my_orders)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpStatusUiList() {
        orderStatusSelectionList.add(
            OrderStatusModel(
                OrderStatus.Requested.value,
                getString(R.string.requested)
            )
        )
        orderStatusSelectionList.add(
            OrderStatusModel(
                OrderStatus.Confirmed.value,
                getString(R.string.confirmed)
            )
        )
        orderStatusSelectionList.add(
            OrderStatusModel(
                OrderStatus.OnTheWay.value,
                getString(R.string.on_the_way)
            )
        )
        orderStatusSelectionList.add(
            OrderStatusModel(
                OrderStatus.Delivered.value,
                getString(R.string.delivered)
            )
        )
        orderStatusSelectionList.add(
            OrderStatusModel(
                OrderStatus.Returned.value,
                getString(R.string.returned)
            )
        )
        orderStatusSelectionList.add(
            OrderStatusModel(
                OrderStatus.Cancelled.value,
                getString(R.string.cancelled)
            )
        )
        orderStatusSelectionAdapter = OrderStatusSelectionAdapter(
            this,
            orderStatusSelectionList,
            onItemClicked = { orderStatusModel ->
                resetList()
                orderStatus = orderStatusModel.orderStatus!!
                orderViewModel.getMyOrders(orderStatusModel.orderStatus!!, skip, take)
            })
        binding.statusRv.adapter = orderStatusSelectionAdapter
        binding.statusRv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }
    private fun setUpOrdersUiList() {
        orderAdapter = OrderAdapter(this, orderList, onCancelClicked = {
            ConfirmDialog(
                this,
                getString(R.string.my_orders),
                getString(R.string.cancel_this_order_are_you_sure),
                onYesBtnClick = {
                    orderViewModel.cancelOrder(orderList[it].id!!, onResult = {result->
                        shoMsg(result,MotionToast.TOAST_SUCCESS)
                    })
                },
                onNoBtnClick = {}).show()
        }, onDetailsClicked = {
            startActivity(
                Intent(
                    this,
                    OrderDetailsActivity::class.java
                ).putExtra(Keys.Intent_Constants.ORDER_ID, orderList[it].id)
            )
        })
        binding.ordersRv.adapter = orderAdapter
        binding.ordersRv.layoutManager = LinearLayoutManager(this)
    }
}