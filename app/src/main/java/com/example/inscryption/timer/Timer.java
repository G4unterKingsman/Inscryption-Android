package com.example.inscryption.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.R;

public class Timer extends AppCompatActivity {
    private CountDownTimer timer;
    private EditText editText;
    private TextView textView;
    private ImageButton buttonPlay, buttonPause, buttonStop;

    private long timeLeftInMillis = 0;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        textView = findViewById(R.id.textView);
        buttonPlay = findViewById(R.id.buttonplay);
        buttonPause = findViewById(R.id.buttonpause);
        buttonStop = findViewById(R.id.buttonstop);
        editText = findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        buttonPause.setVisibility(View.INVISIBLE);
        buttonStop.setVisibility(View.INVISIBLE);

        buttonPlay.setOnClickListener(v -> startTimer());
        buttonPause.setOnClickListener(v -> pauseTimer());
        buttonStop.setOnClickListener(v -> stopTimer());
    }

    private void startTimer() {
        if (!isTimerRunning) {
            if (timeLeftInMillis == 0) {
                String input = editText.getText().toString();
                if (input.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Введите время", Toast.LENGTH_SHORT).show();
                    return;
                }
                timeLeftInMillis = Long.parseLong(input) * 60000;
            }

            editText.setVisibility(View.GONE);
            buttonPlay.setVisibility(View.GONE);
            buttonPause.setVisibility(View.VISIBLE);
            buttonStop.setVisibility(View.VISIBLE);

            startCountDown();
        }
    }

    private void startCountDown() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                resetView();
            }
        }.start();

        isTimerRunning = true;
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            timer.cancel();
            isTimerRunning = false;
            buttonPlay.setVisibility(View.VISIBLE);
            buttonPause.setVisibility(View.GONE);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timeLeftInMillis = 0;
        editText.setText("");
        textView.setText("");
        resetView();
    }

    private void updateCountDownText() {
        long minutes = timeLeftInMillis / 60000;
        long seconds = (timeLeftInMillis % 60000) / 1000;
        textView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void resetView() {
        editText.setVisibility(View.VISIBLE);
        buttonPlay.setVisibility(View.VISIBLE);
        buttonPause.setVisibility(View.INVISIBLE);
        buttonStop.setVisibility(View.INVISIBLE);
        isTimerRunning = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }
}