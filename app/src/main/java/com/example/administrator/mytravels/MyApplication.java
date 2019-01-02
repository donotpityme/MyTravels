package com.example.administrator.mytravels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.administrator.mytravels.base.TravelSort;
import com.example.administrator.mytravels.entity.Travel;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private static final String TRAVEL_SORT_NAME = "TRAVEL_sORT_NAME";
    private static final String TRAVEL_SORT_KEY = "TRAVEL_sORT_KEY";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "OnCreate");
    }
    public void setTravelSort(TravelSort travelSort){
        SharedPreferences sharedPref = getSharedPreferences(TRAVEL_SORT_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TRAVEL_SORT_KEY,travelSort.name());
        editor.commit();
    }
    public TravelSort getTravelSort(){
        SharedPreferences sharedPref = getSharedPreferences(TRAVEL_SORT_NAME,Context.MODE_PRIVATE);
        String name = sharedPref.getString(TRAVEL_SORT_KEY, TravelSort.DEFAULT.name());
        TravelSort travelSort = TravelSort.DEFAULT;
        try{
            travelSort = travelSort.valueOf(name);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return travelSort;
    }
}
