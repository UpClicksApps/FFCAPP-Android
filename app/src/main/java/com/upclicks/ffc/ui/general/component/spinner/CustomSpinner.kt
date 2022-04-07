package com.upclicks.ffc.ui.general.component.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner

class CustomSpinner<T>(context: Context, attrs: AttributeSet?) : AppCompatSpinner(context, attrs) {

    lateinit var dataList: ArrayList<BaseSelection>
    lateinit var adapter: ArrayAdapter<String>
    lateinit var baseSelection: BaseSelection
    var selectionCallback = object : SelectionCallback {
        override fun onItemSelected(baseSelection: BaseSelection) {

        }
    }

    init {
        onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    if (dataList != null && dataList.isNotEmpty()) {
                        baseSelection = dataList[position]
                        selectionCallback.onItemSelected(baseSelection)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }

    fun setSelectionList(dataList: T) {
        this.dataList = dataList as ArrayList<BaseSelection>
        if (dataList != null && dataList.isNotEmpty()) {
            baseSelection = dataList[0]
            initAdapter()
        } else {
            clearData()
        }
    }

    fun setSelectionList(dataList: T, selectedId: String) {
        this.dataList = dataList as ArrayList<BaseSelection>
        if (dataList != null && dataList.isNotEmpty()) {
            setSelectionItemById(selectedId)
            initAdapter()
        } else {
            clearData()
        }
    }

    private fun clearData() {
        dataList.clear()
        adapter.clear()
        adapter.notifyDataSetChanged()
    }

    fun setCustomSelectionCallback(selectionCallback: SelectionCallback) {
        this.selectionCallback = selectionCallback
    }

    private fun initAdapter() {
        var spinnerList: ArrayList<String> = ArrayList<String>()
        for (selection: BaseSelection in dataList) {
            spinnerList.add(selection.name!!)
        }
        adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerList)
        setAdapter(adapter)
    }

    fun getCurrentItem(): BaseSelection {
        return baseSelection
    }

    fun getItemById(id: String): BaseSelection {
        if (dataList != null && dataList.isNotEmpty()) {
            for (selection: BaseSelection in dataList) {
                if (id == selection.id)
                    return selection
            }
        }
        return baseSelection
    }

    public fun setSelectionItemById(id: String) {
        if (dataList != null && dataList.isNotEmpty()) {
            if (id == "0")
                setSelection(0)
            else
                dataList.forEachIndexed { index, selection ->
                    if (id == selection.id) {
                        baseSelection = selection
                        setSelection(index)
                    }
                }
        }
    }


    fun getItemPositionById(id: String): Int {
        for (selection: BaseSelection in dataList) {
            if (id == selection.id)
                return dataList.indexOf(selection)
        }
        return -1
    }


    interface SelectionCallback {
        fun onItemSelected(baseSelection: BaseSelection)
    }
}