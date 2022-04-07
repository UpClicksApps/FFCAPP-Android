package com.upclicks.ffc.ui.products

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityProductsListBinding
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product

class ProductsListActivity : BaseActivity() {
    lateinit var binding: ActivityProductsListBinding

    var productsList = ArrayList<Product>()
    lateinit var productAdapter : ProductGridAdapter

    override fun getLayoutResourceId(): View {
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpUiList()
    }
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.favorites)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpUiList() {
        productAdapter = ProductGridAdapter(this,productsList, onItemClicked = {
            startActivity(Intent(this,ProductDetailsActivity::class.java))
        })
        binding.recycler.adapter = productAdapter
        binding.recycler.layoutManager = GridLayoutManager(this,2)
    }
}
