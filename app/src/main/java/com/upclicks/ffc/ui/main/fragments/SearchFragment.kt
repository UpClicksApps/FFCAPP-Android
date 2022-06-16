package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.adroitandroid.chipcloud.ChipListener
import com.adroitandroid.chipcloud.FlowLayout
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseFragment
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.FragmentSearchBinding
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.dialog.ConfirmDialog
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.adapter.ProductGridAdapter
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.ProductRequest
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast
import java.util.*

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    lateinit var binding: FragmentSearchBinding

    private val cartViewModel: CartViewModel by viewModels()

    var productsList = ArrayList<Product>()
    lateinit var productAdapter: ProductGridAdapter
    private val productViewModel: ProductViewModel by viewModels()
    private var productRequest = ProductRequest()
    var lastSearchedList = ArrayList<String>()
    var searchText = ""

    override fun setUpLayout() {
        binding = FragmentSearchBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageAction()
        setUpUiList()
        setUpObservers()
        setUpHistory()
    }

    private fun setUpPageAction() {
        binding.searchInput.setOnTextTyping(object : BaseInput.TypingCallback {
            override fun onTyping(text: String) {
                searchText = text
                if (searchText.isNullOrEmpty()) {
                    productsList.clear()
                    productAdapter.notifyDataSetChanged()
                    binding.recycler.visibility = View.GONE
                    binding.emptyTopSalesTv.visibility = View.GONE
                    binding.clearIv.visibility = View.GONE
                } else {
                    binding.recycler.visibility = View.VISIBLE
                    binding.emptyTopSalesTv.visibility = View.VISIBLE
                    binding.clearIv.visibility = View.VISIBLE
                }
            }
        })
        binding.searchIv.setOnClickListener {
            if (sessionHelper.lastSearched.trim().isEmpty()) {
                sessionHelper.saveLastSearched(binding.searchInput.text.toString().trim())
            } else sessionHelper.saveLastSearched(
                sessionHelper.lastSearched.trim() + "!!!" + binding.searchInput.text.toString()
                    .trim()
            )
            binding.chipCloud.removeAllViews()
            setUpHistory()
            if (binding.searchInput.isValid) {
                productRequest.productName = searchText
                productViewModel.getProducts(productRequest)
            }
        }
        binding.clearIv.setOnClickListener {
            binding.searchInput.setText("")
            productsList.clear()
            productAdapter.notifyDataSetChanged()
            binding.recycler.visibility = View.GONE
            binding.emptyTopSalesTv.visibility = View.GONE
        }
        binding.clearAllTV.setOnClickListener {
            ConfirmDialog(requireContext(),
                getString(R.string.history),
                getString(R.string.clear_all_history_are_you_sure),
                onYesBtnClick = {
                    sessionHelper.removeLastSearched()
                    binding.chipCloud.removeAllViews()
                    setUpHistory()
                    binding.recycler.visibility = View.GONE
                    binding.emptyTopSalesTv.visibility = View.GONE
                },
                onNoBtnClick = {

                }).show()
        }
    }

    private fun setUpHistory() {
        if (sessionHelper.isEnglish(requireContext())) {
            binding.chipCloud.setGravity(FlowLayout.Gravity.LEFT)
        } else {
            binding.chipCloud.setGravity(FlowLayout.Gravity.RIGHT)
        }
        if (!TextUtils.isEmpty(sessionHelper.lastSearched)) {
            sessionHelper.lastSearched.split("!!!").forEach { chip ->
                binding.chipCloud.addChip(chip)
            }
            binding.historyLy.visibility = View.VISIBLE
            binding.chipCloud.visibility = View.VISIBLE
            lastSearchedList.clear()
            lastSearchedList.addAll(sessionHelper.lastSearched.split("!!!"))
        } else {
            binding.historyLy.visibility = View.GONE
            binding.chipCloud.visibility = View.GONE
        }
        binding.chipCloud.setChipListener(object : ChipListener {
            override fun chipSelected(index: Int) {
                productRequest.productName = lastSearchedList[index]
                productViewModel.getProducts(productRequest)
            }

            override fun chipDeselected(index: Int) {
                binding.searchInput.setText("")
                productsList.clear()
                productAdapter.notifyDataSetChanged()
                binding.recycler.visibility = View.GONE
                binding.emptyTopSalesTv.visibility = View.GONE
            }
        })
    }

    private fun setUpObservers() {
        productViewModel.observeProductList.observe(this, Observer { productResponse ->
            if (productResponse != null && !productResponse.products.isNullOrEmpty()) {
                productsList.clear()
                productsList.addAll(productResponse.products!!)
                productAdapter.notifyDataSetChanged()
                binding.recycler.visibility = View.VISIBLE
                binding.emptyTopSalesTv.visibility = View.GONE
            } else {
                binding.recycler.visibility = View.GONE
                binding.emptyTopSalesTv.visibility = View.VISIBLE
            }
        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
        })
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.search)
        binding.toolbar.backIv.visibility = View.GONE
    }


    private fun setUpUiList() {
        productAdapter = ProductGridAdapter(requireContext(), productsList,
            onFavoriteClicked = { position ->
                productViewModel.assign(productsList[position].id!!, onAddToFavorite = { message ->
//                    callProducts()
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
                        requireContext(),
                        ProductDetailsActivity::class.java
                    ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productsList[it].id)
                )
            })
        binding.recycler.adapter = productAdapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}