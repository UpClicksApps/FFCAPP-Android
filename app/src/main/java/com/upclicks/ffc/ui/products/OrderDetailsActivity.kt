package com.upclicks.ffc.ui.products

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityOrderDetailsBinding
import com.upclicks.ffc.ui.orders.viewmodel.OrderViewModel
import com.upclicks.ffc.ui.products.adapter.OrderDetailsProductAdapter
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product
import www.sanju.motiontoast.MotionToast

class OrderDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityOrderDetailsBinding

    private val orderViewModel: OrderViewModel by viewModels()

    var productsList = ArrayList<Product>()
    lateinit var productAdapter: OrderDetailsProductAdapter

    var orderId = ""

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
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.ORDER_ID) != null)
            orderId = intent.getStringExtra(Keys.Intent_Constants.ORDER_ID)!!
    }

    private fun setUpObserver() {
        orderViewModel.getOrder(orderId)
        orderViewModel.observeOrderDetails.observe(this, Observer { orderDetails ->
            binding.toolbar.titleTv.setText("#" + orderDetails.code)
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
    }


    private fun setUpUiList() {
        productAdapter = OrderDetailsProductAdapter(this, productsList, onItemClicked = {
            startActivity(
                Intent(
                    this,
                    ProductDetailsActivity::class.java
                ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].id)
            )
        })
        binding.productsRv.adapter = productAdapter
        binding.productsRv.layoutManager = GridLayoutManager(this, 2)
    }
}