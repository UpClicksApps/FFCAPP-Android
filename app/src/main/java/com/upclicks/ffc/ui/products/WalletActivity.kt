package com.upclicks.ffc.ui.products

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.ui.products.adapter.WalletAdapter
import com.upclicks.ffc.ui.products.model.Wallet

class WalletActivity : BaseActivity() {
    lateinit var binding: ActivityFavoriteBinding

    var walletList = ArrayList<Wallet>()
    lateinit var walletAdapter : WalletAdapter


    override fun getLayoutResourceId(): View {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpUiList()
    }
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.wallet)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpUiList() {
        walletAdapter = WalletAdapter(this,walletList, onItemClicked = {})
        binding.recycler.adapter = walletAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
}