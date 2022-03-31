package com.upclicks.ffcapp.base;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    /** livData of boolean value used to handle displaying and hiding of progressbar **/
    public MutableLiveData<Boolean> loading =  new MutableLiveData<>();
    /** livData of boolean value used to handle displaying and empty List Flag **/
    public MutableLiveData<Boolean> isEmptyList = new MutableLiveData<>();
    public MutableLiveData<Integer> listCount = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();
    public MutableLiveData<Boolean> notAuthorized = new MutableLiveData<>();
    public MutableLiveData<Boolean> noConnection = new MutableLiveData<>();

    public LiveData<String> errorObserver(){
        return errorMsg;
    }

}
