package com.upclicks.ffcapp.repositories;
import com.upclicks.ffcapp.data.ApiService;
import com.upclicks.ffcapp.models.Result;
import com.upclicks.ffcapp.models.requests.LoginRequest;
import com.upclicks.ffcapp.models.requests.SignUpRequest;
import com.upclicks.ffcapp.models.requests.UpdateProfileRequest;
import com.upclicks.ffcapp.models.response.City;
import com.upclicks.ffcapp.models.response.Profile;
import com.upclicks.ffcapp.session.UserSession;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;

public class AccountRepository {
    private ApiService apiService;

    @Inject
    public AccountRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    //Login
    public Observable<Result<UserSession>> login(LoginRequest loginRequest){
        return apiService.login(loginRequest);
    }
    //SignUp
    public Observable<Result<UserSession>> signUp(SignUpRequest signUpRequest){
        return apiService.signUp(signUpRequest);
    }
    //Update Profile
    public Observable<Result<String>> updateProfile(UpdateProfileRequest updateProfileRequest){
        return apiService.updateProfile(updateProfileRequest);
    }
    //Get Cites
    public Observable<Result<List<City>>> getCites(){
        return apiService.getCites();
    }
    //Get Profile
    public Observable<Result<Profile>> getUserProfile(){
        return apiService.getUserProfile();
    }
    //Update Avatar
    public Observable<Result<String>> updateAvatar(MultipartBody.Part avatar){
        return apiService.updateAvatar(avatar);
    }
    //Change Password
    public Observable<Result<UserSession>> changePassword (Object o){
        return apiService.changePassword(o);
    }
}
