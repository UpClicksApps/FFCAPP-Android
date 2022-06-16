package com.upclicks.ffc.ui.general

import android.content.Intent
import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.databinding.ActivitySettingsBinding
import com.upclicks.ffc.ui.general.dialog.ChangeLanguageDialog

class SettingsActivity : BaseActivity() {

    lateinit var binding: ActivitySettingsBinding
    override fun getLayoutResourceId(): View {
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
    }

    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.settings)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.languageTv.setOnClickListener {
            ChangeLanguageDialog(this, sessionHelper).show()
        }
        binding.contactTv.setOnClickListener {
            startActivity(Intent(this, ContactUs::class.java))
        }
        binding.aboutTv.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
        binding.helpTv.setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }
        binding.shareTv.setOnClickListener {
            Utils.shareAppLink(this)
        }
    }
}