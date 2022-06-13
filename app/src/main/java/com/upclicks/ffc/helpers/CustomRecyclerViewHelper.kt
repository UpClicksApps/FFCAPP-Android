package com.upclicks.ffc.helpers

import android.content.Context
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout


//allprojects {
//    repositories {
//        ...
//        maven { url 'https://jitpack.io' }
//    }
//}
//implementation 'com.github.Spikeysanju:ZoomRecylerLayout:1.0'
//implementation 'com.github.rubensousa:gravitysnaphelper:2.2.2'
class CustomRecyclerViewHelper {

    companion object{

        fun addZoomRecyclerLayoutHorizontal(context:Context, recyclerView: RecyclerView){
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView.layoutManager = linearLayoutManager // Add your recycler
            val snapHelper = GravitySnapHelper(Gravity.CENTER)
            snapHelper.attachToRecyclerView(recyclerView)
        }
        fun addZoomRecyclerLayoutVertical(context:Context,recyclerView: RecyclerView){
            val linearLayoutManager = ZoomRecyclerLayout(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = linearLayoutManager // Add your recycler
            val snapHelper = GravitySnapHelper(Gravity.START)
            snapHelper.attachToRecyclerView(recyclerView)
        }
        fun addSnapHelper(context:Context,recyclerView: RecyclerView){
           val snapHelper = GravitySnapHelper(Gravity.START)
            snapHelper.attachToRecyclerView(recyclerView)
        }
    }

}