package com.upclicks.ffc.ui.general.component

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import com.upclicks.ffc.R
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.component.material.BaseMaterialEditText
import com.upclicks.ffc.ui.general.component.material.CustomMaterialInputLayout
import www.sanju.motiontoast.MotionToast

class CustomInputHelper {
    companion object {
        // set up typingCallback for inputs views
        fun setUpInputsTypingCallback(inputViews: ArrayList<BaseInput>) {
            inputViews.forEach { inputView ->
                inputView!!.setOnTextTyping(
                    object : BaseInput.TypingCallback {
                        override fun onTyping(text: String) {
                        }
                    })
            }
        }
        fun checkIfInputsIsValid(
            context: Context,
            inputViews: ArrayList<BaseInput>
        ): Boolean {
            var inputsViews = inputViews
            inputsViews.forEach { inputView ->
                if (!inputView!!.isValid) {
                    inputView!!.requestFocus()
                    return false
                }
            }
            return true
        }
    }
}