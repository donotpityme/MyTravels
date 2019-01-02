package com.example.administrator.mytravels.dao;

import com.example.administrator.mytravels.entity.Travel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TravelDao {
    @Query("SELECT * FROM travel ORDER BY id DESC") //theo thứ tự giảm dần
    LiveData<List<Travel>> getAllTravels();

    @Query("SELECT * FROM travel ORDER BY title")
    LiveData<List<Travel>> getAllTravelsbyTitleAsc();

    @Query("SELECT * FROM travel ORDER BY title DESC")
    LiveData<List<Travel>> getAllTravelsbyTitleDesc();

    @Query("SELECT * FROM travel ORDER BY startDt")
    LiveData<List<Travel>> getAllTravelsbyStartAsc();

    @Query("SELECT * FROM travel ORDER BY startDt DESC")
    LiveData<List<Travel>> getAllTravelsbyStartDesc();

    @Insert
    void insert(Travel travel);

    @Update
    void update(Travel travel);

    @Delete
    void delete(Travel...travel);
}
