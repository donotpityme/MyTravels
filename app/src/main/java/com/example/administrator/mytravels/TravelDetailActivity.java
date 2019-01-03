package com.example.administrator.mytravels;


import android.os.Bundle;
import android.util.Log;

public class TravelDetailActivity extends BaseActivity {
    private static final String TAG = TravelDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);

        long travelId = getIntent().getLongExtra(REQKEY_TRAVEL_ID,0);
        Log.d(TAG, "onCreate:travelId= "+travelId);
    }
}
