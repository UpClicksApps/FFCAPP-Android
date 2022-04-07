package com.upclicks.ffc.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.upclicks.ffc.ui.main.fragments.CategoriesFragment
import com.upclicks.ffc.ui.main.fragments.HomeFragment
import com.upclicks.ffc.ui.main.fragments.ProfileFragment
import com.upclicks.ffc.ui.main.fragments.SearchFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return CategoriesFragment()
            }
            2 -> {
                return SearchFragment()
            }
            3 -> {
                return ProfileFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Home"
            }
            1 -> {
                return "Categories"
            }
            2 -> {
                return "Search"
            }
            3 -> {
                return "Profile"
            }
        }
        return super.getPageTitle(position)
    }

}