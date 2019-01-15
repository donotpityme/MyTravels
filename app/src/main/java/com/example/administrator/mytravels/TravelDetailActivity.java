package com.example.administrator.mytravels;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mytravels.base.BaseActivity;
import com.example.administrator.mytravels.traveldetail.SectionsPagerAdapter;
import com.example.administrator.mytravels.traveldetail.TravelDetailBaseFragment;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class TravelDetailActivity extends BaseActivity {
    private static final String TAG = TravelDetailActivity.class.getSimpleName();

    private SectionsPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mViewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        // Use setupWithViewPager(ViewPager) to link a TabLayout with a ViewPager.
        // The individual tabs in the TabLayout will be automatically populated
        // with the page titles from the PagerAdapter.
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravelDetailBaseFragment fragment = (TravelDetailBaseFragment) mViewPagerAdapter.getRegisteredFragment(mViewPager.getCurrentItem());
                fragment.requestAddItem();
            }
        });

        mToolbarLayout = findViewById(R.id.toolbar_layout);
        //mSubtitle = findViewById(R.id.subtitle_txt);


    }


}

