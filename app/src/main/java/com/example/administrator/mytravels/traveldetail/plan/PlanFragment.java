package com.example.administrator.mytravels.traveldetail.plan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mytravels.R;
import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.traveldetail.PlanDetailActivity;
import com.example.administrator.mytravels.traveldetail.TravelDetailBaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class PlanFragment extends TravelDetailBaseFragment {
    private static final String TAG = PlanFragment.class.getSimpleName();
    public static final int TITLE_ID = R.string.title_frag_daily_plans;

    public PlanFragment(){

    }

    public static PlanFragment newInstance(int sectionNumber){
        Log.d(TAG, "newInstance: sectionNumber="+sectionNumber);
        PlanFragment fragment = new PlanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plan,container,false);
        return rootView;
    }

    @Override
    protected void onTravelChanged(Travel travel) {

    }

    @Override
    public void requestAddItem() {
        Intent intent = new Intent(getContext(), PlanDetailActivity.class);
        startActivity(intent);
    }
}
