package com.upclicks.ffc.commons

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.TextView
import androidx.annotation.NonNull
import com.google.gson.Gson
import com.upclicks.ffc.R
import com.upclicks.ffc.data.event.ErrorEvent
import com.upclicks.ffc.data.model.ErrorModel
import com.upclicks.ffc.session.SessionHelper
import com.upclicks.ffc.ui.notification.GetTimeAgo
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {
        // parse error body
        fun parseResponse(response: Response<Any>): ErrorEvent? {
            var errorMessage = ErrorEvent(0, "", "")
            var errorModel: ErrorModel
            try {
                val jsonObject = JSONObject(response.errorBody()!!.string())
                val errorObject: JSONObject = jsonObject.getJSONObject("error")
                if (errorObject != null) {
                    errorModel =
                        Gson().fromJson(errorObject.toString(), ErrorModel::class.java)
                } else {
                    errorModel = ErrorModel("Error in api response!")
                }
                errorMessage.message = errorModel.message
                errorMessage.details = errorModel.details
                errorMessage.code = errorModel.code
            } catch (e: JSONException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return errorMessage
        }


        fun createPartFromString(str: String): RequestBody {
            return RequestBody.create("text/plain".toMediaTypeOrNull(), str!!)
        }

        fun openUrl(context: Context, url: String?) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            } catch (e: Exception) {
            }
        }

        fun convertFromStringToDate(stringDate: String): Date {
            var format: SimpleDateFormat? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            }
            var date = Date()
            try {
                date = format!!.parse(stringDate)
            } catch (e: android.net.ParseException) {
                e.printStackTrace()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return date
        }
        //Get Device Id
        fun getDeviceId(context: Context): String? {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun getDurationFromData(datef: String?, context: Context?): String? {
            var milliseconds: Long = 0
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            var date: Date? =
                null //You will get date object relative to server/client timezone wherever it is parsed
            try {
                date = dateFormat.parse(datef)
                milliseconds = date.time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return GetTimeAgo.getTimeAgo(milliseconds, context)
        }

        fun getDate(@NonNull textView: TextView, dateString: String?) {
            var dateStr = ""
            if (dateString != null) {
                val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                val sessionHelper = SessionHelper(textView.context)
                var date: Date? =
                    null //You will get date object relative to server/client timezone wherever it is parsed
                try {
                    //  Sat 10 November 2018
                    date = dateFormat.parse(dateString)
                    val formatter: DateFormat = SimpleDateFormat(
                        "EEE d MMM yyyy-HH:mm",
                        Locale(sessionHelper.userLanguageCode)
                    ) //If you need time just put specific format for time like 'HH:mm:ss'
                    dateStr = formatter.format(date)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
            textView.text = dateStr
        }

        fun dialPhoneNumber(context: Context, phoneNumber: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(intent)
        }

        // share play store link of my app
        fun shareAppLink(context: Context) {
            try {

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
                val shareMessage =
                    "https://play.google.com/store/apps/details?id=${context.applicationContext.packageName}"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        context.getString(R.string.share)
                    )
                )
            } catch (e: java.lang.Exception) {
                //e.toString();
            }
        }

        fun isNullOrEmpty(str: String?): Boolean {
            return str == null || str.isEmpty() || str.trim { it <= ' ' }.isEmpty() || str == "null"
        }
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
}
