package com.codificador.stopwatch;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class TimerValue extends BaseObservable{

    private String value;

    public TimerValue(){
        value = "00:00:00:00";
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }
}
