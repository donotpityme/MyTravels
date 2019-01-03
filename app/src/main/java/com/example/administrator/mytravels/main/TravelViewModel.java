package com.example.administrator.mytravels.main;

import android.app.Application;

import com.example.administrator.mytravels.MyApplication;
import com.example.administrator.mytravels.base.TravelSort;
import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.repository.TravelRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class TravelViewModel extends AndroidViewModel {
    private final TravelRepository mRepository;
    //private LiveData<List<Travel>> mAllTravels;

    public TravelViewModel(@NonNull Application application) {
        super(application);
        mRepository = TravelRepository.getINSTANCE(application);
        //mAllTravels = mRepository.getAllTravels(TravelSort.DEFAULT);
    }


    private final MutableLiveData<TravelSort> mTravelSort = new MutableLiveData<>(); //class mo rong cua Livedata, mutable co the thay doi duoc gia tri con live data thi khong

    public void setTravelSort(TravelSort option) {
        mTravelSort.setValue(option); // livedata khong thuc hien duoc hanh dong nay
        ((MyApplication) getApplication()).setTravelSort(option);
    }

    private final LiveData<List<Travel>> mAllTravels = Transformations.switchMap(mTravelSort,
            new Function<TravelSort, LiveData<List<Travel>>>() {
                @Override
                public LiveData<List<Travel>> apply(TravelSort input) {
                    return mRepository.getAllTravels(input);
                }
            });

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
