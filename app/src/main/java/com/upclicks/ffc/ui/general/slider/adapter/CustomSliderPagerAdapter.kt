package com.upclicks.ffc.ui.general.slider.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.upclicks.ffc.ui.general.slider.fragment.MySliderFragment
import com.upclicks.ffc.ui.general.slider.model.Slider

class CustomSliderPagerAdapter(
    fm: FragmentManager,
    private val dataList: List<Slider>,
    private val providerSliders: List<String>
) : FragmentStatePagerAdapter(fm) {
    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        if (dataList != null && dataList.isNotEmpty()) {
            bundle.putString("img", dataList[position].imagePath)
            bundle.putString("url", dataList[position].url)
        }
        val mySliderFragment = MySliderFragment()
        mySliderFragment.arguments = bundle
        return mySliderFragment
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        if (dataList != null)
            return dataList.size
        return 0
    }


}
