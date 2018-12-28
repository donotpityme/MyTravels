package com.example.administrator.mytravels.database;


import android.content.Context;

import com.example.administrator.mytravels.dao.TravelDao;
import com.example.administrator.mytravels.entity.Travel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Travel.class}, version = 1, exportSchema = false) //???
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract TravelDao travelDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , AppDatabase.class
                            , "travle_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
