package com.upclicks.ffc.ui.general.component.customedittext

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import com.google.common.base.Strings.isNullOrEmpty
import com.upclicks.tcare.R
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateInput(context: Context, attrs: AttributeSet?) : BaseInput(context, attrs) {
    var selectDate: String = ""
    var dateWillSendToServer: String = ""
    init {
        isFocusable = false
        isFocusableInTouchMode = false
        isValid = !isNullOrEmpty(text.toString())
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                typingCallback.onTyping(charSequence.toString())
                if (!isNullOrEmpty(charSequence.toString())) {
                    isValid = true
                } else {
                    isValid = false
                    error = context.getString(R.string.not_valid)
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
        setOnClickListener {
            showDatePicker()
        }
    }
    private fun showDatePicker() {
        val c = Calendar.getInstance(Locale.US)
        val mYear = c[Calendar.YEAR] // current year
        val mMonth = c[Calendar.MONTH] // current month
        val mDay = c[Calendar.DAY_OF_MONTH] // current day
        // date picker dialog
        val datePickerDialog = DatePickerDialog(
            context,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // set day of month , month and year value in the edit text
                //                        view.setMinDate(System.currentTimeMillis() - 1000);
                c.set(year, monthOfYear, dayOfMonth)
                selectDate = formatDate(dayOfMonth, monthOfYear, year)
                dateWillSendToServer = dateServerFormat(c.time)
                setText(selectDate)
                error = null
                isValid = if (!isNullOrEmpty(selectDate)) {
                    true
                } else {
                    true
                }
            }, mYear, mMonth, mDay
        )
        //        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show()
    }
    fun getSelectedDate(): String {
        return selectDate
    }
    private fun formatDate(dayOfMonth: Int, monthOfYear: Int, year: Int): String {
        return (if (dayOfMonth <= 9) "0$dayOfMonth" else dayOfMonth).toString() + "/" + (if (monthOfYear + 1 <= 9) "0" + (monthOfYear + 1) else monthOfYear + 1) + "/" + year
    }
    private fun dateServerFormat(date: Date): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return dateFormat.format(date)
    }
}
@BindingAdapter("dateInputUi")
fun dateInputUi(@NonNull textView: TextView, dateString: String?) {
    var dateStr = "00/00/0000"
    if (dateString != null) {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale("En"))
        var date: Date? =
            null //You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(dateString)
            val formatter: DateFormat = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.ENGLISH
            ) //If you need time just put specific format for time like 'HH:mm:ss'
            dateStr = formatter.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    textView.text = dateStr
}

