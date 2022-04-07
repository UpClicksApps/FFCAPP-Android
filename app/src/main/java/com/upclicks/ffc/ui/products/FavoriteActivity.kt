package com.upclicks.ffc.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityProductsListBinding
import com.upclicks.ffc.ui.products.adapter.FavoriteAdapter
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product

class FavoriteActivity  : BaseActivity() {
    lateinit var binding: ActivityFavoriteBinding

    var productsList = ArrayList<Product>()
    lateinit var favoriteAdapter : FavoriteAdapter


    override fun getLayoutResourceId(): View {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
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
        favoriteAdapter = FavoriteAdapter(this,productsList, onItemClicked = {})
        binding.recycler.adapter = favoriteAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}