package com.example.clockapp.placeholder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlarmModel extends ViewModel {
    private final MutableLiveData<Object> sharedData = new MutableLiveData<>();

    public void setData(Object data) {
        sharedData.setValue(data);
    }

    public LiveData<Object> getData() {
        return sharedData;
    }
}
