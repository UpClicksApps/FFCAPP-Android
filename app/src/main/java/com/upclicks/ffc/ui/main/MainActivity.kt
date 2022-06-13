package com.upclicks.ffc.ui.main

import android.content.Intent
import android.view.View
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivityMainBinding
import com.upclicks.ffc.ui.authentication.LoginByEmailActivity
import com.upclicks.ffc.ui.cart.ShoppingCartActivity
import com.upclicks.ffc.ui.general.dialog.LoginDialog

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    override fun getLayoutResourceId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setUpMenu()
        return binding.root
    }


    private fun setUpMenu() {
        binding.menu.setItemSelected(R.id.home_nav, false)
        pagesController!!.switchToPage(Keys.NavigationBottom.HOME, getTransaction())

        binding.menu.setOnItemSelectedListener { id ->
            when (id) {
                R.id.home_nav -> {
                    pagesController!!.switchToPage(Keys.NavigationBottom.HOME, getTransaction())
                }
                R.id.categories_nav -> {
                    pagesController!!.switchToPage(
                        Keys.NavigationBottom.CATEGORIES,
                        getTransaction()
                    )
                }
                R.id.search_nav -> {
                    pagesController!!.switchToPage(Keys.NavigationBottom.SEARCH, getTransaction())
                }
                R.id.profile_nav -> {
                    if (sessionHelper.isLogin)
                        pagesController!!.switchToPage(Keys.NavigationBottom.PROFILE, getTransaction())
                    else {
                        LoginDialog(this, onYesBtnClick = {
                            startActivity(Intent(this, LoginByEmailActivity::class.java))
                            finishAffinity()
                        }, onNoBtnClick = {}).show()
                    }
                }
            }
        }
    }
}
