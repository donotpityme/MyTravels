package com.example.administrator.mytravels.traveldetail.expense;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mytravels.R;
import com.example.administrator.mytravels.entity.Travel;
import com.example.administrator.mytravels.traveldetail.TravelDetailBaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ExpenseFragment extends TravelDetailBaseFragment {
    private static final String TAG = ExpenseFragment.class.getSimpleName();
    public static final int TITLE_ID = R.string.title_frag_expenses;

    public ExpenseFragment() {
    }

    public static ExpenseFragment newInstance(int sectionNumber){
        Log.d(TAG, "newInstance: sectionNumber="+sectionNumber);
        ExpenseFragment fragment = new ExpenseFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_expense,container,false);
        return rootView;
    }

    @Override
    protected void onTravelChanged(Travel travel) {

    }

    @Override
    public void requestAddItem() {
        Intent intent = new Intent(getContext(), ExpenseFragment.class);
        startActivity(intent);
    }
}
