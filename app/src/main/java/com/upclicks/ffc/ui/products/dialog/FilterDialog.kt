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
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.DialogFilterBinding
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.model.ProductRequest
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast

class FilterDialog(
    var mContext: Context,
    var categoryId: String,
    var productViewModel: ProductViewModel,
    private val onApplyBtnClicked: (String, ProductRequest) -> Unit
) : BottomSheetDialog(mContext, R.style.BottomSheetDialog) {
    lateinit var binding: DialogFilterBinding
    var productRequest = ProductRequest()
    var categoriesList = ArrayList<Category>()
    var categoryName = ""
    var resetName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }

    private fun setUpDialogUi() {
        binding = DialogFilterBinding.inflate(LayoutInflater.from(context))
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
        setUpCategories()
        binding.closeIv.setOnClickListener {
            dismiss()
        }
        productRequest.categoryId = categoryId
        binding.resetBtn.setOnClickListener {
            productRequest = ProductRequest()
            binding.chipCloud.removeAllViews()
            categoriesList.forEach { category ->
                binding.chipCloud.addChip(category.name)
            }
            binding.seekbar.setProgress(0f, 0f)
            productRequest.minPrice = 0f
            productRequest.maxPrice = 0f
            categoryName = resetName
        }
        binding.applyBtn.setOnClickListener {
            onApplyBtnClicked(categoryName, productRequest)
            dismiss()
        }
        binding.seekbar.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(
                view: RangeSeekBar?,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {
                productRequest.minPrice = leftValue
                productRequest.maxPrice = rightValue
            }

            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
            }

        })

    }

    private fun setUpCategories() {
        productViewModel.getCategories { categories ->
            if (!categories.isNullOrEmpty()) {
                binding.chipCloud.visibility = View.VISIBLE
                binding.emptyCategoriesTv.visibility = View.GONE
                categoriesList.clear()
                categoriesList.addAll(categories)
                categories.forEachIndexed { index, category ->
                    binding.chipCloud.addChip(category.name)
                    if (category.id == categoryId) {
                        binding.chipCloud.setSelectedChip(index)
                        categoryName = categoriesList[index].name!!
                        resetName = categoriesList[index].name!!
                    }
                }
                binding.chipCloud.setChipListener(object : ChipListener {
                    override fun chipSelected(index: Int) {
                        productRequest.categoryId = categoriesList[index].id
                        categoryName = categoriesList[index].name!!
                    }

                    override fun chipDeselected(index: Int) {
                        productRequest.categoryId = categoryId
                        categoryName = resetName
                    }
                })
            } else {
                binding.chipCloud.visibility = View.GONE
                binding.emptyCategoriesTv.visibility = View.VISIBLE
            }
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