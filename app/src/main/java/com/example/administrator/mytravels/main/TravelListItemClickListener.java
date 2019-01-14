package com.example.administrator.mytravels.main;

import android.view.View;

import com.example.administrator.mytravels.entity.TravelBaseEntity;

public interface TravelListItemClickListener {
    void onListItemClick(View view, int position, TravelBaseEntity entity
            , boolean longClick);
}
