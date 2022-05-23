package com.upclicks.ffc.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityProductDetailsBinding
import com.upclicks.ffc.ui.cart.viewmodel.CartViewModel
import com.upclicks.ffc.ui.general.slider.adapter.CustomSliderPagerAdapter
import com.upclicks.ffc.ui.general.slider.model.Slider
import com.upclicks.ffc.ui.products.adapter.WalletAdapter
import com.upclicks.ffc.ui.products.model.ProductDetails
import com.upclicks.ffc.ui.products.model.Wallet
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast

class ProductDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailsBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    var productId = ""
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
                if (productDetails.isOutOfStock!!)
                    binding.addToCartBtn.text = getString(R.string.out_of_stock)

                setUpSlider(productDetails.mediaFiles!!)
            } else {
                finish()
            }
        })
        cartViewModel.observeCartActionResponse.observe(this, Observer { cartActionResponse->
            shoMsg(cartActionResponse.message!!,MotionToast.TOAST_SUCCESS)
        })
    }

    private fun setUpPageActions() {
        binding.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.addToCartBtn.setOnClickListener {
            if (productDetails.quantity == 0)
                cartViewModel.addProductToCart(
                    productDetails.id!!,
                    productDetails.currentPrice!!,
                    binding.quantity.value
                )
            else
                cartViewModel.updateProductQuantity(
                    productDetails.id!!,
                    binding.quantity.value
                )
        }
    }

    // set up slider
    private fun setUpSlider(slides: List<String>) {
        var sliderList = ArrayList<Slider>()
        var slider = Slider()
        slides.forEach { image ->
            slider.imagePath = image
            sliderList.add(slider)
            sliderList.add(slider)
            sliderList.add(slider)
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