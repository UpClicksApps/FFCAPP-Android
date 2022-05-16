package com.upclicks.ffc.data.remote

import com.upclicks.ffc.ui.authentication.model.Session
import com.upclicks.ffc.ui.authentication.model.request.ChangePasswordRequest
import com.upclicks.ffc.ui.authentication.model.request.ResetPasswordRequest
import com.upclicks.ffc.ui.authentication.model.response.ForgotPasswordResponse
import com.upclicks.ffc.ui.authentication.model.response.IdentityVerificationStatus
import com.upclicks.ffc.ui.authentication.model.response.MembershipResponse
import com.upclicks.ffc.ui.general.model.City
import com.upclicks.ffc.ui.general.model.Country
import com.upclicks.ffc.ui.authentication.model.response.VerifySession
import com.upclicks.ffc.ui.general.model.FeedbackRequest
import com.upclicks.ffc.ui.general.model.Category
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import retrofit2.http.*
import com.upclicks.ffc.ui.notification.data.model.Notification
import com.upclicks.ffc.data.remote.Result
import com.upclicks.ffc.ui.authentication.model.request.ValidateResetPasswordCodeRequest

interface ApiService {
    @POST("TokenAuth/Authenticate")
    fun authenticate(@Body req: Any?): Observable<Result<Session>>

    //Create Membership
    @POST("services/app/Member/CreateMembership")
    fun createMembership(@Body req: Any?): Observable<Result<MembershipResponse>>

    //Verify Membership
    @POST("services/app/Member/VerifyMembership")
    fun verifyMembership(
        @Query("membershipIdentity") membershipIdentity: String,
        @Query("verificationCode") verificationCode: String
    ): Observable<Result<Boolean>>

    //Validate reset password code
    @POST("services/app/Account/ValidateResetPasswordCode")
    fun validateResetPasswordCode(@Body validateResetPasswordCodeRequest : ValidateResetPasswordCodeRequest): Observable<Result<Boolean>>

    //Verify Session
    @POST("services/app/session/Verify")
    fun verifySession(): Observable<Result<VerifySession>>

    //Get Countries
    @GET("services/app/Country/GetAll")
    fun getCountries(): Observable<Result<List<Country>>>

    //Get Cities
    @GET("services/app/City/GetAll")
    fun getCities(): Observable<Result<List<City>>>

    //Complete profile
    @POST("services/app/Member/CompleteProfile")
    fun completeProfile(@Body req: Any?): Observable<Result<String>>

    //Update Avatar
    @Multipart
    @PUT("services/app/Member/UpdateAvatar")
    fun updateAvatar(@Part avatar: MultipartBody.Part): Observable<Result<String>>

    //Update profile
    @PUT("services/app/Member/UpdateProfile")
    fun updateProfile(@Body req: Any?): Observable<Result<String>>

    //Verify account identity
    @POST("services/app/Member/RequestIdentityVerification")
    fun verifyAccountIdentity(@Body req: Any?): Observable<Result<String>>

    //Charge Balance
    @Multipart
    @POST("services/app/RechargeBalanceRequest/Create")
    fun rechargeBalance(
        @Part image: MultipartBody.Part,
        @Part balance: MultipartBody.Part
    ): Observable<Result<String>>

    //Get Identity verification status
    @GET("services/app/Member/GetMyIdentityVerificationRequest")
    fun getIdentityVerificationStatus(@Query("notifyId") notifyId: String): Observable<Result<IdentityVerificationStatus>>

    //Get my profile
    @GET("services/app/Member/GetMyProfile")
    fun getMyProfile(): Observable<Result<VerifySession.Profile>>

    //Change password
    @POST("services/app/User/ChangePassword")
    fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Observable<Result<Boolean>>

    //Forgot password
    @POST("services/app/Account/ForgetPassword")
    fun forgotPassword(@Query("emailAddress") emailAddress: String): Observable<Result<ForgotPasswordResponse>>

    //Reset password
    @POST("services/app/Account/ResetPassword")
    fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Observable<Result<Boolean>>

