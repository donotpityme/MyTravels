package com.example.administrator.mytravels.dao;

import com.example.administrator.mytravels.entity.TravelPlan;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TravelPlanDao {

    @Query("SELECT * FROM travel_plan WHERE deleteYn=0 and travelId=:travelId ORDER BY dateTime DESC, Id DESC")

    DataSource.Factory<Integer, TravelPlan> getAllPlansOfTravel(long travelId);

    @Query("SELECT * FROM travel_plan WHERE deleteYn=0 and travelId=:travelId and substr(dateTime,1,8) = substr(:dateTime,1,8) ORDER BY dateTime DESC, id DESC")

    DataSource.Factory<Integer,TravelPlan> getPlansOnDate(long travelId, String dateTime);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TravelPlan... items);

    @Update
    void update(TravelPlan... items);
    @Delete
    void delete(TravelPlan... items);

    @Query("DELETE FROM travel_plan WHERE deleteYn=1 and travelId=:travelId")
            void deleteAllMarkedYes(long travelId);

    @Query("UPDATE travel_plan SET deleteYn=0 WHERE deleteYn=1 and travelId=:travelId")
            void undeleteAllMarkedYes(long travelId);

}
