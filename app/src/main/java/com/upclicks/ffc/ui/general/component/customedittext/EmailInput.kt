package com.upclicks.ffc.ui.general.component.customedittext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.upclicks.ffc.R


class EmailInput(context: Context, attrs: AttributeSet?) : BaseInput(context, attrs) {
    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                typingCallback.onTyping(charSequence.toString())
                if (!charSequence.toString()
                        .isNullOrEmpty() && isEmailValid(charSequence.toString())
                ) {
                    isValid = true
                    error = null
                } else {
                    isValid = false
                    error = context.getString(R.string.not_valid)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    fun isEmailValid(input: String): Boolean {
        return !input.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    override fun setOnTextTyping(typingCallback: TypingCallback) {
        super.setOnTextTyping(typingCallback)
    }


}