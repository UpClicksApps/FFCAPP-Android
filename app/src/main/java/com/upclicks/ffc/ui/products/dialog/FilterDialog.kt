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
import com.upclicks.ffc.databinding.DialogFilterBinding
import com.upclicks.ffc.ui.general.model.Category
import com.upclicks.ffc.ui.products.viewmodel.ProductViewModel
import www.sanju.motiontoast.MotionToast

class FilterDialog (
    context: Context,
    var productViewModel: ProductViewModel,
    private val onApplyBtnClicked: (Category) -> Unit
) : BottomSheetDialog(context, R.style.BottomSheetDialog) {
    lateinit var binding: DialogFilterBinding
    var category = Category()
    var categoriesList = ArrayList<Category>()

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
        binding.resetBtn.setOnClickListener {
            category = Category()
            dismiss()
        }
        binding.applyBtn.setOnClickListener {
            if (category.id != null){
                onApplyBtnClicked(category)
                dismiss()
            }else{
                shoMsg(context.getString(R.string.category_must_be_selected),MotionToast.TOAST_ERROR)
            }
        }

    }
    private fun setUpCategories() {
        productViewModel.getCategories { categories->
            if (!categories.isNullOrEmpty()){
                binding.chipCloud.visibility = View.VISIBLE
                binding.emptyCategoriesTv.visibility = View.GONE
                categoriesList.clear()
                categoriesList.addAll(categories)
                categories.forEach { category ->
                    binding.chipCloud.addChip(category.name)
                }
                binding.chipCloud.setChipListener(object :ChipListener{
                    override fun chipSelected(index: Int) {
                        category = categoriesList[index]
                    }
                    override fun chipDeselected(index: Int) {
                    }
                })
            }else{
                binding.chipCloud.visibility = View.GONE
                binding.emptyCategoriesTv.visibility = View.VISIBLE
            }
        }
    }

    fun shoMsg(msg: String, type: String) {

        MotionToast.createColorToast(
            context as Activity,
            "App",
            msg,
            type,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION, null
        )
    }


}