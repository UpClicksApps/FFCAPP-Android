package com.upclicks.ffc.ui.authentication

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.upclicks.ffc.R
import com.upclicks.ffc.architecture.BaseActivity
import com.upclicks.ffc.databinding.ActivityPersonalDetailsBinding
import com.upclicks.ffc.helpers.CameraGalleryHelper
import com.upclicks.ffc.helpers.imageBinding
import com.upclicks.ffc.ui.authentication.model.request.UpdateProfileRequest
import com.upclicks.ffc.ui.authentication.model.response.VerifySession
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.CustomInputHelper
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection
import com.upclicks.ffc.ui.general.component.spinner.CustomSpinner
import com.upclicks.ffc.ui.general.dialog.CameraGalleryDialog
import www.sanju.motiontoast.MotionToast

class PersonalDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityPersonalDetailsBinding
    private val accountViewModel: AccountViewModel by viewModels()
    var governorateId = ""
    var profile = VerifySession.Profile()
    override fun getLayoutResourceId(): View {
        binding = ActivityPersonalDetailsBinding.inflate(layoutInflater)
        initPage()
        return binding.root
    }

    private fun initPage() {
        setUpToolbar()
        setUpPageAction()
        observeMyProfile()
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = this
    }

    private fun getCities(governorateId: String) {
        accountViewModel.getCities(governorateId) { cities ->
            if (!cities.isNullOrEmpty()) {
                if (profile.cityId != null && profile.cityId!!.isNotEmpty()) {
                    binding.citySP.setSelectionList(cities, profile.cityId!!)
                    binding.citySP.setSelectionItemById(profile.cityId!!)
                }
            } else {
                binding.citySP.setSelectionList(cities)
            }
        }
    }

    private fun getGovernorates() {
        accountViewModel.getGovernorates { governorates ->
            if (!governorates.isNullOrEmpty()) {
                if (profile.governorateId != null && profile.governorateId!!.isNotEmpty()) {
                    binding.governorateSP.setSelectionList(governorates)
                    binding.governorateSP.setSelectionItemById(profile.governorateId!!)
                    getCities(profile.governorateId!!)
                } else {
                    binding.governorateSP.setSelectionList(governorates)
                    getCities(governorates[0].id!!)
                }
            }
        }
    }

    private fun observeMyProfile() {
        accountViewModel.getMyProfile { profile ->
            if (profile != null) {
                this.profile = profile
                imageBinding(binding.imageProfile, profile.avatarPath)
                binding.nameInput.setText(profile.name)
                binding.surnameInput.setText(profile.surname)
                binding.phoneInput.setText(profile.phoneNumber)
                getGovernorates()
            }
        }
    }


    private fun setUpToolbar() {
        binding.toolbar.titleTv.text = getString(R.string.personal_details)
        binding.toolbar.backIv.visibility = View.VISIBLE
        binding.toolbar.backIv.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpPageAction() {
        binding.governorateSP.setCustomSelectionCallback(object : CustomSpinner.SelectionCallback {
            override fun onItemSelected(baseSelection: BaseSelection) {
                getCities(baseSelection.id!!)
            }
        })
        binding.citySP.setCustomSelectionCallback(object : CustomSpinner.SelectionCallback {
            override fun onItemSelected(baseSelection: BaseSelection) {
            }
        })
        binding.imageProfileEdit.setOnClickListener {
            CameraGalleryDialog(this,
                onCameraClick = {
                    CameraGalleryHelper.openCamera(this)
                }, onGalleryClick = {
                    CameraGalleryHelper.openGallery(this)
                }).show()
        }
        CustomInputHelper.setUpInputsTypingCallback(createInputViewsList())

        binding.saveBtn.setOnClickListener {
            save()
        }
    }

    private fun save() {
        if (CustomInputHelper.checkIfInputsIsValid(this, createInputViewsList())) {
            var updateProfileRequest = UpdateProfileRequest()
            updateProfileRequest.cityId = binding.citySP.baseSelection.id
            updateProfileRequest.governorateId = binding.governorateSP.baseSelection.id
            updateProfileRequest.name = binding.nameInput.text.toString()
            updateProfileRequest.surname = binding.surnameInput.text.toString()
            updateProfileRequest.phoneNumber = binding.phoneInput.text.toString()
            accountViewModel.updateProfile(updateProfileRequest, onProfileUpdated = { message ->
                shoMsg(message, MotionToast.TOAST_SUCCESS)

            })
        }
    }

    // create lis of inputs view
    private fun createInputViewsList(): ArrayList<BaseInput> {
        var inputsList = ArrayList<BaseInput>()
        inputsList.add(binding.nameInput)
        inputsList.add(binding.surnameInput)
        inputsList.add(binding.phoneInput)
        return inputsList
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!!.data != null) {
            accountViewModel.updateAvatar(CameraGalleryHelper.getImageSelectionMultipart(
                this,
                data.data!!
            ),
                onAvatarUpdated = { result ->
                    Log.e("UpdateAvatar", result)
                })
        }
    }
}