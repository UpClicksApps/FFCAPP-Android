package com.upclicks.ffc.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import com.upclicks.ffc.R
import com.upclicks.ffc.rx.RxBus
import com.upclicks.ffc.ui.general.events.EventsModel
import com.upclicks.ffc.session.SessionHelper
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.data.BaseURLConfigHelper
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import www.sanju.motiontoast.MotionToast
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    private lateinit var eventDisposable: Disposable
    var eventSessionUpdateDisposable: Disposable? = null
    var eventIncomeChatMsg: Disposable? = null
    private var eventMessageDisposable: Disposable? = null
    private var eventUnAuthDisposable: Disposable? = null
    @Inject
    lateinit var sessionHelper: SessionHelper
    @Inject
    lateinit var baseURLConfigHelper: BaseURLConfigHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionHelper.configLanguage(this)
        sessionHelper.saveDeviceId(Utils.getDeviceId(this))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(getLayoutResourceId())
        //subscribe to events
        eventDisposable = RxBus.listen(EventsModel.MessageEvent::class.java)
            .subscribe() {
                shoMsg(it.message, MotionToast.TOAST_ERROR)
            }
        eventDisposable = RxBus.listen(EventsModel.UnAuthorizedEvent::class.java)
            .subscribe { shoMsg(it.message, MotionToast.TOAST_ERROR) }
    }

    protected abstract fun getLayoutResourceId(): View


    fun shoMsg(msg: String, type: String) {
        MotionToast.createColorToast(
            this,
            "App",
            msg,
            type,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION, null
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!eventDisposable.isDisposed) eventDisposable.dispose()
    }


    @SuppressLint("ResourceType")
    open fun getTransaction(): FragmentTransaction? {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.abc_fade_in,
            R.anim.abc_fade_out
        )
        return transaction
    }

}