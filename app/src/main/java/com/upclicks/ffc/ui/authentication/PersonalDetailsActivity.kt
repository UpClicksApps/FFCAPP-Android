package com.upclicks.ffc.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.upclicks.ffc.R
import com.upclicks.ffc.base.BaseActivity
import com.upclicks.ffc.databinding.ActivityFavoriteBinding
import com.upclicks.ffc.databinding.ActivityPersonalDetailsBinding
import com.upclicks.ffc.helpers.CameraGalleryHelper
import com.upclicks.ffc.ui.authentication.model.response.VerifySession
import com.upclicks.ffc.ui.authentication.viewmodel.AccountViewModel
import com.upclicks.ffc.ui.general.component.CustomInputHelper
import com.upclicks.ffc.ui.general.component.customedittext.BaseInput
import com.upclicks.ffc.ui.general.component.material.CustomMaterialInputLayout
import com.upclicks.ffc.ui.general.component.spinner.BaseSelection
import com.upclicks.ffc.ui.general.component.spinner.CustomSpinner
import com.upclicks.ffc.ui.general.dialog.CameraGalleryDialog
import com.upclicks.ffc.ui.products.adapter.FavoriteAdapter
import com.upclicks.ffc.ui.products.model.Product

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
        getGovernorates()
    }

    private fun getCities(governorateId:String,cityId:String) {
        accountViewModel.getCities(governorateId) {cities->
            if (!cities.isNullOrEmpty()){
                binding.citySP.setSelectionList(cities,cityId)
            }
        }
    }
    private fun getGovernorates() {
        accountViewModel.getGovernorates {governorates->
            if (!governorates.isNullOrEmpty()){
                binding.governorateSP.setSelectionList(governorates,governorateId)
            }
        }
    }
    private fun observeMyProfile() {


        accountViewModel.getMyProfile { profile ->
            if (profile != null) {
                this.profile = profile
                binding.nameInput.setText(profile.name)
                binding.surnameInput.setText(profile.surname)
                binding.phoneInput.setText(profile.phoneNumber)
                if (profile.cityId != null && profile.cityId!!.isNotEmpty())
                    binding.citySP.setSelectionItemById(profile.cityId!!)
                if (profile.governorateId != null && profile.governorateId!!.isNotEmpty())
                    binding.governorateSP.setSelectionItemById(profile.governorateId!!)
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