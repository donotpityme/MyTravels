package com.example.administrator.mytravels.database;


import android.content.Context;

import com.example.administrator.mytravels.dao.TravelDao;
import com.example.administrator.mytravels.dao.TravelPlanDao;
import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.entity.TravelDiary;
import com.example.administrator.mytravels.entity.TravelExpense;
import com.example.administrator.mytravels.entity.TravelPlan;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Travel.class, TravelPlan.class, TravelDiary.class, TravelExpense.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    // refer to https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "travel_db")
                            .fallbackToDestructiveMigration()
                            /*.addMigrations(MIGRATION_1_2)*/
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TravelDao travelDao();

    public abstract TravelPlanDao travelPlanDao();
}
