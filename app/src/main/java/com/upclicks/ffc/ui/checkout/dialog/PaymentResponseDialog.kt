package com.upclicks.ffc.ui.checkout.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.upclicks.ffc.databinding.DialogPaymentResponseBinding

class PaymentResponseDialog(
    context: Context,
    var message: String,
    private val onOkBtnClick: () -> Unit
) : Dialog(context) {

    lateinit var binding: DialogPaymentResponseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }

    private fun setUpDialogUi() {
        binding = DialogPaymentResponseBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        binding.msg = message
        binding.okBtn.setOnClickListener {
            dismiss()
            onOkBtnClick()
        }
    }
}