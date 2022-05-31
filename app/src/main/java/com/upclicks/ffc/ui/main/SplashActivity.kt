package com.upclicks.ffc.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ActivitySplashBinding
import com.upclicks.ffc.ui.authentication.LoginByEmailActivity
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    private val accountViewModel: AccountViewModel by viewModels()

    var splashFinished = MutableLiveData<Boolean>()
    var verifyFinished = MutableLiveData<Boolean>()
    var check = MutableLiveData<Boolean>()

    override fun getLayoutResourceId(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }
    // main function for this page
    private fun initPage() {
        setUpPageUi()
        setUpPageActions()
    }
    // set up page ui
    private fun setUpPageUi() {
        splashFinished.postValue(false)
        verifyFinished.postValue(false)

        Handler().postDelayed({
            splashFinished.postValue(true)
        }, Keys.SPLASH_DISPLAY_LENGTH)
        verifyFinished.observe(this) { verify: Boolean? ->
            check.postValue(
                true
            )
        }
        splashFinished.observe(this) { splash: Boolean? ->
            check.postValue(
                true
            )
        }
        baseURLConfigHelper!!.configBaseUrl { baseUrl ->
            Keys.BASE_URL = baseUrl
            initFirebaseToken()
        }
        check.observe(this) { check: Boolean? ->
            if (verifyFinished.value!! && splashFinished.value!!) {
                if (sessionHelper.isLogin) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    startActivity(Intent(this, LoginByEmailActivity::class.java))
//                    if (!sessionHelper.isLangSelected(this)) {
//                        val intent = Intent(this, SelectLanguageActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                    }
                }
                finish()
            }
        }
    }

    //
    private fun initFirebaseToken() {
        FirebaseApp.initializeApp(this)
        if (TextUtils.isEmpty(sessionHelper.pushNotificationToken)) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(
                        "addOnCompleteListener",
                        "Fetching FCM registration token failed",
                        task.exception
                    )
                    return@OnCompleteListener
                }
                // Get new FCM registration token
                val token = task.result
                // Log and toast
                Log.e("FCM", "Current token=$token")
                sessionHelper.savePushNotificationToken(token)
                verifySession()
            })
        } else {
            verifySession()
        }
    }

    private fun verifySession() {
        accountViewModel.callVerifySession()
        accountViewModel.observeVerifySession.observe(this) { verifySession ->
            if (verifySession != null) {
                if (verifySession.sessionStatus === 1) {
//                    Utils.showWarningDialog(
//                        this,
//                        verifySession.getMessage(),
//                        object : ClickAction() {
//                            fun onButtonClick(isPositive: Boolean) {
//                                if (isPositive) {
//
//                                    verifyFinished.postValue(true)
//                                }
//                            }
//                        })
                } else if (verifySession.sessionStatus === 2) {
//                    Utils.showBlockDialog(this, verifySession.getMessage(), object : ClickAction() {
//                        fun onButtonClick(isPositive: Boolean) {}
//                    })
                } else {
                    if (verifySession.profile != null) {
                        sessionHelper.userProfile = verifySession.profile!!
                        sessionHelper.saveCartCount(verifySession.profile!!.currentCartProductsCount)
                    }
                    verifyFinished.postValue(true)
                }
            }
        }
    }

    //     set up page listeners and callback
    private fun setUpPageActions() {
    }
}