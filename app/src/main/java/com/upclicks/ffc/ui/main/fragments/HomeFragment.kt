package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.FragmentHomeBinding
import com.upclicks.ffc.helpers.CustomRecyclerViewHelper
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.main.adapters.HomeCategoryAdapter
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.ProductsListActivity
import com.upclicks.ffc.ui.products.ShoppingCartActivity
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private val productViewModel: ProductViewModel by viewModels()


    var categoriesList = ArrayList<Category>()
    lateinit var categoryAdapter: HomeCategoryAdapter

    var productsList = ArrayList<Product>()
    lateinit var productAdapter: ProductGridAdapter

    override fun setUpLayout() {
        binding = FragmentHomeBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpPageActions()
        setUpCategoryUiList()
        setUpProductUiList()
        setUpObservers()
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        productViewModel.getTopSales()
    }

    private fun setUpObservers() {
        productViewModel.getCategories { categories ->
            if (!categories.isNullOrEmpty()) {
                categoriesList.clear()
                categoriesList.addAll(categories)
                categoryAdapter.notifyDataSetChanged()
                binding.categoryRv.visibility = View.VISIBLE
                binding.emptyCategoriesTv.visibility = View.GONE
            } else {
                binding.categoryRv.visibility = View.GONE
                binding.emptyCategoriesTv.visibility = View.VISIBLE
            }
        }
        productViewModel.observeTopSalesList.observe(this, Observer { products ->
            if (!products.isNullOrEmpty()) {
                productsList.clear()
                productsList.addAll(products)
                productAdapter.notifyDataSetChanged()
                binding.topSaleRv.visibility = View.VISIBLE
                binding.emptyTopSalesTv.visibility = View.GONE
            } else {
                binding.topSaleRv.visibility = View.GONE
                binding.emptyTopSalesTv.visibility = View.VISIBLE
            }
        })
    }

    private fun setUpPageActions() {
        binding.cartIv.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }
    }

    private fun setUpCategoryUiList() {
        categoryAdapter = HomeCategoryAdapter(requireContext(), categoriesList, onItemClicked = {
            startActivity(Intent(requireContext(), ProductsListActivity::class.java))
        })
        binding.categoryRv.adapter = categoryAdapter
        CustomRecyclerViewHelper.addZoomRecyclerLayoutHorizontal(
            requireContext(),
            binding.categoryRv
        )
    }

    private fun setUpProductUiList() {
        productAdapter = ProductGridAdapter(requireContext(), productsList, onItemClicked = {
            startActivity(Intent(requireContext(), ProductDetailsActivity::class.java).putExtra(Keys.Intent_Constants.PRODUCT_ID,productsList[it].id))
        })
        binding.topSaleRv.adapter = productAdapter
        binding.topSaleRv.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}