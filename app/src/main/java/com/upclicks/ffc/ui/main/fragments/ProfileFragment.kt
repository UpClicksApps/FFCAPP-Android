package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import androidx.fragment.app.viewModels
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseFragment
import com.upclicks.ffc.databinding.FragmentProfileBinding
import com.upclicks.ffc.ui.authentication.PersonalDetailsActivity
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.SettingsActivity
import com.upclicks.ffc.ui.general.dialog.ConfirmDialog
import com.upclicks.ffc.ui.main.SplashActivity
import com.upclicks.ffc.ui.notification.NotificationActivity
import com.upclicks.ffc.ui.products.FavoriteActivity
import com.upclicks.ffc.ui.orders.MyOrdersActivity
import com.upclicks.ffc.ui.products.WalletActivity

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    lateinit var binding: FragmentProfileBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun setUpLayout() {
        binding = FragmentProfileBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
        callMyProfile()
    }

    private fun callMyProfile() {
        accountViewModel.getMyProfile { profile ->
            binding.profile = profile
        }
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.titleTv.text = getString(R.string.profile)
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
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }
        binding.logoutLy.setOnClickListener {
            ConfirmDialog(requireContext(),
                requireActivity().getString(R.string.logout),
                requireActivity().getString(R.string.are_you_sure_to_logout),
                onYesBtnClick = {
                    sessionHelper.logout {
                        startActivity(Intent(requireContext(), SplashActivity::class.java))
                        requireActivity().finishAffinity()
                    }
                },
                onNoBtnClick = {
                }).show()

        }

    }
}