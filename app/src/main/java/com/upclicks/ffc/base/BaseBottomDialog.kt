package com.upclicks.ffc.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.upclicks.ffc.R
import com.upclicks.ffc.session.SessionHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// bottom sheet dialog style for xml
//<style name="BottomSheetDialog" parent="Theme.Design.Light.BottomSheetDialog">
//<!--<item name="android:windowCloseOnTouchOutside">false</item>-->
//<item name="android:windowIsTranslucent">true</item>
//<item name="android:windowContentOverlay">@null</item>
//<item name="android:colorBackground">@android:color/transparent</item>
//<item name="android:backgroundDimEnabled">true</item>
//<item name="android:backgroundDimAmount">0.3</item>
//<item name="android:windowFrame">@null</item>
//<item name="android:windowIsFloating">true</item>
//<item name="windowFixedWidthMajor">100%</item>
//<item name="windowFixedHeightMajor">100%</item>
//</style>
abstract class BaseBottomDialog(context: Context
) : BottomSheetDialog(context, R.style.BottomSheetDialog) {
    @Inject
    lateinit var sessionHelper: SessionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }
    private fun setUpDialogUi() {
        setContentView(getLayoutResourceId())
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val displayMetrics = context.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val mBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from(getLayoutResourceId())
        mBehavior.peekHeight = height
    }
    protected abstract fun getLayoutResourceId(): View
}