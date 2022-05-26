package com.upclicks.ffc.ui.products

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.databinding.ActivityProductDetailsBinding
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.general.dialog.ConfirmDialog
import com.upclicks.ffc.ui.general.slider.adapter.CustomSliderPagerAdapter
import com.upclicks.ffc.ui.general.slider.model.Slider
import com.upclicks.ffc.ui.products.model.ProductDetails
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast

class ProductDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailsBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    var productId = ""
    var currentProductCountValue = 0
    var productDetails = ProductDetails()
    override fun getLayoutResourceId(): View {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpIntent()
        setUpPageActions()
        setUpObservers()
    }

    private fun setUpIntent() {
        if (intent.getStringExtra(Keys.Intent_Constants.PRODUCT_ID) != null)
            productId = intent.getStringExtra(Keys.Intent_Constants.PRODUCT_ID)!!
        else finish()
    }

    private fun setUpObservers() {
        productViewModel.getProductDetails(productId)
        productViewModel.observeProductDetails.observe(this, Observer { productDetails ->
            if (productDetails != null) {
                binding.product = productDetails
                this.productDetails = productDetails
                currentProductCountValue = productDetails.quantityInCart!!
                updateQuantityUi()
                if (productDetails.isOutOfStock!!)
                    binding.addToCartBtn.text = getString(R.string.out_of_stock)

                setUpSlider(productDetails.mediaFiles!!)
            } else {
                finish()
            }
        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse ->
            shoMsg(cartActionResponse.message!!, MotionToast.TOAST_SUCCESS)
            sessionHelper.saveCartCount(cartActionResponse.currentCartItemsCount)
        })
    }

    private fun setUpPageActions() {
        binding.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.addToCartBtn.setOnClickListener {
            cartViewModel.addProductToCart(
                productDetails.id!!,
                productDetails.currentPrice!!
            )
            currentProductCountValue = 1
            updateQuantityUi()
        }
        binding.quantity.setValueChangedListener { value, action ->
            if (value == 0)
                ConfirmDialog(this,
                    getString(R.string.delete),
                    getString(R.string.are_you_sure),
                    onYesBtnClick = {
                        cartViewModel.removeProductFromCart(productDetails.id!!)
                        currentProductCountValue = 0
                        updateQuantityUi()
                    },
                    onNoBtnClick = {
                        binding.quantity.value = 1
                        currentProductCountValue = 1
                    }).show()
            else {
                cartViewModel.updateProductQuantity(
                    productDetails.id!!,
                    binding.quantity.value
                )
                currentProductCountValue = value
            }
        }
        binding.toggleFavBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                productViewModel.assign(productDetails.id!!, onAddToFavorite = { message ->
                    productDetails.isOnMyWishlist = !productDetails.isOnMyWishlist!!
                    shoMsg(message, MotionToast.TOAST_SUCCESS)
                })
            }
        }
        binding.shareIv.setOnClickListener {
            if (!productDetails.shareLink.isNullOrEmpty())
                Utils.openUrl(this, productDetails.shareLink)
            else shoMsg(getString(R.string.This_product_not_has_share_link),MotionToast.TOAST_ERROR)
        }
    }

    private fun updateQuantityUi() {
        if (currentProductCountValue == 0) {
            binding.addToCartBtn.visibility = View.VISIBLE
            binding.quantity.visibility = View.GONE
        } else {
            binding.quantity.visibility = View.VISIBLE
            binding.addToCartBtn.visibility = View.GONE
        }
    }

    // set up slider
    private fun setUpSlider(slides: List<String>) {
        var sliderList = ArrayList<Slider>()
        var slider = Slider()
        slides.forEach { image ->
            slider.imagePath = image
            sliderList.add(slider)
        }
        val mySliderPagerAdapter =
            CustomSliderPagerAdapter(supportFragmentManager, sliderList, slides)
        binding.sliderPager.adapter = mySliderPagerAdapter
        binding.indicatorView.setupWithViewPager(binding.sliderPager)
        binding.sliderPager.interval = 4000
        binding.sliderPager.startAutoScroll()
    }
}