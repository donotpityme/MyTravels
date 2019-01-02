package com.example.administrator.mytravels;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.mytravels.base.TravelSort;
import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.main.TravelListAdapter;
import com.example.administrator.mytravels.main.TravelViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity implements TravelListAdapter.ListItemClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private TravelViewModel mTravelViewModel;
    private TravelListAdapter mTravelListAdapter;

    private final Observer<List<Travel>> mTravelObserver = new Observer<List<Travel>>() {
        @Override
        public void onChanged(List<Travel> travels) {
            Log.d(TAG, "onChanged: travel.size="+travels.size());
            mTravelListAdapter.setList(travels);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTravelListAdapter = new TravelListAdapter(this);
        mTravelListAdapter.setListItemClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(mTravelListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditTravelsActivity.class);
                startActivityForResult(intent, REQCD_TRAVEL_ADD);
            }
        });

        mTravelViewModel = ViewModelProviders.of(this).get(TravelViewModel.class);
        mTravelViewModel.getAllTravels().observe(this,mTravelObserver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        TravelSort travelSort = ((MyApplication) getApplication()).getTravelSort();
        switch (travelSort){
            case DEFAULT:
                menu.findItem(R.id.action_sort_default).setChecked(true);
                break;
            case TITLE_ASC:
                menu.findItem(R.id.action_sort_travel_asc).setChecked(true);
                break;
            case TITLE_DESC:
                menu.findItem(R.id.action_sort_travels_desc).setChecked(true);
                break;
            case START_ASC:
                menu.findItem(R.id.action_sort_start_asc).setChecked(true);
                break;
            case START_DESC:
                menu.findItem(R.id.action_sort_start_desc).setChecked(true);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort_default:
                item.setChecked(true);
                return true;
            case R.id.action_sort_travel_asc:
                item.setChecked(true);
                return true;
            case R.id.action_sort_travels_desc:
                item.setChecked(true);
                return true;
            case R.id.action_sort_start_asc:
                item.setChecked(true);
                return true;
            case R.id.action_sort_start_desc:
                item.setChecked(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode= "+requestCode);
        Log.d(TAG, "onActivityResult: resultCode= "+resultCode);
        Log.d(TAG, "onActivityResult: data= "+data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case REQCD_TRAVEL_ADD: {
                Travel travel = (Travel) data.getExtras().getSerializable(REQKEY_TRAVEL);
                Log.d(TAG, "onActivityResult: travel= "+travel);
                mTravelViewModel.insert(travel);
            }
            break;
            case REQCD_TRAVEL_EDIT:{
                Travel travel = (Travel) data.getExtras().getSerializable(REQKEY_TRAVEL);
                Log.d(TAG, "onActivityResult: travel="+travel);
                if (REQACTION_DEL_TRAVEL.equals(data.getAction())){
                    mTravelViewModel.delete(travel);
                }else mTravelViewModel.update(travel);
            }
            break;
        }
    }

    @Override
    public void onListItemClick(View view, int position, Travel travel) {
        Log.d(TAG, "onListItemClick: position="+position);
        Log.d(TAG, "onListItemClick: travel="+travel);
    }
}
