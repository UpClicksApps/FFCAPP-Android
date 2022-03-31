package com.upclicks.ffcapp.repositories;

import com.upclicks.ffcapp.data.ApiService;
import com.upclicks.ffcapp.models.Result;


import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class SettingsRepository {
    private ApiService apiService;
    @Inject
    public SettingsRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Result<Boolean>> registerDevice(){
        return apiService.registerDevice();
    }
}
