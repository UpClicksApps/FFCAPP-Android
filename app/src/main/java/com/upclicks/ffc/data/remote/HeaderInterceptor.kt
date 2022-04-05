package com.upclicks.ffc.data.remote

import android.os.Build
import com.google.firebase.database.annotations.NotNull
import com.upclicks.ffc.BuildConfig
import com.upclicks.ffc.commons.Connectivity
import com.upclicks.ffc.session.SessionHelper
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HeaderInterceptor : Interceptor {
    @Inject
    constructor()

    @Inject
    lateinit var connectivity: Connectivity
    @Inject
    lateinit var sessionHelper: SessionHelper

    val ANDROID = "0"

    @NotNull
    @Throws(IOException::class)
    override fun intercept(@NotNull chain: Interceptor.Chain): Response {
        return addRequestHeaders(chain)
    }

    private fun addRequestHeaders(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val originalHttpUrl = origin.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("culture", sessionHelper.userLanguageCode)
            .addQueryParameter("ui-culture", sessionHelper.appLanguage)
            .build()
        var request: Request? = null
        var builder: Request.Builder? = null
        builder = chain.request().newBuilder()
            .url(url)
            .addHeader("x-access-token", sessionHelper.userToken()!!)
            .addHeader("LanguageCode", sessionHelper.userLanguageCode)
            .addHeader("RegistrationToken", sessionHelper.pushNotificationToken)
            .addHeader("FirebaseToken", sessionHelper.pushNotificationToken)
            .addHeader("OSVersion", Build.VERSION.RELEASE.toString())
            .addHeader("ConnectionType", connectivity.networkConnectionType)
            .addHeader("AppVersion", BuildConfig.VERSION_NAME)
            .addHeader("DeviceType", Build.BRAND + " " + Build.MODEL)
            .addHeader("OSType", ANDROID)
            .addHeader("DeviceId", sessionHelper.deviceId)
            .addHeader("Latitude", "0")
            .addHeader("Longitude", "0")
            .addHeader("Accept", "application/json")
        try {
            if (sessionHelper.isLogin) {
                builder.addHeader("Authorization", "Bearer " + sessionHelper.userToken())
            }
            request = builder.build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return chain.proceed(request!!)
    }


    private fun internalServerError(response: Response): Boolean {
        //500 internal server error
        return (response.code == 500)
    }

    private fun shouldLogout(response: Response): Boolean {
        // 401 and auth token means that we need to logout
        return (response.code == 401)
    }
}