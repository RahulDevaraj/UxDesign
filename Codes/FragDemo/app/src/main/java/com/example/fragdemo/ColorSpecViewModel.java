package com.example.fragdemo;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ColorSpecViewModel extends ViewModel {
    MutableLiveData<List<ColorSpec>> ColorList = new MutableLiveData<>();

    public LiveData<List<ColorSpec>> getColors(){
        if(ColorList==null){
            ColorList=new MutableLiveData<>();}
        return ColorList;
    }
    public void loadColors(List<ColorSpec> colorList){
        ColorList.setValue(colorList);
//        Handler handler = new Handler();
//        handler.postDelayed(()-> {
//            ColorList.setValue(colorList);
//        },1000);
    }
}
