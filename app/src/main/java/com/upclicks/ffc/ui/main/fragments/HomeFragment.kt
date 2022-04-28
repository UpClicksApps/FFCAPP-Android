package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.databinding.FragmentHomeBinding
import com.upclicks.ffc.helpers.CustomRecyclerViewHelper
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.main.adapters.HomeCategoryAdapter
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.ProductsListActivity
import com.upclicks.ffc.ui.products.ShoppingCartActivity
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding

    var categoriesList = ArrayList<Category>()
    lateinit var categoryAdapter : HomeCategoryAdapter

    var productsList = ArrayList<Product>()
    lateinit var productAdapter : ProductGridAdapter

    override fun setUpLayout() {
        binding = FragmentHomeBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {

        setUpPageActions()
        setUpCategoryUiList()
        setUpProductUiList()
    }

    private fun setUpPageActions() {
        binding.cartIv.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }
    }

    private fun setUpCategoryUiList() {
        categoryAdapter = HomeCategoryAdapter(requireContext(),categoriesList, onItemClicked = {
            startActivity(Intent(requireContext(), ProductsListActivity::class.java))
        })
        binding.categoryRv.adapter = categoryAdapter
        CustomRecyclerViewHelper.addZoomRecyclerLayoutHorizontal(requireContext(),binding.categoryRv)
//        binding.categoryRv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
    }

    private fun setUpProductUiList() {
        productAdapter = ProductGridAdapter(requireContext(),productsList, onItemClicked = {
            startActivity(Intent(requireContext(), ProductDetailsActivity::class.java))
        })
        binding.topSaleRv.adapter = productAdapter
        binding.topSaleRv.layoutManager = GridLayoutManager(requireContext(),2)
    }
}