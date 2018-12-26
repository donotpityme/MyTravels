package com.example.administrator.mytravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_launcher);

        Intent t = new Intent(LauncherActivity.this,MainActivity.class);
        startActivity(t);
        finish();
    }
}
