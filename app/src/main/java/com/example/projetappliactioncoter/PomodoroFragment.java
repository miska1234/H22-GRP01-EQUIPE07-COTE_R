package com.example.projetappliactioncoter;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PomodoroFragment extends Fragment {
    static final long START_TIME_IN_MILLIS = 600000;

    TextView mTextViewCountDown;
    Button mButton25, mButton50;
    CountDownTimer countDownTimer;
    boolean mTimerRunnig;
    long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pomodoro, container, false);
        mTextViewCountDown = (TextView) view.findViewById(R.id.minutrie);
        mButton25 = (Button) view.findViewById(R.id.pomodoro25);
        mButton50 = (Button) view.findViewById(R.id.pomodoro50);

        mButton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starterTimer();
            }
        });

        mButton50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starterTimer();
            }
        });

        return inflater.inflate(R.layout.fragment_pomodoro, container, false);
    }

void starterTimer(){
        new CountDownTimer(30000,1000) {
            public void onTick(long millisUnilFinished) {
                mTextViewCountDown.setText((int) (millisUnilFinished/1000));
            }

            @Override
            public void onFinish() {
            mTimerRunnig=false;
            }
        }.start();
        mTimerRunnig = true;
}

 void  updateCountDownText(){
        int minutes = (int)(mTimeLeftInMillis/1000)/60;
        int seconds = (int)(mTimeLeftInMillis/1000)%60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,
                seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
}
}
