package com.upclicks.ffc.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityMainBinding
import com.upclicks.ffc.databinding.ActivitySignUpFirstBinding
import com.upclicks.ffc.helpers.CustomColorHelper
import com.upclicks.ffc.ui.main.adapters.PagerAdapter
import com.upclicks.ffc.ui.main.fragments.CategoriesFragment
import com.upclicks.ffc.ui.main.fragments.HomeFragment
import com.upclicks.ffc.ui.main.fragments.ProfileFragment
import com.upclicks.ffc.ui.main.fragments.SearchFragment
import io.ak1.OnBubbleClickListener
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    override fun getLayoutResourceId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setUpViewPager()
        return binding.root
    }
    private fun setUpViewPager() {
        binding.viewPager.adapter = PagerAdapter(supportFragmentManager)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                binding.bubbleTabBar.setSelected(position, false)
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        binding.bubbleTabBar.addBubbleListener { id ->
            when(id) {
                R.id.home_nav -> {
                    binding.viewPager.currentItem = 0
                }
                R.id.categories_nav -> {
                    binding.viewPager.currentItem = 1
                }
                R.id.search_nav -> {
                    binding.viewPager.currentItem = 2
                }
                R.id.profile_nav -> {
                    binding.viewPager.currentItem = 3
                }
                else -> {
                    binding.viewPager.currentItem = 0
                }
            }
        }
    }
}