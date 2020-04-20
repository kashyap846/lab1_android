package com.hands_on_android.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MainAdapter extends FragmentStatePagerAdapter {
    public MainAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Properly implement this function
        switch(position){
            case 0: return new StopwatchFragment();

            case 1: return new TimerFragment();

            default: return new StopwatchFragment();

        }
    }
    /*
    You need to add a function here to handle TabLayout's title
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Stopwatch";
        }else{
            return "Timer";
        }
    }

    @Override
    public int getCount() {
        //This function need to be updated
        return 2;
    }
}
