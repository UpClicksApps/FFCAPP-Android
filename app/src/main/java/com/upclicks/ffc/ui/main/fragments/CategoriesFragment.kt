package com.upclicks.ffc.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseFragment
import com.upclicks.ffc.databinding.FragmentCategoriesBinding
import com.upclicks.ffc.databinding.FragmentHomeBinding
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.main.adapters.CategoryAdapter
import com.upclicks.ffc.ui.products.ShoppingCartActivity

class CategoriesFragment : BaseFragment(R.layout.fragment_categories) {
    lateinit var binding: FragmentCategoriesBinding

    var categoriesList = ArrayList<Category>()
    lateinit var categoryAdapter : CategoryAdapter

    override fun setUpLayout() {
        binding = FragmentCategoriesBinding.bind(requireView())
        initPage()
    }

    private fun initPage() {
        setUpToolbar()
        setUpUiList()
    }
    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.categories)
        binding.toolbar.cartIv.visibility = View.VISIBLE
        binding.toolbar.backIv.visibility = View.GONE
        binding.toolbar.cartIv.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }
    }
    private fun setUpUiList() {
        categoryAdapter = CategoryAdapter(requireContext(),categoriesList, onItemClicked = {})
        binding.recycler.adapter = categoryAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}