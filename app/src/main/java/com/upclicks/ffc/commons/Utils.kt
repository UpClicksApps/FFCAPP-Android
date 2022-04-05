package com.upclicks.tcare.commons

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.upclicks.tcare.R
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException


class Utils {
    companion object {
        // parse error body
        fun parseResponse(response: Response<Any>): String? {
            var errorMessage = ""
            try {
                val jsonObject = JSONObject(response.errorBody()!!.string())
                val errorObject: JSONObject = jsonObject.getJSONObject("error")
                errorMessage =
                    if (errorObject.getString("message") + " " + errorObject.getString("details") != null) errorObject.getString(
                        "details"
                    )
                    else ""
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return errorMessage
        }

        fun openUrl(context: Context, url: String?) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            } catch (e: Exception) {
            }
        }
        //Get Device Id
        fun getDeviceId(context: Context): String? {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }
    }


    fun dialPhoneNumber(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(intent)
    }
//
//    fun share(context: Context, url: String) {
//        try {
//            val shareIntent = Intent(Intent.ACTION_SEND)
//            shareIntent.type = "text/plain"
//            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MyTo")
//            val shareMessage = "" + url
//            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//            context.startActivity(
//                Intent.createChooser(
//                    shareIntent,
//                    context.getString(R.string.share_with)
//                )
//            )
//        } catch (e: java.lang.Exception) {
//            //e.toString();
//        }
//    }
}