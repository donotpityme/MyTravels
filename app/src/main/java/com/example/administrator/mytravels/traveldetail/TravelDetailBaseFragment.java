package com.example.administrator.mytravels.traveldetail;

import android.os.Bundle;

import com.example.administrator.mytravels.entity.Travel;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class TravelDetailBaseFragment extends Fragment {
    private final static String TAG = TravelDetailBaseFragment.class.getSimpleName();
    protected static final String ARG_SECTION_NUMBER = "section_number";

    protected abstract void onTravelChanged(Travel travel);

    public abstract void requestAddItem();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
