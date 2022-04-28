package com.upclicks.ffc.helpers

import android.content.Context
import androidx.core.graphics.ColorUtils
import com.upclicks.ffc.R
import java.util.*


class CustomColorHelper {
    companion object {
        fun generateRandomColor(context: Context): Int {
            var androidColors: IntArray = context.resources.getIntArray(R.array.randomColors)
            return ColorUtils.setAlphaComponent(androidColors[Random().nextInt(androidColors.size)],0xBB)
        }

    }
}

//
//<!-- this colors for Color helper class used to generate random colors  -->
//<item name="blue_helper" type="color">#FF33B5E5</item>
//<item name="purple_helper" type="color">#FFAA66CC</item>
//<item name="green_helper" type="color">#FF99CC00</item>
//<item name="orange_helper" type="color">#FFFFBB33</item>
//<item name="red_helper" type="color">#FFFF4444</item>
//<item name="darkblue_helper" type="color">#FF0099CC</item>
//<item name="darkpurple_helper" type="color">#FF9933CC</item>
//<item name="darkgreen_helper" type="color">#FF669900</item>
//<item name="darkorange_helper" type="color">#FFFF8800</item>
//<item name="darkred_helper" type="color">#FFCC0000</item>
//<integer-array name="randomColors">
//<item>@color/blue_helper</item>
//<item>@color/purple_helper</item>
//<item>@color/green_helper</item>
//<item>@color/orange_helper</item>
//<item>@color/red_helper</item>
//<item>@color/darkblue_helper</item>
//<item>@color/darkpurple_helper</item>
//<item>@color/darkgreen_helper</item>
//<item>@color/darkorange_helper</item>
//<item>@color/darkred_helper</item>
//</integer-array>