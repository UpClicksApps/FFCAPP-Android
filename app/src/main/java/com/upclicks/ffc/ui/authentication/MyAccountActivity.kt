package com.upclicks.ffc.ui.authentication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.commons.ImageUtil
import com.upclicks.ffc.commons.Keys.Intent_Constants.PICK_FROM_GALLERY
import com.upclicks.ffc.databinding.ActivityMyAccountBinding
import com.upclicks.ffc.ui.authentication.model.request.UpdateProfileRequest
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import www.sanju.motiontoast.MotionToast
import java.io.File

class MyAccountActivity : BaseActivity() {

    lateinit var binding: ActivityMyAccountBinding
    private val accountViewModel: AccountViewModel by viewModels()

    var selectedFilePath: String? = null
    var docImage: MultipartBody.Part? = null

    override fun getLayoutResourceId(): View {
        binding = ActivityMyAccountBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    // main function for this page
    private fun initPage() {
        setUpToolbar()
        setUpPageActions()
        callProfile()
        binding.lifecycleOwner = this
    }

    private fun callProfile() {
        accountViewModel.getMyProfile { profile ->
            binding.profile = profile
        }
    }
    // set up toolbar like page title,back button...etc
    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.my_account)
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageActions() {
        binding.editBtn.setOnClickListener {
            updateProfile()
        }
        binding.userIv.setOnClickListener {
            updateAvatar()
        }
        binding.editProfileIv.setOnClickListener {
            binding.userIv.performClick()
        }
        binding.changePasswordCv.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
        binding.nameInput.typingCallback = object: BaseInput.TypingCallback{
            override fun onTyping(text: String) {

            }
        }
        binding.surnameInput.typingCallback = object: BaseInput.TypingCallback{
            override fun onTyping(text: String) {

            }
        }
        binding.addressInput.typingCallback = object: BaseInput.TypingCallback{
            override fun onTyping(text: String) {

            }
        }
        binding.dateInput.typingCallback = object: BaseInput.TypingCallback{
            override fun onTyping(text: String) {

            }
        }
    }


    private fun updateProfile() {
        if (binding.nameInput!!.text.isNullOrEmpty())
            binding.nameInput.error = ""
        else if (binding.surnameInput!!.text.isNullOrEmpty())
            binding.surnameInput.error = ""
        else if (binding.addressInput!!.text.isNullOrEmpty())
            binding.addressInput.error = ""
        else if (binding.dateInput!!.text.isNullOrEmpty())
            binding.dateInput.error = ""
        else {
            var updateProfileRequest = UpdateProfileRequest()
            updateProfileRequest.name = binding.nameInput!!.text.toString()
            updateProfileRequest.surname = binding.surnameInput!!.text.toString()
            updateProfileRequest.address = binding.addressInput!!.text.toString()
            updateProfileRequest.birthdate = binding.dateInput!!.dateWillSendToServer
            accountViewModel.updateProfile(updateProfileRequest, onProfileUpdated = {
                shoMsg(it, MotionToast.TOAST_SUCCESS)
            })
        }
    }

    private fun updateAvatar() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        } else {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // Start the Intent
            startActivityForResult(galleryIntent, PICK_FROM_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FROM_GALLERY) {
            if (resultCode == RESULT_OK) {
                val selectedFileUri = data!!.data
                selectedFilePath = ImageUtil.getPath(this, selectedFileUri)
                Log.i("TAG", "Selected File Path:$selectedFilePath")
                val selectedImage = data!!.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                // Get the cursor
                val cursor: Cursor = contentResolver
                    .query(selectedImage!!, filePathColumn, null, null, null)!!
                // Move to first row
                cursor.moveToFirst()
                if (selectedFilePath != null) {
                    val file = File(selectedFilePath)
                    docImage = MultipartBody.Part.createFormData(
                        "File",
                        file.name,
                        RequestBody.create("image*//*".toMediaTypeOrNull(), file)
                    )
                    if (docImage != null) {
                        accountViewModel.updateAvatar(
                            docImage!!,
                            onAvatarUpdated = {
                                shoMsg(it, MotionToast.TOAST_SUCCESS)
                                accountViewModel.updateAvatar(docImage!!, onAvatarUpdated = {
                                    shoMsg(it,MotionToast.TOAST_SUCCESS)
                                })
                                selectedFilePath = null
                                docImage = null
                            })
                    }
                }
            }
        }
    }

}