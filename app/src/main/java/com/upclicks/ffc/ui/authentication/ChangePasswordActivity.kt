package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.activity.viewModels
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityChangePasswordBinding
import com.upclicks.ffc.ui.authentication.model.request.ChangePasswordRequest
import com.upclicks.ffc.ui.general.component.Validator
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import www.sanju.motiontoast.MotionToast

class ChangePasswordActivity : BaseActivity() {

    lateinit var binding: ActivityChangePasswordBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun getLayoutResourceId(): View {
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
        binding.lifecycleOwner = this
    }

    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.change_password)
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.saveBtn.setOnClickListener {
            save()
        }
    }

    private fun save() {
        if (isDataValid()) {
            var changePassword = ChangePasswordRequest()
            changePassword.currentPassword = binding.currentPasswordInput.editText!!.text.toString()
            changePassword.newPassword = binding.newPasswordInput.editText!!.text.toString()
            accountViewModel.changePassword(changePassword, onPasswordChanged = {
                if (it) {
                    shoMsg(
                        getString(R.string.password_changed_successfully),
                        MotionToast.TOAST_SUCCESS
                    )
                    sessionHelper.logout {
                        startActivity(Intent(this, LoginByEmailActivity::class.java))
                        finishAffinity()
                    }
                }
            })
        }
    }

    private fun isDataValid(): Boolean {
        var currentPassword = binding.currentPasswordInput.editText!!.text.toString()
        var newPassword = binding.newPasswordInput.editText!!.text.toString()
        var confirmNewPassword = binding.confirmNewPasswordInput.editText!!.text.toString()
        if (TextUtils.isEmpty(currentPassword)) {
            binding.currentPasswordInput.editText!!.startAnimation(Validator.shakeError())
            binding.currentPasswordInput.error = ""
            return false
        }
        if (TextUtils.isEmpty(newPassword)) {
            binding.newPasswordInput.editText!!.startAnimation(Validator.shakeError())
            binding.newPasswordInput.error = ""
            return false
        }
        if (TextUtils.isEmpty(confirmNewPassword)) {
            binding.confirmNewPasswordInput.editText!!.startAnimation(Validator.shakeError())
            binding.confirmNewPasswordInput.error = ""
            return false
        }
        if (newPassword != confirmNewPassword) {
            binding.newPasswordInput.editText!!.startAnimation(Validator.shakeError())
            binding.confirmNewPasswordInput.editText!!.startAnimation(Validator.shakeError())
            binding.confirmNewPasswordInput.error = ""
            return false
        }
        return true
    }
}