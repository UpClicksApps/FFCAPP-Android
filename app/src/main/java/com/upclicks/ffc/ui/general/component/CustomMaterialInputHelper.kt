package com.upclicks.ffc.ui.general.component

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import com.upclicks.ffc.R
import com.upclicks.ffc.ui.general.component.material.BaseMaterialEditText
import com.upclicks.ffc.ui.general.component.material.CustomMaterialInputLayout
import www.sanju.motiontoast.MotionToast

class CustomMaterialInputHelper {
    companion object {
        // set up typingCallback for inputs views
        fun setUpInputsTypingCallback(inputViews: ArrayList<CustomMaterialInputLayout>) {
            inputViews.forEach { inputView ->
                inputView!!.baseMaterialEditText.setOnTextTyping(
                    inputView,
                    object : BaseMaterialEditText.TypingCallback {
                        override fun onTyping(text: String) {
                        }
                    })
            }
        }

        fun checkIfInputsIsValid(
            context: Context,
            inputViews: ArrayList<CustomMaterialInputLayout>
        ): Boolean {
            var inputsViews = inputViews
            inputsViews.forEach { inputView ->
                if (!inputView!!.baseMaterialEditText.isValid) {
                    inputView!!.baseMaterialEditText.requestFocus()
                    inputView!!.baseMaterialEditText.error = context.getString(R.string.required)
                    return false
                }
            }
            return true
        }
    }
}
