package com.upclicks.ffc.ui.general.slider.fragment

import android.text.TextUtils
import com.squareup.picasso.Picasso
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.FragmentMySliderBinding
import com.upclicks.ffc.commons.Utils.Companion.openUrl
import com.upclicks.ffc.architecture.BaseFragment

class MySliderFragment : BaseFragment(R.layout.fragment_my_slider) {

    lateinit var binding: FragmentMySliderBinding
    var imageUrl: String? = null
    var url: String? = null


    override fun setUpLayout() {
        binding = FragmentMySliderBinding.bind(requireView())

        if (requireArguments() != null) {
            imageUrl = requireArguments().getString("img")
            url = requireArguments().getString("url")
            binding.image.setOnClickListener {
                if (!TextUtils.isEmpty(url)) {
                    openUrl(requireContext(), url)
                }
            }
            if (!TextUtils.isEmpty(imageUrl)) Picasso.get().load(imageUrl)
                .placeholder(R.mipmap.ic_launcher).into(binding.image)
        }
    }

}