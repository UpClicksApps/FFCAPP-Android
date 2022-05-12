package com.upclicks.ffc.ui.general.component.material

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import com.google.android.material.textfield.TextInputLayout


open class CustomMaterialInputLayout(context: Context, val attrs: AttributeSet?) :
    TextInputLayout(context, attrs) {
    var baseMaterialEditText =  BaseMaterialEditText(context,attrs)
    override fun getEditText(): BaseMaterialEditText? {
        return baseMaterialEditText
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        if (child is BaseMaterialEditText) {
            baseMaterialEditText = child as BaseMaterialEditText
        }
    }
}