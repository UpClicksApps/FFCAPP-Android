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
import com.upclicks.ffc.databinding.DialogCameraGalleryBinding
import com.upclicks.ffc.databinding.DialogConfirmBinding

class CameraGalleryDialog(
    context: Context,
    private val onCameraClick: () -> Unit,
    private val onGalleryClick: () -> Unit
) : Dialog(context) {

    lateinit var binding: DialogCameraGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDialogUi()
    }

    private fun setUpDialogUi() {
        binding = DialogCameraGalleryBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
//        val displayMetrics = context.resources.displayMetrics
//        val height = displayMetrics.heightPixels
//        val mBehavior: BottomSheetBehavior<*> =
//            BottomSheetBehavior.from(binding.root.parent as View)
//        mBehavior.peekHeight = height

        binding.cameraLy.setOnClickListener {
            onCameraClick()
            dismiss()
        }
        binding.galleryLy.setOnClickListener {
            onGalleryClick()
            dismiss()
        }

        binding.closeIv.setOnClickListener {
            dismiss()
        }

    }
}