package com.upclicks.ffc.ui.authentication.repositories

import com.upclicks.ffc.data.remote.ApiService
import com.upclicks.ffc.ui.authentication.model.response.*
import com.upclicks.ffc.ui.authentication.model.Session
import com.upclicks.ffc.ui.general.model.City
import com.upclicks.ffc.ui.general.model.Country
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MultipartBody
import javax.inject.Inject
import com.upclicks.ffc.ui.authentication.model.request.*
import com.upclicks.ffc.ui.general.model.FeedbackRequest
import com.upclicks.ffc.data.remote.Result

@ExperimentalCoroutinesApi
class AccountRepository @Inject constructor(private val apiService: ApiService) {


    //Get Countries
    fun getCountries() : Observable<Result<List<Country>>> {
        return apiService.getCountries()
    }

    //Get Cities
    fun getCities() : Observable<Result<List<City>>> {
        return apiService.getCities()
    }


    //Create membership
    fun createMembership(createMemberShipRequest : CreateMemberShipRequest) : Observable<Result<MembershipResponse>> {
        return apiService.createMembership(createMemberShipRequest)
    }

    //Create membership
    fun validateResetPasswordCode(validateResetPasswordCodeRequest : ValidateResetPasswordCodeRequest) : Observable<Result<Boolean>> {
        return apiService.validateResetPasswordCode(validateResetPasswordCodeRequest)
    }

    //Verify membership
    fun verifyMembership(membershipIdentity : String, verificationCode : String) : Observable<Result<Boolean>> {
        return apiService.verifyMembership(membershipIdentity,verificationCode)
    }

    //Login
    fun login(loginRequest: LoginRequest): Observable<Result<Session>> {
        return apiService.authenticate(loginRequest)
    }

    //Verify Session
    fun verifySession(): Observable<Result<VerifySession>> {
        return apiService.verifySession()
    }

    //Complete profile
    fun completeProfile(completeProfileRequest: CompleteProfileRequest): Observable<Result<String>> {
        return apiService.completeProfile(completeProfileRequest)
    }

    //Update avatar
    fun updateAvatar(avatar: MultipartBody.Part): Observable<Result<String>> {
        return apiService.updateAvatar(avatar)
    }

    //Update profile
    fun updateProfile(updateProfileRequest: UpdateProfileRequest) : Observable<Result<String>> {
        return apiService.updateProfile(updateProfileRequest)
    }

    //Get my profile
    fun getMyProfile() : Observable<Result<VerifySession.Profile>> {
        return apiService.getMyProfile()
    }

    //Change Password
    fun changePassword(changePasswordRequest: ChangePasswordRequest) : Observable<Result<Boolean>> {
        return apiService.changePassword(changePasswordRequest)
    }


    //Forgot Password
    fun forgotPassword(emailAddress : String) : Observable<Result<ForgotPasswordResponse>> {
        return apiService.forgotPassword(emailAddress)
    }


    //Reset Password
    fun resetPassword(resetPasswordRequest: ResetPasswordRequest) : Observable<Result<Boolean>> {
        return apiService.resetPassword(resetPasswordRequest)
    }

    //Verify Account Identity
    fun verifyAccountIdentity(verifyIdRequest: VerifyIDRequest) : Observable<Result<String>> {
        return apiService.verifyAccountIdentity(verifyIdRequest)
    }

    //Get my profile
    fun getIdentityVerificationStatus(notifyId : String) : Observable<Result<IdentityVerificationStatus>> {
        return apiService.getIdentityVerificationStatus(notifyId)
    }

    //sendFeedback
    fun sendFeedback(feedbackRequest: FeedbackRequest) : Observable<Result<String>> {
        return apiService.sendFeedback(feedbackRequest)
    }

}