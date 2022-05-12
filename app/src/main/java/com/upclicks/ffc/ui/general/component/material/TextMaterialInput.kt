package com.upclicks.ffc.ui.general.component.material

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.upclicks.ffc.R
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput


class TextMaterialInput(context: Context, attrs: AttributeSet?) :
    BaseMaterialEditText(context, attrs) {
    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                typingCallback.onTyping(charSequence.toString())
                if (!charSequence.toString().isNullOrEmpty()) {
                    isValid = true
                    textInputLayout.error = null
                } else {
                    isValid = false
                    textInputLayout.error = context.getString(R.string.required)
                }
            }
            override fun afterTextChanged(editable: Editable) {
            }
        })
    }

}