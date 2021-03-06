package com.upclicks.ffc.data.remote

import com.upclicks.ffc.ui.authentication.model.Session
import com.upclicks.ffc.ui.authentication.model.request.ChangePasswordRequest
import com.upclicks.ffc.ui.authentication.model.request.ResetPasswordRequest
import com.upclicks.ffc.ui.authentication.model.response.ForgotPasswordResponse
import com.upclicks.ffc.ui.authentication.model.response.IdentityVerificationStatus
import com.upclicks.ffc.ui.authentication.model.response.MembershipResponse
import com.upclicks.ffc.ui.authentication.model.response.VerifySession
import io.reactivex.rxjava3.core.Observable
import okhttp3.MultipartBody
import retrofit2.http.*
import com.upclicks.ffc.ui.notification.data.model.Notification
import com.upclicks.ffc.ui.authentication.model.request.ValidateResetPasswordCodeRequest
import com.upclicks.ffc.ui.cart.model.CartActionResponse
import com.upclicks.ffc.ui.cart.model.CartDetails
import com.upclicks.ffc.ui.chat.data.model.*
import com.upclicks.ffc.ui.checkout.model.CheckoutRequest
import com.upclicks.ffc.ui.checkout.model.CheckoutResponse
import com.upclicks.ffc.ui.checkout.model.DeliveryTimeResponse
import com.upclicks.ffc.ui.checkout.model.PaymentResponse
import com.upclicks.ffc.ui.general.model.*
import com.upclicks.ffc.ui.general.slider.model.Slider
import com.upclicks.ffc.ui.orders.model.OrderDetails
import com.upclicks.ffc.ui.orders.model.Order
import com.upclicks.ffc.ui.products.model.*
import okhttp3.RequestBody

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


    //Get All Faq
    @GET("services/app/FAQ/GetAll")
    fun getAllFaq(
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Observable<Result<List<Faq>>>


    //Validate reset password code
    @POST("services/app/Account/ValidateResetPasswordCode")
    fun validateResetPasswordCode(@Body validateResetPasswordCodeRequest: ValidateResetPasswordCodeRequest): Observable<Result<Boolean>>

    //Verify Session
    @POST("services/app/session/Verify")
    fun verifySession(): Observable<Result<VerifySession>>

    //Get Countries
    @GET("services/app/Country/GetAll")
    fun getCountries(): Observable<Result<List<Country>>>

    //Get Countries
    @GET("services/app/Governorate/GetAll")
    fun getGovernorates(): Observable<Result<List<Governorate>>>

    //Get Cities
    @GET("services/app/City/GetAll")
    fun getCities(
        @Query("governorateId") governorateId: String
    ): Observable<Result<List<City>>>

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
    @GET("services/app/Category/GetPinnedCategories")
    fun getCategories(): Observable<Result<List<Category>>>

    //Get Categories
    @GET("services/app/Category/GetAll")
    fun getAllCategories(
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Observable<Result<List<Category>>>

    //Get Top Sales
    @GET("services/app/Product/GetOnSaleProducts")
    fun getTopSales(): Observable<Result<List<Product>>>

    //Get Top Sales
    @GET("services/app/Category/GetHomeCategories")
    fun getHomeCategories(): Observable<Result<List<HomeProduct>>>

    //Get Products
    @GET("services/app/Product/GetProducts")
    fun getProducts(
        @Query("categoryId") categoryId: String,
        @Query("productName") productName: String,
        @Query("sortProductBy") sortProductBy: Int,
        @Query("minPrice") minPrice: Float,
        @Query("maxPrice") maxPrice: Float,
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Observable<Result<ProductsResponse>>

    //Get Product
    @GET("services/app/Product/GetProduct")
    fun getProductDetails(@Query("id") id: String): Observable<Result<ProductDetails>>

    //Get MyWishlist
    @GET("services/app/MemberWishlist/GetMyWishlist")
    fun getMyWishlist(
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Observable<Result<List<Product>>>


    //Get HomeSliderForMobile
    @GET("services/app/PageSection/GetHomeSliderForMobile")
    fun getHomeSliderForMobile(
    ): Observable<Result<List<Slider>>>



    //Get HomeSliderForMobile
    @POST("services/app/PageSection/mainAdBanner")
    fun geMainAdBanner(
    ): Observable<Result<Slider>>


    //Assign
    @POST("services/app/MemberWishlist/Assign")
    fun assign(@Body body: Any): Observable<Result<String>>


    ////////////// order
    //Get MyOrders
    @GET("services/app/Order/GetMyOrders")
    fun getMyOrders(
        @Query("orderStatus") orderStatus: Int,
        @Query("skip") skip: Int,
        @Query("take") take: Int
    ): Observable<Result<List<Order>>>

    //Get Order
    @GET("services/app/Order/GetOrder")
    fun getOrder(
        @Query("id") OrderId: String,
        @Query("notifyId") notifyId: String
    ): Observable<Result<OrderDetails>>

    //Cancel Order
    @POST("services/app/Order/Cancel")
    fun cancelOrder(
        @Query("id") id: String
    ): Observable<Result<String>>


    ///////////////// cart
    //GetCurrentCartDetails
    @GET("services/app/Cart/GetCurrentCartDetails")
        fun getCurrentCartDetails(@Query("CouponCode") couponId: String): Observable<Result<CartDetails>>

    //Add Product
    @POST("services/app/Cart/AddProduct")
    fun addProductToCart(@Body req: Any?): Observable<Result<CartActionResponse>>

    //UpdateProductQuantity
    @PUT("services/app/Cart/UpdateProductQuantity")
    fun updateProductQuantity(@Body req: Any?): Observable<Result<CartActionResponse>>

    //RemoveProduct
    @DELETE("services/app/Cart/RemoveProduct")
    fun removeProductFromCart(
        @Query("productId") id: String
    ): Observable<Result<CartActionResponse>>

    @POST("services/app/Cart/DeleteShoppingCart")
    fun deleteShoppingCart(): Observable<Result<String>>

    /////////////// checkout

    //Get Available Delivery Times
    @GET("services/app/DeliveryTime/GetAvailableDeliveryTimes")
    fun getAvailableDeliveryTimes(): Observable<Result<DeliveryTimeResponse>>

    //make checkout
    @POST("services/app/Cart/Checkout")
    fun checkout(
        @Body() checkoutRequest: CheckoutRequest
    ): Observable<Result<CheckoutResponse>>

    //check payment online
    @POST("services/app/Order/CheckOnlinePaymentRequest")
    fun checkPaymentOnline(
        @Query("oId") orderId: String
    ): Observable<Result<PaymentResponse>>

    //reschedule Order
    @POST("services/app/Order/Reschedule")
    fun reschedule(
        @Query("id") id: String,
        @Query("date") date: String
    ): Observable<Result<String>>

    //list your business
    @POST("services/app/ContactMessage/Create")
    fun contactUs(
        @Body() contactUsRequest: ContactUsRequest
    ): Observable<Result<String>>


    /////////////////////////////////////////
    //************* Notification Module **************

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

    //************* End Of Notification Module **************


    //************* Chats Module **************

    //Get chat messages
    @GET("services/app/CustomerServiceChat/GetMessages")
    fun getChatMessages(
        @Query("memberId") memberId: String,
        @Query("lastMessageId") lastMessageId: String,
        @Query("take") take: Int
    ): Observable<Result<List<Message>>>

    //************* End of Chats Module **************


    //************* Settings **************

    @GET("services/app/Setting/GetByKey")
    open fun getAboutUs(
        @Query("key") skip: String
    ): Observable<Result<String>>
}