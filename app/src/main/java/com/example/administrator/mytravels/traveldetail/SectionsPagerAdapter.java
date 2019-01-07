package com.example.administrator.mytravels.traveldetail;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.administrator.mytravels.traveldetail.diary.DiaryFragment;
import com.example.administrator.mytravels.traveldetail.expense.ExpenseFragment;
import com.example.administrator.mytravels.traveldetail.plan.PlanFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> registeredFragment = new SparseArray<>();
    private final Context mContext;
    public SectionsPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return DiaryFragment.newInstance(position);
            case 2:
                return ExpenseFragment.newInstance(position);
            default:
                return PlanFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 1:
                return mContext.getString(DiaryFragment.TITLE_ID);
            case 2:
                return mContext.getString(ExpenseFragment.TITLE_ID);
            default:
                return mContext.getString(PlanFragment.TITLE_ID);
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragment.put(position,fragment);
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        registeredFragment.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position){
        return registeredFragment.get(position);
    }
}
