package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.databinding.FragmentHomeBinding
import com.upclicks.ffc.databinding.FragmentProfileBinding
import com.upclicks.ffc.ui.authentication.PersonalDetailsActivity
import com.upclicks.ffc.ui.notification.NotificationActivity
import com.upclicks.ffc.ui.products.FavoriteActivity
import com.upclicks.ffc.ui.products.MyOrdersActivity
import com.upclicks.ffc.ui.products.ShoppingCartActivity
import com.upclicks.ffc.ui.products.WalletActivity

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    lateinit var binding: FragmentProfileBinding

    override fun setUpLayout() {
        binding = FragmentProfileBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.titleTv.text = getString(R.string.profile)
        binding.cartIv.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }
    }

    private fun setUpPageActions() {
        binding.personalDetailsTv.setOnClickListener {
            startActivity(Intent(requireContext(), PersonalDetailsActivity::class.java))
        }
        binding.myOrdersTv.setOnClickListener {
            startActivity(Intent(requireContext(), MyOrdersActivity::class.java))
        }
        binding.myFavoritesTv.setOnClickListener {
            startActivity(Intent(requireContext(), FavoriteActivity::class.java))
        }

        binding.notificationTv.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationActivity::class.java))
        }
        binding.walletTv.setOnClickListener {
            startActivity(Intent(requireContext(), WalletActivity::class.java))
        }
        binding.shippingAddressTv.setOnClickListener {

        }
        binding.settingsTv.setOnClickListener {

        }
    }
}