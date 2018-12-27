package com.example.administrator.mytravels.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.administrator.mytravels.dao.TravelDao;
import com.example.administrator.mytravels.database.AppDatabase;
import com.example.administrator.mytravels.entity.Travel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TravelRepository {
    private static volatile TravelRepository INSTANCE;
    private final TravelDao mTravelDao;
    private LiveData<List<Travel>> mAllTravels;

    public TravelRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTravelDao = db.travelDao();
    }

    public static TravelRepository getINSTANCE(final Application application) {
        if (INSTANCE == null) {
            synchronized (TravelRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TravelRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<Travel>> getAllTravels() {
        if (mAllTravels == null) {
            mAllTravels = mTravelDao.getAllTravels();
        }
        return mAllTravels;
    }

    private static class insertAsyncTask extends AsyncTask<Travel, Void, Void> {

        private TravelDao mAsyncTaskDao;

        insertAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Travel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    //miss update
}
