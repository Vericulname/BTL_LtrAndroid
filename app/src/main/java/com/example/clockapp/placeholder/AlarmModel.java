package com.example.clockapp.placeholder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlarmModel extends ViewModel {
    private final MutableLiveData<Object> sharedData = new MutableLiveData<>();
    private int pos =-1;

    public void setData(Object data) {
        sharedData.setValue(data);
    }
    public void SetPos(int pos){
        this.pos = pos;
    }
    public int getPos(){
        return pos;
    }


    public LiveData<Object> getData() {
        return sharedData;
    }
}
