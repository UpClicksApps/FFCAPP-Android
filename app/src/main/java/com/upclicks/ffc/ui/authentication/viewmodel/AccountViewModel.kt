package com.upclicks.ffc.ui.authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upclicks.ffc.base.BaseViewModel
import com.upclicks.ffc.ui.authentication.model.response.*
import com.upclicks.ffc.rx.CustomRxObserver
import com.upclicks.ffc.ui.authentication.model.Session
import com.upclicks.ffc.ui.authentication.repositories.AccountRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MultipartBody
import javax.inject.Inject
import com.upclicks.ffc.ui.authentication.model.request.*
import com.upclicks.ffc.ui.general.model.City
import com.upclicks.ffc.ui.general.model.Country
import com.upclicks.ffc.ui.general.model.FeedbackRequest
import com.upclicks.ffc.data.remote.Result
import dagger.hilt.android.lifecycle.HiltViewModel


@ExperimentalCoroutinesApi
@HiltViewModel
class AccountViewModel
@Inject constructor(
    private val accountRepository: AccountRepository,
) : BaseViewModel() {
    private val _authResponse: MutableLiveData<Session> = MutableLiveData()
    private val _verifySessionResponse: MutableLiveData<VerifySession> = MutableLiveData()

    val authResponse: LiveData<Session>
        get() = _authResponse
    val observeVerifySession: LiveData<VerifySession>
        get() = _verifySessionResponse

    fun auth(loginRequest : LoginRequest) {
        loginRequest.rememberClient = true
        accountRepository.login(loginRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<Session>>(this@AccountViewModel) {
                override fun onResponse(response: Result<Session>) {
                    _authResponse.postValue(response!!.result)
                }
            })
    }


    //call Verify Session
    fun callVerifySession() {
        accountRepository.verifySession()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<VerifySession>>(this@AccountViewModel) {
                override fun onResponse(response: Result<VerifySession>) {
                    _verifySessionResponse?.postValue(response.result)
                }
            })
    }


    //Get Countries
    fun getCounties(onGetCountries: (List<Country>) -> Unit) {
        accountRepository.getCountries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<Country>>>(this@AccountViewModel) {
                override fun onResponse(response: Result<List<Country>>) {
                    onGetCountries(response?.result!!)
                }
            })
    }

    //Get Cities
    fun getCities(onGetCites: (List<City>) -> Unit) {
        accountRepository.getCities()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<List<City>>>(this@AccountViewModel) {
                override fun onResponse(response: Result<List<City>>) {
                    onGetCites(response.result!!)
                }
            })
    }

    //Create membership
    fun createMembershipRequest(
        memberShipRequest: CreateMemberShipRequest,
        onMembershipRequestCreated: (MembershipResponse) -> Unit
    ) {
        accountRepository.createMembership(memberShipRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :
                CustomRxObserver<Result<MembershipResponse>>(this@AccountViewModel) {
                override fun onResponse(response: Result<MembershipResponse>) {
                    onMembershipRequestCreated(response?.result!!)
                }
            })
    }

    //sendFeedback
    fun sendFeedback(
        feedbackRequest: FeedbackRequest,
        onResult: (String) -> Unit
    ) {
        accountRepository.sendFeedback(feedbackRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :
                CustomRxObserver<Result<String>>(this@AccountViewModel) {
                override fun onResponse(response: Result<String>) {
                    onResult(response?.result!!)
                }
            })
    }

    //Verify membership
    fun verifyMembership(
        membershipIdentity: String,
        verificationCode: String,
        onMembershipVerified: (Boolean) -> Unit
    ) {
        accountRepository.verifyMembership(membershipIdentity, verificationCode)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<Boolean>>(this@AccountViewModel) {
                override fun onResponse(response: Result<Boolean>) {
                    onMembershipVerified(response?.result!!)
                }
            })
    }


    //Complete profile
    fun completeProfile(
        completeProfileRequest: CompleteProfileRequest,
        onProfileCompleted: (String) -> Unit
    ) {
        accountRepository.completeProfile(completeProfileRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@AccountViewModel) {
                override fun onResponse(response: Result<String>) {
                    onProfileCompleted(response?.result!!)
                }
            })
    }


    //Update Avatar
    fun updateAvatar(avatar: MultipartBody.Part, onAvatarUpdated: (String) -> Unit) {
        accountRepository.updateAvatar(avatar)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@AccountViewModel) {
                override fun onResponse(response: Result<String>) {
                    onAvatarUpdated(response?.result!!)
                }
            })
    }


    //Update Profile
    fun updateProfile(updateRequest: UpdateProfileRequest, onProfileUpdated: (String) -> Unit) {
        accountRepository.updateProfile(updateRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@AccountViewModel) {
                override fun onResponse(response: Result<String>) {
                    onProfileUpdated(response?.result!!)
                }
            })
    }


    //Verify Account Identity
    fun verifyAccountIdentity(verifyIDRequest: VerifyIDRequest, onRequestSent: (String) -> Unit) {
        accountRepository.verifyAccountIdentity(verifyIDRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<String>>(this@AccountViewModel) {
                override fun onResponse(response: Result<String>) {
                    onRequestSent(response?.result!!)
                }
            })
    }

    //Get Identity verification status
    fun getIdentityVerificationStatus(
        notifyId: String,
        onStatusLoaded: (IdentityVerificationStatus) -> Unit
    ) {
        accountRepository.getIdentityVerificationStatus(notifyId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :
                CustomRxObserver<Result<IdentityVerificationStatus>>(this@AccountViewModel) {
                override fun onResponse(response: Result<IdentityVerificationStatus>) {
                    onStatusLoaded(response?.result!!)
                }
            })
    }

    //Get My Profile
    fun getMyProfile(onMyProfileLoaded: (VerifySession.Profile) -> Unit) {
        accountRepository.getMyProfile()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :
                CustomRxObserver<Result<VerifySession.Profile>>(this@AccountViewModel) {
                override fun onResponse(response: Result<VerifySession.Profile>) {
                    onMyProfileLoaded(response?.result!!)
                }
            })
    }

    //Change Password
    fun changePassword(
        changePasswordRequest: ChangePasswordRequest,
        onPasswordChanged: (Boolean) -> Unit
    ) {
        accountRepository.changePassword(changePasswordRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<Boolean>>(this@AccountViewModel) {
                override fun onResponse(response: Result<Boolean>) {
                    onPasswordChanged(response?.result!!)
                }
            })
    }

    //Forgot password
    fun forgotPassword(
        emailAddress: String,
        onForgotPasswordRequested: (ForgotPasswordResponse) -> Unit
    ) {
        accountRepository.forgotPassword(emailAddress)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :
                CustomRxObserver<Result<ForgotPasswordResponse>>(this@AccountViewModel) {
                override fun onResponse(response: Result<ForgotPasswordResponse>) {
                    onForgotPasswordRequested(response?.result!!)
                }
            })
    }

    //Reset password
    fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest,
        onPasswordReseated: (Boolean) -> Unit
    ) {
        accountRepository.resetPassword(resetPasswordRequest)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CustomRxObserver<Result<Boolean>>(this@AccountViewModel) {
                override fun onResponse(response: Result<Boolean>) {
                    onPasswordReseated(response?.result!!)
                }
            })
    }

}