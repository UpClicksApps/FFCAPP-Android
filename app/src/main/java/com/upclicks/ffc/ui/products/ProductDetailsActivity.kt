package com.upclicks.ffc.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityProductDetailsBinding
import com.upclicks.ffc.ui.general.slider.adapter.CustomSliderPagerAdapter
import com.upclicks.ffc.ui.general.slider.model.Slider
import com.upclicks.ffc.ui.products.adapter.WalletAdapter
import com.upclicks.ffc.ui.products.model.Wallet

class ProductDetailsActivity : BaseActivity() {
    lateinit var binding: ActivityProductDetailsBinding
    override fun getLayoutResourceId(): View {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpPageActions()
        setUpSlider(ArrayList())
    }

    private fun setUpPageActions() {
        binding.backIv.setOnClickListener {
            onBackPressed()
        }
        binding.addToCartBtn.setOnClickListener {

        }
    }

    // set up slider
    private fun setUpSlider(slides: ArrayList<String>) {
        slides.add("https://images.everydayhealth.com/images/ordinary-fruits-with-amazing-health-benefits-05-1440x810.jpg")
        slides.add("https://media.istockphoto.com/photos/assortment-of-fruits-picture-id173255460?b=1&k=20&m=173255460&s=170667a&w=0&h=tW4RbC7PSzob-cPwRtrZUIcanVE5p5voBf_195KsD2o=")
        slides.add("https://images.unsplash.com/photo-1619566636858-adf3ef46400b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8NHx8fGVufDB8fHx8&w=1000&q=80")
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