package com.upclicks.ffcapp.viewModels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.upclicks.ffcapp.base.BaseViewModel;
import com.upclicks.ffcapp.data.CustomRxObserver;
import com.upclicks.ffcapp.models.Result;
import com.upclicks.ffcapp.repositories.SettingsRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SettingsViewModel extends BaseViewModel {
    SettingsRepository settingsRepository;
    MutableLiveData<Boolean> appSettings = new MutableLiveData<>();
    public LiveData<Boolean> observeAppSettings(){
        return appSettings;
    }
    @ViewModelInject
    public SettingsViewModel(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public void callAppSettings(){
        settingsRepository.registerDevice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver(this) {
                    @Override
                    public void onResponse(Result<Object> response) {
                        appSettings.postValue((Boolean) response.getResult());
                    }
                });
    }

}
