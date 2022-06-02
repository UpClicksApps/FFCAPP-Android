package com.upclicks.ffc.ui.orders

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityOrderDetailsBinding
import com.upclicks.ffc.ui.orders.viewmodel.OrderViewModel
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.adapter.OrderDetailsProductAdapter
import www.sanju.motiontoast.MotionToast

class OrderDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityOrderDetailsBinding

    private val orderViewModel: OrderViewModel by viewModels()

    var productsList = ArrayList<OrderDetails.OrderDetailsProduct>()
    lateinit var productAdapter: OrderDetailsProductAdapter

    var orderId = ""
    var notifyId: String = ""

    override fun getLayoutResourceId(): View {
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpPageActions()
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
        if (!intent.getStringExtra(Keys.Intent_Constants.NOTIFY_ID).isNullOrEmpty())
            notifyId = intent.getStringExtra(Keys.Intent_Constants.NOTIFY_ID)!!
    }

    private fun setUpObserver() {
        orderViewModel.getOrder(orderId, notifyId)
        orderViewModel.observeOrderDetails.observe(this, Observer { orderDetails ->
            binding.toolbar.titleTv.text = "#" + orderDetails.code
            binding.orderDetails = orderDetails
            if (!orderDetails.orderProducts.isNullOrEmpty()) {
                productsList.clear()
                productsList.addAll(orderDetails.orderProducts!!)
                productAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setUpToolbar() {
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.deliveryInformationTV.setOnClickListener {
            if (binding.deliveryInformationExpand.isExpanded) {
                binding.deliveryInformationExpand.collapse()
                binding.deliveryInformationArrowIv.rotation = 0f
            } else {
                binding.deliveryInformationExpand.expand()
                binding.deliveryInformationArrowIv.rotation = 180f
            }
        }
        binding.productsTv.setOnClickListener {
            if (binding.productsExpand.isExpanded) {
                binding.productsExpand.collapse()
                binding.productsArrowIv.rotation = 0f
            } else {
                binding.productsExpand.expand()
                binding.productsArrowIv.rotation = 180f
            }
        }
    }

    private fun setUpUiList() {
        productAdapter = OrderDetailsProductAdapter(this, productsList, onItemClicked = {
            startActivity(
                Intent(
                    this,
                    ProductDetailsActivity::class.java
                ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].productName)
            )
        })
        binding.productsRv.adapter = productAdapter
        binding.productsRv.layoutManager = LinearLayoutManager(this)
    }
}