package com.upclicks.ffc.ui.general.component

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.upclicks.ffc.R

class CustomSwipeRefreshLayout(context: Context, attrs: AttributeSet?) :
    SwipeRefreshLayout(context, attrs) {
        init {
            setColorSchemeColors(context.resources.getColor(R.color.primary))
        }
}