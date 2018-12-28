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

    public void insert(Travel travels){
        new insertAsyncTask(mTravelDao).execute(travels);
    }

    private static class insertAsyncTask extends AsyncTask<Travel, Void, Void> {

        private TravelDao mAsyncTaskDao;

        insertAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Travel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update(Travel travels) {new updateAsyncTask(mTravelDao).execute(travels);}

    private static class updateAsyncTask extends AsyncTask<Travel, Void, Void> {

        private TravelDao mAsyncTaskDao;

        updateAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Travel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete(Travel travels){new deleteAsyncTask(mTravelDao).execute(travels);}

    private static class deleteAsyncTask extends AsyncTask<Travel, Void, Void> {

        private TravelDao mAsyncTaskDao;

        deleteAsyncTask(TravelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Travel... params) {
            mAsyncTaskDao.delete(params);
            return null;
        }
    }
}
