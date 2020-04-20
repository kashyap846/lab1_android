package com.hands_on_android.lab1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static android.content.ContentValues.TAG;

public class TimerFragment extends Fragment {

    private TextInputLayout timeInputLayout;
    private TextInputEditText timeInput;
    private TextView timeLeft;
    private Button startStopButton;

    private boolean isRunning = false;
    private TimerAsyncTask timerAsyncTask = null;

    private int timerVariable;
    private String timerStrVar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_timer,container,false);
        startStopButton = layout.findViewById(R.id.startStopButton);
        timeLeft = layout.findViewById(R.id.timeLeft);
        timeInput = layout.findViewById(R.id.timeInput);
        timeInputLayout = layout.findViewById(R.id.timeInputLayout);
        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: efwef" );
                onStartOrStop();
            }
        });
    return layout;
    }

    private void onStartOrStop() {
        timerStrVar = timeInput.getText().toString();
        boolean containsDigit = false;

        if (timerStrVar != null && !timerStrVar.isEmpty()) {
            for (char c : timerStrVar.toCharArray()) {
            if (containsDigit == Character.isDigit(c)) {
                break;
                }
            Log.e(TAG, "isRunning: "+isRunning);
            }
            if(isRunning){
                stop();
            }else {
                start();
            }
        }

    }

    private void stop() {
        Log.e(TAG, "onClick: stop" );
        timerAsyncTask.cancel(true);
        timerVariable = 0;
        isRunning = false;
        timeInputLayout.setVisibility(View.VISIBLE);
        timeLeft.setVisibility(View.GONE);
        timeLeft.setText(Helper.formatTime(timerVariable));
        startStopButton.setText(getResources().getString(R.string.start));
    }

    /*
    Hint: If timeInput contains invalid value, you shouldn't run the timer.
    Make sure your app doesn't crash on invalid input
    Hint: use Integer.valueOf(timeInput.getText().toString()) to get int value from the input
    Be careful with invalid input crashing the app
     */
    private void start() {
        Log.e(TAG, "onClick: start" );
        timerVariable = Integer.parseInt(timerStrVar);
        timerAsyncTask =  new TimerAsyncTask();
        timerAsyncTask.execute(timerVariable);
        isRunning = true;
        timeInputLayout.setVisibility(View.GONE);
        timeLeft.setVisibility(View.VISIBLE);
        timeLeft.setText(Helper.formatTime(timerVariable));
        startStopButton.setText(getResources().getString(R.string.stop));

        }
    /*
    Finish implementing this class
     */
    private class TimerAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.e(TAG, "doInBackground: "+integers[0] );
            int timeInSeconds = integers[0];
            for(int i = timeInSeconds;i>=0;i--){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                publishProgress(--timeInSeconds);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e(TAG, "onProgressUpdate: "+values[0] );
            timeLeft.setText(Helper.formatTime(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.e(TAG, "onPostExecute: " );
            stop();
        }
    }


}

