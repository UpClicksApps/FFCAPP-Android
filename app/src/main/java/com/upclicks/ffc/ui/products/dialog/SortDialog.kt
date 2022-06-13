package com.upclicks.ffc.ui.products.dialog

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.adroitandroid.chipcloud.ChipListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.upclicks.ffc.R
import com.upclicks.ffc.commons.ProductSortBy
import com.upclicks.ffc.databinding.DialogSortBinding
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast

class SortDialog(
    var mContext: Context,
    private val onApplyBtnClicked: (Int) -> Unit
) : BottomSheetDialog(mContext, R.style.BottomSheetDialog) {
    lateinit var binding: DialogSortBinding
    var sortByKey:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }

    private fun setUpDialogUi() {
        binding = DialogSortBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        val displayMetrics = context.resources.displayMetrics
        val height = displayMetrics.heightPixels
        val mBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from(binding.root.parent as View)
        mBehavior.peekHeight = height
        binding.closeIv.setOnClickListener {
            dismiss()
        }
        binding.resetBtn.setOnClickListener {
            binding.highPriceRb.isChecked = false
            binding.lowPriceRb.isChecked = false
            binding.fromAToZRb.isChecked = false
            binding.fromZToARb.isChecked = false
            sortByKey = null
        }
        binding.applyBtn.setOnClickListener {
                onApplyBtnClicked(sortByKey!!)
                dismiss()
        }

        binding.lowPriceTv.setOnClickListener {
            binding.highPriceRb.isChecked = false
            binding.lowPriceRb.isChecked = true
            binding.fromAToZRb.isChecked = false
            binding.fromZToARb.isChecked = false
            sortByKey = ProductSortBy.HighToLowPrice.value
        }
        binding.highPriceTv.setOnClickListener {
            binding.highPriceRb.isChecked = true
            binding.lowPriceRb.isChecked = false
            binding.fromAToZRb.isChecked = false
            binding.fromZToARb.isChecked = false
            sortByKey = ProductSortBy.LowToHighPrice.value
        }
        binding.fromAToZTv.setOnClickListener {
            binding.highPriceRb.isChecked = false
            binding.lowPriceRb.isChecked = false
            binding.fromAToZRb.isChecked = true
            binding.fromZToARb.isChecked = false
            sortByKey = ProductSortBy.AToZ.value
        }
        binding.fromZToATv.setOnClickListener {
            binding.highPriceRb.isChecked = false
            binding.lowPriceRb.isChecked = false
            binding.fromAToZRb.isChecked = false
            binding.fromZToARb.isChecked = true
            sortByKey = ProductSortBy.ZToA.value
        }
    }

    fun shoMsg(msg: String, type: String) {
        MotionToast.createColorToast(
            mContext as Activity,
            mContext.getString(R.string.app_name),
            msg,
            type,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION, null
        )
    }


}