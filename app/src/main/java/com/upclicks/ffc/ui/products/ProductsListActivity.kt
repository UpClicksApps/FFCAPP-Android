package com.upclicks.ffc.ui.products

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityProductsListBinding
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.dialog.FilterDialog
import com.upclicks.ffc.ui.products.dialog.SortDialog
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.ProductRequest
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_login_by_email.*
import www.sanju.motiontoast.MotionToast

class ProductsListActivity : BaseActivity() {
    lateinit var binding: ActivityProductsListBinding

    var productsList = ArrayList<Product>()
    lateinit var productAdapter: ProductGridAdapter
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private var productRequest = ProductRequest()
    private var categoryId = String
    override fun getLayoutResourceId(): View {
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (intent.getStringExtra(Keys.Intent_Constants.CATEGORY_ID) != null)
            productViewModel.getProducts(productRequest)
    }

    private fun initPage() {
        setUpIntent()
        setUpToolbar()
        setUpPAgeActions()
        setUpUiList()
        setUpObservers()
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpPAgeActions() {
        binding.filterBtn.setOnClickListener {
            FilterDialog(
                this,
                productRequest.categoryId!!,
                productViewModel,
                onApplyBtnClicked = { categoryName, productRequest ->
                    if (!productRequest.categoryId.isNullOrEmpty())
                        this.productRequest.categoryId = productRequest.categoryId
                    binding.toolbar.titleTv.text = categoryName
                    productViewModel.getProducts(productRequest)
                }).show()
        }
        binding.sortBtn.setOnClickListener {
            SortDialog(
                this,
                onApplyBtnClicked = { sortByKey ->
                    productRequest.sortProductBy = sortByKey
                    productViewModel.getProducts(productRequest)
                }).show()
        }
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.CATEGORY_ID) != null) {
            productRequest.categoryId =
                intent.getStringExtra(Keys.Intent_Constants.CATEGORY_ID)!!
        } else {
            shoMsg(getString(R.string.category_id_is_empty), MotionToast.TOAST_ERROR)
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text =
            intent.getStringExtra(Keys.Intent_Constants.CATEGORY_NAME)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpUiList() {
        productAdapter = ProductGridAdapter(this, productsList,
            onFavoriteClicked = { position ->
                productViewModel.assign(
                    productsList[position].id!!,
                    onAddToFavorite = { message ->
                        productViewModel.getProducts(productRequest)
                        shoMsg(message, MotionToast.TOAST_SUCCESS)
                    })
            }, onCartClicked = {
                cartViewModel.addProductToCart(
                    productsList[it].id!!,
                    productsList[it].currentPrice!!
                )
            }, onItemClicked = {
                startActivity(
                    Intent(
                        this,
                        ProductDetailsActivity::class.java
                    ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].id)
                )
            })
        binding.recycler.adapter = productAdapter
        binding.recycler.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setUpObservers() {
        productViewModel.observeProductList.observe(this, Observer { productResponse ->
            if (productResponse != null && !productResponse.products.isNullOrEmpty()) {
                productsList.clear()
                productsList.addAll(productResponse.products!!)
                productAdapter.notifyDataSetChanged()
                binding.recycler.visibility = View.VISIBLE
                binding.emptyTopSalesTv.visibility = View.GONE
            }else{
                binding.recycler.visibility = View.GONE
                binding.emptyTopSalesTv.visibility = View.VISIBLE
            }



        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
        })
    }
}
