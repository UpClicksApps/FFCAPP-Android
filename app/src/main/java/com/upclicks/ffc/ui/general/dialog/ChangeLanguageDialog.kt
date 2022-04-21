package com.upclicks.ffc.ui.general.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.upclicks.ffc.databinding.DialogChangeLanguageBinding
import com.upclicks.ffc.databinding.DialogConfirmBinding
import com.upclicks.ffc.session.SessionHelper

class ChangeLanguageDialog(
    context: Context,
    sessionHelper: SessionHelper
) : Dialog(context) {

    lateinit var binding: DialogChangeLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }

    private fun setUpDialogUi() {
        binding = DialogChangeLanguageBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window!!.setBackgroundDrawableResource(android.R.color.transparent)

        binding.arabicLy.setOnClickListener {
            binding.arabicIv.visibility = View.VISIBLE
            binding.englishIv.visibility = View.INVISIBLE
        }
        binding.englishLy.setOnClickListener {
            binding.englishIv.visibility = View.VISIBLE
            binding.arabicIv.visibility = View.INVISIBLE
        }
        binding.closeIv.setOnClickListener {
            dismiss()
        }
    }
}