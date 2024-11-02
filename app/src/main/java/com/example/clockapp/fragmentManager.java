package com.example.clockapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class fragmentManager extends FragmentStatePagerAdapter {

    public fragmentManager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if(position == 0){

            return new clockFragment();

        }else if(position == 1){
            return new alarmFragment();
        }
        return new clockFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){

            return "Đồng hồ";
        }else if(position == 1){

            return "Báo thức";
        }
        return null;
    }
}
