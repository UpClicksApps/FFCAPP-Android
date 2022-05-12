package com.upclicks.ffc.ui.general.component.material

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout
import com.upclicks.ffc.R


class PasswordMaterialEditText(context: Context, attrs: AttributeSet?) :
    BaseMaterialEditText(context, attrs) {
    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                typingCallback.onTyping(charSequence.toString())
                when {
                    charSequence.toString().isNullOrEmpty() -> {
                        isValid = false
                        textInputLayout.error = context.getString(R.string.required)
                    }
                    charSequence.toString().length < 6 -> {
                        isValid = false
                        textInputLayout.error = context.getString(R.string.not_valid)
                    }
                    else -> {
                        isValid = true
                        textInputLayout.error = null
                    }
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}