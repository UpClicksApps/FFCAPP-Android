package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.FragmentHomeBinding
import com.upclicks.ffc.helpers.CustomRecyclerViewHelper
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.main.adapters.HomeCategoryAdapter
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.ProductsListActivity
import com.upclicks.ffc.ui.cart.ShoppingCartActivity
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.products.adapter.HomeProductAdapter
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.HomeProduct
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import q.rorbin.badgeview.QBadgeView
import www.sanju.motiontoast.MotionToast

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()


    var categoriesList = ArrayList<Category>()
    lateinit var categoryAdapter: HomeCategoryAdapter

    var productsList = ArrayList<Product>()
    lateinit var productAdapter: ProductGridAdapter

    var homeCategoriesList = ArrayList<HomeProduct>()
    lateinit var homeProductAdapter: HomeProductAdapter

    override fun setUpLayout() {
        binding = FragmentHomeBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpPageActions()
        setUpCategoryUiList()
        setUpProductUiList()
        setUpHomeCategoriesUiList()
        setUpObservers()
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this
        setUpCartBadgeCount();
    }

    private fun setUpCartBadgeCount() {
        try {
            if (sessionHelper.cartCount > 0)
                if (sessionHelper.isEnglish(requireContext()))
                    QBadgeView(requireContext()).bindTarget(binding.cartIv)
                        .setBadgeNumber(sessionHelper.cartCount!!)
                        .setBadgePadding(2f, true).badgeGravity =
                        Gravity.END or Gravity.TOP
                else QBadgeView(requireContext()).bindTarget(binding.cartIv)
                    .setBadgeNumber(sessionHelper.cartCount!!)
                    .setBadgePadding(2f, true).badgeGravity =
                    Gravity.START or Gravity.TOP
        } catch (e: Exception) {
            Log.e("Error in setBadgeNumber", "error")
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        callProducts()
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
        productViewModel.observeHomeCategoriesList.observe(this, Observer { homeCategories ->
            if (!homeCategories.isNullOrEmpty()) {
                homeCategoriesList.clear()
                homeCategoriesList.addAll(homeCategories)
                homeProductAdapter.notifyDataSetChanged()
                binding.homeCategoriesRv.visibility = View.VISIBLE
                binding.emptyHomeCategoriesTv.visibility = View.GONE
            } else {
                binding.homeCategoriesRv.visibility = View.GONE
                binding.emptyHomeCategoriesTv.visibility = View.VISIBLE
            }
        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
            setUpCartBadgeCount()
        })
    }

    private fun setUpPageActions() {
        binding.cartIv.setOnClickListener {
            if (sessionHelper.cartCount > 0)
                startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))

        }
    }

    private fun setUpCategoryUiList() {
        categoryAdapter = HomeCategoryAdapter(requireContext(), categoriesList, onItemClicked = {
            startActivity(
                Intent(
                    requireContext(),
                    ProductsListActivity::class.java
                ).putExtra(Keys.Intent_Constants.CATEGORY_NAME, categoriesList[it].name)
                    .putExtra(Keys.Intent_Constants.CATEGORY_ID, categoriesList[it].id)
            )
        })
        binding.categoryRv.adapter = categoryAdapter
        CustomRecyclerViewHelper.addZoomRecyclerLayoutHorizontal(
            requireContext(),
            binding.categoryRv
        )
    }

    private fun setUpProductUiList() {
        productAdapter =
            ProductGridAdapter(requireContext(), productsList, onFavoriteClicked = { position ->
                productViewModel.assign(productsList[position].id!!, onAddToFavorite = { message ->
                    callProducts()
                    shoMsg(message, MotionToast.TOAST_SUCCESS)
                })
            }, onCartClicked = {
                cartViewModel.addProductToCart(productsList[it].id!!,productsList[it].currentPrice!!)
            }, onItemClicked = {
                startActivity(
                    Intent(
                        requireContext(),
                        ProductDetailsActivity::class.java
                    ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].id)
                )
            })
        binding.topSaleRv.adapter = productAdapter
        binding.topSaleRv.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    private fun callProducts() {
        productViewModel.getTopSales()
        productViewModel.getHomeCategories()
    }

    private fun setUpHomeCategoriesUiList() {
        homeProductAdapter = HomeProductAdapter(
            requireContext(),
            homeCategoriesList,
            onFavoriteClicked = { product ->
                productViewModel.assign(product.id!!, onAddToFavorite = { message ->
                    callProducts()
                    shoMsg(message, MotionToast.TOAST_SUCCESS)
                })
            }, onCartClicked = { product ->
                cartViewModel.addProductToCart(product.id!!,product.currentPrice!!)
            },
            onItemClicked = {
            })
        binding.homeCategoriesRv.adapter = homeProductAdapter
        binding.homeCategoriesRv.layoutManager = LinearLayoutManager(requireContext())
    }
}