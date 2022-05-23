package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.databinding.FragmentSearchBinding
import com.upclicks.ffc.ui.cart.ShoppingCartActivity
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    lateinit var binding: FragmentSearchBinding


    var productsList = ArrayList<Product>()
    lateinit var productAdapter : ProductGridAdapter

    override fun setUpLayout() {
        binding = FragmentSearchBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpUiList()
    }
    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.search)
        binding.toolbar.cartIv.visibility = View.VISIBLE
        binding.toolbar.backIv.visibility = View.GONE
        binding.toolbar.cartIv.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }
    }


    private fun setUpUiList() {
        productAdapter = ProductGridAdapter(requireContext(),productsList, onItemClicked = {})
        binding.recycler.adapter = productAdapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),2)
    }
}