package com.example.administrator.mytravels.traveldetail;

import android.os.Bundle;

import com.example.administrator.mytravels.base.BaseActivity;
import com.example.administrator.mytravels.R;

import androidx.appcompat.widget.Toolbar;

public class DiaryDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
