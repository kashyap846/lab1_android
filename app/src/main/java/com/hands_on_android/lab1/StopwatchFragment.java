package com.hands_on_android.lab1;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StopwatchFragment extends Fragment {

    //You will need to assign views from layout during onCreateView
    private Button startPauseButton;
    private Button stopButton;
    private TextView timePassedTextView;
    private int timePassed;
    private boolean isRunning = false;
    private Handler myHandler= new Handler();


    /*
    Add Handler and Runnable here
     */
    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            incrementSecond();
            myHandler.postDelayed(timeRunnable,1000);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch,container,false);
        /*
        Load fragment_stopwatch.xml into View.
        set view variables from the layout
         */

        startPauseButton = layout.findViewById(R.id.startPauseButton);
        stopButton = layout.findViewById(R.id.stopButton);
        timePassedTextView = layout.findViewById(R.id.timePassed);

        //Uncomment this

        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartOrPauseClick();

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        return layout;
    }

    private void onStartOrPauseClick() {
        if (isRunning) {
            pause();
        } else {
            start();
        }
    }

    private void pause() {
        //Make sure you're properly setting isRunning value
        //Make sure you're properly handling buttons' visibilities
        isRunning=false;
        startPauseButton.setText(getResources().getString(R.string.start));
        myHandler.removeCallbacks(timeRunnable);
    }

    private void start() {
        //Make sure you're properly setting isRunning value
        //Make sure you're properly handling buttons' visibilities
        isRunning = true;
        startPauseButton.setText(getResources().getString(R.string.pause));
        stopButton.setVisibility(View.VISIBLE);
        myHandler.postDelayed(timeRunnable,1000);
    }

    private void stop() {
        //Make sure you're properly setting isRunning value
        //Make sure you're properly handling buttons' visibilities
        isRunning=false;
        startPauseButton.setText(getResources().getString(R.string.start));
        stopButton.setVisibility(View.GONE);
        myHandler.removeCallbacks(timeRunnable);
        timePassed=0;
        setText();
     }


    private void incrementSecond(){
        timePassed=timePassed+1;
        setText();
     }
    private void setText(){
        timePassedTextView.setText(Helper.formatTime(timePassed));
    }
}

