package com.upclicks.ffc.ui.general.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
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
import com.upclicks.ffc.socket.api.SocketController
import com.upclicks.ffc.ui.main.SplashActivity

class ChangeLanguageDialog(
    var mContext: Context,
    var sessionHelper: SessionHelper
) : Dialog(mContext),SessionHelper.OnSessionUpdate{

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
        if (sessionHelper.isEnglish(mContext)){
            binding.englishIv.visibility = View.VISIBLE
            binding.arabicIv.visibility = View.INVISIBLE
        }else{
            binding.arabicIv.visibility = View.VISIBLE
            binding.englishIv.visibility = View.INVISIBLE
        }
        binding.arabicLy.setOnClickListener {
            binding.arabicIv.visibility = View.VISIBLE
            binding.englishIv.visibility = View.INVISIBLE
            dismiss()
            sessionHelper.setLanguageArabic(this)
        }
        binding.englishLy.setOnClickListener {
            binding.englishIv.visibility = View.VISIBLE
            binding.arabicIv.visibility = View.INVISIBLE
            dismiss()
            sessionHelper.setLanguageEnglish(this)
        }
        binding.closeIv.setOnClickListener {
            dismiss()
        }
    }

    override fun refreshActivity() {
        (mContext as Activity).startActivity(Intent(context,SplashActivity::class.java))
        (mContext as Activity).finishAffinity()
    }
}