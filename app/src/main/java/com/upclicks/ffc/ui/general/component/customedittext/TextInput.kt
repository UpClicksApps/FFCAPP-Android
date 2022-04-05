package com.upclicks.ffc.ui.general.component.customedittext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.upclicks.ffc.R
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput


class TextInput(context: Context, attrs: AttributeSet?) : BaseInput(context, attrs) {
    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                typingCallback.onTyping(charSequence.toString())
                testValidation(charSequence.toString())
            }
            override fun afterTextChanged(editable: Editable) {
            }
        })
    }
    fun testValidation(text: String){
        if (!text.isNullOrEmpty()) {
            isValid = true
            error = null
        } else{
            isValid = false
            error = context.getString(R.string.required)
            animation = shakeError()
        }
    }

}