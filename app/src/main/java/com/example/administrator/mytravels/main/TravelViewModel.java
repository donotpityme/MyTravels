package com.example.administrator.mytravels.main;

import android.app.Application;

import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.repository.TravelRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TravelViewModel extends AndroidViewModel {
    private final TravelRepository mRepository;
    private LiveData<List<Travel>> mAllTravels;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        mRepository = TravelRepository.getINSTANCE(application);
        mAllTravels = mRepository.getAllTravels();
    }

    public LiveData<List<Travel>> getAllTravels() {
        return mAllTravels;
    }

    public void insert(Travel travel) {
        mRepository.insert(travel);
    }

    public void update(Travel travel) {
        mRepository.update(travel);
    }

    public void delete(Travel travel) {
        mRepository.delete(travel);
    }
}
