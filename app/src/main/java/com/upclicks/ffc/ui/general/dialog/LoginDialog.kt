package com.upclicks.ffc.ui.general.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.upclicks.ffc.databinding.DialogLoginBinding

class LoginDialog(
    context: Context,
    private val onYesBtnClick: () -> Unit,
    private val onNoBtnClick: () -> Unit
) : Dialog(context) {

    lateinit var binding: DialogLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }

    private fun setUpDialogUi() {
        binding = DialogLoginBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
//        val displayMetrics = context.resources.displayMetrics
//        val height = displayMetrics.heightPixels
//        val mBehavior: BottomSheetBehavior<*> =
//            BottomSheetBehavior.from(binding.root.parent as View)
//        mBehavior.peekHeight = height

        binding.yesBtn.setOnClickListener {
            onYesBtnClick()
            dismiss()
        }
        binding.noBtn.setOnClickListener {
            onNoBtnClick()
            dismiss()
        }
    }
}