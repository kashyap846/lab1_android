package com.hands_on_android.lab1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
/*
Name: Kashyhap K
Student #: A00209673
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Added viewpager and Main adapter
        ViewPager myView = findViewById(R.id.viewPager);
        MainAdapter myAdapter = new MainAdapter(
                getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        //Linking main adapter and view pager
        myView.setAdapter(myAdapter);
    }
}

