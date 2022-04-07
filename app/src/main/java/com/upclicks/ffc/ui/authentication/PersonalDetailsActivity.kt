package com.upclicks.ffc.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityPersonalDetailsBinding
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection
import com.upclicks.ffc.ui.general.component.spinner.CustomSpinner
import com.upclicks.ffc.ui.products.adapter.FavoriteAdapter
import com.upclicks.ffc.ui.products.model.Product

class PersonalDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityPersonalDetailsBinding

    override fun getLayoutResourceId(): View {
        binding = ActivityPersonalDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageAction()
    }

    private fun setUpPageAction() {
        binding.phoneInput.setOnTextTyping(object :BaseInput.TypingCallback{
            override fun onTyping(text: String) {

            }
        })
        binding.userNameInput.setOnTextTyping(object :BaseInput.TypingCallback{
            override fun onTyping(text: String) {

            }
        })
        binding.citySP.setCustomSelectionCallback(object :CustomSpinner.SelectionCallback{
            override fun onItemSelected(baseSelection: BaseSelection) {

            }
        })
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.personal_details)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

}