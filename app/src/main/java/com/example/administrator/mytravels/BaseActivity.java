package com.example.administrator.mytravels;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private  static final String TAG = MyApplication.class.getSimpleName();
    public static final int REQCD_PLACE_AUTOCOMPLETE = 1000;
    public  static final int REQCD_TRAVEL_ADD = 2000;
    public  static final int REQCD_TRAVEL_EDIT = 2001;
    public  static final String REQKEY_TRAVEL = "REQKEY_TRAVEL";
    public  static final String REQACTION_EDIT_TRAVEL = "REQACTION_EDIT_TRAVEL";
    public  static final String REQACTION_DEL_TRAVEL= "REQACTION_DEL_TRAVEL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }
}