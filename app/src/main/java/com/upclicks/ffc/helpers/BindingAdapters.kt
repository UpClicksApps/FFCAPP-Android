package com.upclicks.ffc.helpers

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.upclicks.ffc.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.commons.Utils
import com.upclicks.ffc.session.SessionHelper


@BindingAdapter("notNullText")
fun text(textView: TextView, str: String?) {
    textView.text = if (str == null || str.isEmpty() || str.trim { it <= ' ' }
            .isEmpty() || str === "null") "" else str.replace("\"", "")
}

@BindingAdapter("notNullHtmlText")
fun textHtml(textView: TextView, str: String?) {
    val txt = if (str == null || str.isEmpty() || str.trim { it <= ' ' }
            .isEmpty() || str === "null") "" else str
    textView.text = Html.fromHtml(txt)
}


@BindingAdapter("underlineTextView")
fun underlineTextView(textView: TextView, text: String?) {
    textView.text = text
    textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

@BindingAdapter("oldPriceTextView")
fun setOldPriceTextView(textView: TextView, oldPrice: String?) {
    textView.text = oldPrice
    textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
@BindingAdapter("bindServerDate")
fun bindServerDate(@NonNull textView: TextView, dateString: String?) {
    var dateStr = ""
    val sessionHelper = SessionHelper(textView.context)
    if (dateString != null) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var date: Date? =
            null //You will get date object relative to server/client timezone wherever it is parsed
        try {
            //  Sat 10 November 2018
            date = dateFormat.parse(dateString)
            val formatter: DateFormat = SimpleDateFormat(
                "EEE d MMM yyyy",
                Locale(sessionHelper.userLanguageCode)
            ) //If you need time just put specific format for time like 'HH:mm:ss'
            dateStr = formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    textView.text = dateStr
}
@BindingAdapter("bindBirthdate")
fun bindBirthdate(@NonNull textView: TextView, dateString: String?) {
    var dateStr = ""
    val sessionHelper = SessionHelper(textView.context)
    if (dateString != null) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var date: Date? =
            null //You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(dateString)
            val formatter: DateFormat = SimpleDateFormat(
                "dd/MM/yyyy"
                ,Locale.ENGLISH
            ) //If you need time just put specific format for time like 'HH:mm:ss'
            dateStr = formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    textView.text = dateStr
}

@BindingAdapter("bindLeftTime")
fun bindLeftTime(@NonNull textView: TextView, dateString: String?) {
    var dateStr = ""
    val sessionHelper = SessionHelper(textView.context)
    if (dateString != null) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var date: Date? =
            null //You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(dateString)
            val formatter: DateFormat = SimpleDateFormat(
                "HH:mm:ss",
                Locale(sessionHelper.userLanguageCode)
            ) //If you need time just put specific format for time like 'HH:mm:ss'
            dateStr = formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    textView.text = dateStr
}

@BindingAdapter("bindTime")
fun bindTime(@NonNull textView: TextView, dateString: String?) {
    var dateStr = "00/00/0000"
    val sessionHelper = SessionHelper(textView.context)
    if (dateString != null) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        var date: Date? =
            null //You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(dateString)
            val formatter: DateFormat = SimpleDateFormat(
                "HH:mm aa",
                Locale(sessionHelper.userLanguageCode)
            ) //If you need time just put specific format for time like 'HH:mm:ss'
            dateStr = formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    textView.text = dateStr
}

//
//
//@BindingAdapter("imageAvatarBinding")
//fun avatarBinding(view: ImageView, imageUrl: String?) {
//    if (TextUtils.isEmpty(imageUrl)){
//        view.setImageResource(R.drawable.ic_shoply_logo)
//        return
//    }
//    Glide.with(view.context)
//        .load(Utils.PrepareImageUrl(imageUrl))
//        .placeholder(R.drawable.ic_user_new)
//        .timeout(20000)
//        .into(view)
//}
//
@BindingAdapter("imageBinding")
fun imageBinding(view: ImageView, imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        view.setImageResource(R.mipmap.ic_launcher)
        return
    }
    Glide.with(view.context)
        .load(prepareImageUrl(imageUrl!!))
        .placeholder(R.mipmap.ic_launcher)
        .timeout(20000)
        .into(view)
}

@BindingAdapter("salonLogoBinding")
fun salonLogoBinding(view: ImageView, imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        view.setImageResource(R.mipmap.ic_launcher)
        return
    }
    Glide.with(view.context)
        .load(prepareImageUrl(imageUrl!!))
        .placeholder(R.mipmap.ic_launcher)
        .timeout(20000)
        .into(view)
}
@BindingAdapter("salonDefaultBinding")
fun salonDefaultBinding(view: ImageView, imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        view.setImageResource(R.mipmap.ic_launcher)
        return
    }
    Glide.with(view.context)
        .load(prepareImageUrl(imageUrl!!))
        .placeholder(R.mipmap.ic_launcher)
        .timeout(20000)
        .into(view)
}

//@BindingAdapter("bindAgo")
//fun bindAgo(txt : TextView,dateTxt : String?){
//    if(dateTxt==null)
//        return
//    var milliseconds: Long = 0
//    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//    var date: Date? = null //You will get date object relative to server/client timezone wherever it is parsed
//    try {
//        date = dateFormat.parse(dateTxt)
//        milliseconds = date.time
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }
//    txt.text=GetTimeAgo.getTimeAgo(milliseconds, txt.context)
//}
 fun prepareImageUrl(imageUrl: String): String? {
        var url = ""
        url =
            if (imageUrl.contains(Keys.BASE_URL) || imageUrl.contains("http://") || imageUrl.contains(
                    "https://"
                )
            ) imageUrl else Keys.BASE_URL.toString() + imageUrl
        return url
    }


@BindingAdapter("strikeThrough")
fun strikeThrough(textView: TextView, strikeThrough: Boolean) {
    if (strikeThrough) {
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }



}

