package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.databinding.FragmentCategoriesBinding
import com.upclicks.ffc.helpers.CustomRecyclerViewHelper
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.main.adapters.CategoryAdapter
import com.upclicks.ffc.ui.products.ShoppingCartActivity
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel

class CategoriesFragment : BaseFragment(R.layout.fragment_categories) {
    lateinit var binding: FragmentCategoriesBinding
    private val productViewModel: ProductViewModel by viewModels()

    var categoriesList = ArrayList<Category>()
    lateinit var categoryAdapter: CategoryAdapter


    //load on scroll properties
    lateinit var linearLayoutManager: LinearLayoutManager
    var onScrollChangeListener: NestedScrollView.OnScrollChangeListener? = null
    var skip = 0
    var take: Int = 10
    var stopScroll: Boolean = false

    override fun setUpLayout() {
        binding = FragmentCategoriesBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpUiList()
        setUpPageActions()
        observeCategories()
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpPageActions() {
        setUpLoadOnScrollListener()
        binding.swipeRefresh.setOnRefreshListener {
            skip = 0
            categoriesList.clear()
            categoryAdapter.notifyDataSetChanged()
            productViewModel.getAllCategories(skip, take)
        }
    }
    private fun observeCategories() {
        productViewModel.getAllCategories(skip, take)
        productViewModel.observeAllCategoriesList.observe(this, Observer { categories ->
            binding.swipeRefresh.isRefreshing = false
            if (!categories.isNullOrEmpty()) {
                binding.categoriesRv.visibility = View.VISIBLE
                binding.emptyCategoriesTv.visibility = View.GONE
                categoriesList.clear()
                categoriesList.addAll(categories)
                stopScrollIfDataEnded(binding.nestedScrollView, categories.size)
                categoryAdapter.notifyDataSetChanged()
            } else {
                binding.categoriesRv.visibility = View.VISIBLE
                binding.emptyCategoriesTv.visibility = View.GONE
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
                            productViewModel.getAllCategories(skip, take)
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
    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.categories)
        binding.toolbar.cartIv.visibility = View.VISIBLE
        binding.toolbar.backIv.visibility = View.GONE
        binding.toolbar.cartIv.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }
    }

    private fun setUpUiList() {
        categoryAdapter = CategoryAdapter(requireContext(), categoriesList, onItemClicked = {})
        binding.categoriesRv.adapter = categoryAdapter
        binding.categoriesRv.layoutManager = LinearLayoutManager(requireContext())
    }
}