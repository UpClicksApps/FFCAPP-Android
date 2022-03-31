package com.upclicks.ffcapp.viewModels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.upclicks.ffcapp.base.BaseViewModel;
import com.upclicks.ffcapp.data.CustomRxObserver;
import com.upclicks.ffcapp.models.Result;
import com.upclicks.ffcapp.models.requests.LoginRequest;
import com.upclicks.ffcapp.models.requests.SignUpRequest;
import com.upclicks.ffcapp.models.requests.UpdateProfileRequest;
import com.upclicks.ffcapp.models.response.City;
import com.upclicks.ffcapp.models.response.Profile;
import com.upclicks.ffcapp.repositories.AccountRepository;
import com.upclicks.ffcapp.session.UserSession;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class AccountViewModel extends BaseViewModel {
    private AccountRepository accountRepository;
    private MutableLiveData<String> updateProfileLive = new MutableLiveData<>();
    private MutableLiveData<UserSession> userSessionMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<City>> citesLiveData = new MutableLiveData<>();
    private MutableLiveData<Profile> userProfile = new MutableLiveData<>();
    private MutableLiveData<String> avatarUpdateRes = new MutableLiveData<>();
    private MutableLiveData<UserSession> changingPasswordResponse = new MutableLiveData<>();

    @ViewModelInject
    public AccountViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public LiveData<UserSession> observeLogin(){
        return userSessionMutableLiveData;
    }
    public LiveData<UserSession> observeSignUp(){
        return userSessionMutableLiveData;
    }
    public LiveData<List<City>> observeCites(){return citesLiveData;}
    public LiveData<String> observeUpdateProfile (){
        return updateProfileLive;
    }
    public LiveData<Profile> observeUserProfile(){return userProfile;}
    public LiveData<String> observeUpdatingAvatar(){return avatarUpdateRes;}
    public LiveData<UserSession> observeChangingPassword(){
        return changingPasswordResponse;
    }
    //Login
    public void login(LoginRequest loginRequest){
        loading.postValue(true);
        accountRepository.login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        userSessionMutableLiveData.postValue((UserSession) response.getResult());
                    }
                });
    }
    //Sign Up
    public void signUp(SignUpRequest signUpRequest){
        loading.postValue(true);
        accountRepository.signUp(signUpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        userSessionMutableLiveData.postValue((UserSession) response.getResult());
                    }
                });
    }
    //Get Profile
    public void getUserProfile(){
        loading.postValue(true);
        accountRepository.getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        userProfile.postValue((Profile) response.getResult());
                    }
                });
    }
    //Get Cites
    public void getCites(){
        accountRepository.getCites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        citesLiveData.postValue((List<City>) response.getResult());
                    }
                });
    }
    //Update Profile
    public void updateProfile(UpdateProfileRequest updateProfileRequest){
        loading.postValue(true);
        accountRepository.updateProfile(updateProfileRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        updateProfileLive.postValue((String) response.getResult());
                    }
                });
    }
    //Update avatar
    public void updateAvatar(MultipartBody.Part avatar){
        loading.postValue(true);
        accountRepository.updateAvatar(avatar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        avatarUpdateRes.postValue((String) response.getResult());
                    }
                });
    }
    //Change password
    public void changePassword(String currentPassword , String newPassword){
        HashMap request = new HashMap();
        request.put("CurrentPassword",currentPassword);
        request.put("NewPassword",newPassword);
        loading.postValue(true);
        accountRepository.changePassword(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        changingPasswordResponse.postValue((UserSession) response.getResult());
                    }
                });
    }
}