    //Get Categories
    @GET("services/app/Category/GetPinned")
    fun getCategories(): Observable<Result<List<Category>>>

//    //Get salons(providers)
//    @GET("services/app/Provider/GetProviders")
//    fun getSalons(
//        @Query("categoryId") categoryId: String,
//        @Query("servicePlace") servicePlace: Int,
//        @Query("name") name: String,
//        @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<Salon>>>
//
//    //Get salon(provider)
//    @GET("services/app/Provider/GetProvider")
//    fun getSalon(
//        @Query("id") providerId: String
//    ): Observable<Result<SalonDetails>>
//
//    //Get salons on map(providers)
//    @GET("services/app/Provider/GetNearBy")
//    fun getSalonsOnMap(
//        @Query("lat") lat: Double,
//        @Query("lng") lng: Double
//    ): Observable<Result<List<Salon>>>
//
//    //Get Offers
//    @GET("services/app/Provider/GetOffers")
//    fun getSalonOffers(
//        @Query("providerId") providerId: String, @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<Order>>>
//
//    //Get Offers
//    @GET("services/app/Provider/GetOffers")
//    fun getSpecialOffers(
//        @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<Order>>>
//
//    //Get Order
//    @GET("services/app/Provider/GetOffer")
//    fun getOffer(
//        @Query("id") id: String,
//        @Query("notifyId") notifyId: String
//    ): Observable<Result<Order>>
//
//    //Get Specialist
//    @GET("services/app/Provider/GetAvailableTimesAndSpecialists")
//    fun getAvailableTimesAndSpecialists(
//        @Query("providerId") providerId: String, @Query("Date") skip: String
//    ): Observable<Result<SpecialistResponse>>
//
//    //Get services
//    @GET("services/app/Provider/GetServices")
//    fun getSalonServices(
//        @Query("providerId") providerId: String, @Query("servicePlace") servicePlace: Int,
//        @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<ServiceResponse>>>
//
//    //Get salon product
//    @GET("services/app/Provider/GetProducts")
//    fun getSalonProducts(
//        @Query("providerId") providerId: String, @Query("servicePlace") servicePlace: Int,
//        @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<ProductResponse>>>
//
//    //Get salon product
//    @POST("services/app/Provider/AssignToWishlist")
//    fun assignToWishlist(
//        @Query("providerId") providerId: String
//    ): Observable<Result<String>>
//
//    //make checkout
//    @POST("services/app/Order/Checkout")
//    fun checkout(
//        @Body() checkoutRequest: CheckoutRequest
//    ): Observable<Result<String>>
//
//    //validate checkout order items
//    @POST("services/app/Order/CalculateTotals")
//    fun calculateTotals(
//        @Body() totalsRequest: TotalsRequest
//    ): Observable<Result<TotalsResponse>>
//
//    //Get MyOrders
//    @GET("services/app/Order/GetMyOrders")
//    fun getMyOrders(
//        @Query("appointmentsStatus") AppointmentsStatus: Int,
//        @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<Appointment>>>
//
//    //Get order
//    @GET("services/app/Order/GetOrder")
//    fun getOrder(
//        @Query("id") id: String,
//        @Query("notifyId") notifyId: String
//    ): Observable<Result<Appointment>>

    //reschedule order
    @POST("services/app/Order/Reschedule")
    fun reschedule(
        @Query("id") id: String,
        @Query("date") date: String
    ): Observable<Result<String>>

    //cancel order
    @POST("services/app/Order/Cancel")
    fun cancel(
        @Query("id") id: String
    ): Observable<Result<String>>

// //review order
//    @POST("services/app/Order/Review")
//    fun reviewOrder(
//     @Body() reviewRequest: ReviewRequest
//    ): Observable<Result<String>>
//
//    //Get Wishlist
//    @GET("services/app/Member/GetMemberWishlist")
//    fun getWishlist(
//        @Query("skip") skip: Int,
//        @Query("take") take: Int
//    ): Observable<Result<List<Salon>>>
//
//
//
//    //list your business
//    @POST("services/app/ProviderJoiningRequest/Create")
//    fun listYourBusiness(
//        @Body() joinRequest: JoinRequest
//    ): Observable<Result<String>>


    //list your business
    @POST("services/app/ContactMessage/Create")
    fun sendFeedback(
        @Body() feedbackRequest: FeedbackRequest
    ): Observable<Result<String>>




    /////////////////////////////////////////
    //notification
    //Notifications
    //Get Notifications
    @GET("services/app/SystemNotification/GetMyNotifications")
    open fun callNotificationsList(
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Observable<Result<List<Notification>>>

    //Read notification
    @POST("services/app/SystemNotification/SetNotificationAsRead")
    fun callReadNotification(@Body id: Any): Observable<Result<String>>

    //Read all notifications
    @POST("services/app/SystemNotification/SetAllNotificationsAsRead")
    fun callReadAllNotifications(): Observable<Result<String>>

    //Delete notification
    @DELETE("services/app/SystemNotification/DeleteUserNotification")
    fun callDeleteNotification(@Query("id") id: String): Observable<Result<String>>

    //Delete all notifications
    @DELETE("services/app/SystemNotification/DeleteAllMyNotifications")
    fun callDeleteAllNotifications(): Observable<Result<String>>
}