package com.upclicks.ffcapp.data;

import com.upclicks.ffcapp.models.Result;
import com.upclicks.ffcapp.models.requests.LoginRequest;
import com.upclicks.ffcapp.models.requests.SignUpRequest;
import com.upclicks.ffcapp.models.requests.UpdateProfileRequest;
import com.upclicks.ffcapp.models.response.Category;
import com.upclicks.ffcapp.models.response.City;
import com.upclicks.ffcapp.models.response.Profile;
import com.upclicks.ffcapp.session.UserSession;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    //Authenticate
    @POST("account/Authenticate")
    Observable<Result<UserSession>> login(@Body LoginRequest body);
    //Sign up
    @POST("account/Signup")
    Observable<Result<UserSession>> signUp(@Body SignUpRequest body);
    //Get User profile
    @POST("services/app/member/GetMyProfile")
    Observable<Result<Profile>> getUserProfile();
    //Get Cites
    @POST("services/app/city/getCities")
    Observable<Result<List<City>>> getCites();
    //Update profile
    @POST("services/app/member/UpdateProfile")
    Observable<Result<String>> updateProfile(@Body UpdateProfileRequest body);
    // Update avatar
    @Multipart
    @POST("services/app/member/UpdateAvatar")
    Observable<Result<String>> updateAvatar(@Part MultipartBody.Part avatar);
    //Change Password
    @POST("services/app/member/ChangePassword")
    Observable<Result<UserSession>> changePassword( @Body Object object);
    //Get Categories
    @POST("services/app/category/getCategories")
    Observable<Result<List<Category>>> getCategories();
    //Register Device
    @POST("services/app/RegisteredDevice/reset")
    Observable<Result<Boolean>> registerDevice();

}
