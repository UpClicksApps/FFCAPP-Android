package com.upclicks.ffc.architecture

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentTransaction
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.engineio.client.Transport
import com.upclicks.ffc.R
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.rx.RxBus
import com.upclicks.ffc.session.SessionHelper
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.data.BaseURLConfigHelper
import com.upclicks.ffc.data.event.MessageEvent
import com.upclicks.ffc.data.event.UnAuthorizedEvent
import com.upclicks.ffc.ui.general.component.PagesController
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
    @Inject
    lateinit var pagesController: PagesController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionHelper.configLanguage(this)
        sessionHelper.saveDeviceId(Utils.getDeviceId(this))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(getLayoutResourceId())
        //subscribe to events
        eventDisposable = RxBus.listen(UnAuthorizedEvent::class.java)
            .subscribe() { unAuth ->
                shoMsg(unAuth.message!!, MotionToast.TOAST_ERROR)
            }
        eventDisposable = RxBus.listen(MessageEvent::class.java)
            .subscribe() { message ->
                shoMsg(message.message!!, MotionToast.TOAST_ERROR)
            }
    }
    protected abstract fun getLayoutResourceId(): View
    fun shoMsg(msg: String, type: String) {
        MotionToast.createColorToast(
            this,
            getString(R.string.app_name),
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


    //Init Socket connection Headers
    fun initSocketHeaders(): Emitter.Listener? {
        return Emitter.Listener { args ->
            val transport = args[0] as Transport
            transport.on(
                Transport.EVENT_REQUEST_HEADERS
            ) { args ->
                val headers =
                    args[0] as MutableMap<String, List<String>>
                var bearer = ""
                if(sessionHelper.isLogin) {
                    bearer = "bearer " + sessionHelper.userToken()
                }
                var lang = sessionHelper.userLanguageCode
                var firebaseToke = sessionHelper.pushNotificationToken
                var osVersion = Build.VERSION.RELEASE
                var connectionType = ""
                var appVersion = ""
                var deviceType = Build.BRAND + " " + Build.MODEL
                var deviceId = sessionHelper.deviceId
                headers["Authorization"] = listOf(bearer)
                headers["x-access-token"] = listOf(bearer)
                headers["LanguageCode"] = listOf(lang)
                headers["RegistrationToken"] = listOf(firebaseToke)
                headers["FirebaseToken"] = listOf(firebaseToke)
                headers["OSVersion"] = listOf(osVersion.toString())
                headers["OSType"] = listOf(Keys.OS_TYPE)
                headers["ConnectionType"] = listOf(connectionType)
                headers["AppVersion"] = listOf(appVersion)
                headers["MachineType"] = listOf(deviceType)
                headers["MachineId"] = listOf(deviceId)
                headers["Accept"] = listOf("application/json")
            }.on(
                Transport.EVENT_RESPONSE_HEADERS
            ) { }
        }
    }
}