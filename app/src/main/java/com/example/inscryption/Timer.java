package com.example.inscryption;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Timer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "не тот класс", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_timer);

        final CountDownTimer[] timer = new CountDownTimer[1];
        TextView textView = findViewById(R.id.textView);
        ImageButton buttonPlay = findViewById(R.id.buttonplay);
        ImageButton buttonPause = findViewById(R.id.buttonpause);
        buttonPause.setVisibility(View.GONE);

        EditText editText = findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        textView.setText(editText.toString());

        buttonPlay.setOnClickListener(v -> {
            if (editText.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "пусто", Toast.LENGTH_SHORT).show();
            } else {
                String tex = editText.getText().toString();
                long tex2 = Long.parseLong(tex) * 60000;

                buttonPlay.setVisibility(View.GONE);
                buttonPause.setVisibility(View.VISIBLE);

                timer[0] = new CountDownTimer(tex2, 1000) {
                    @Override
                    public void onTick(long l) {
                        NumberFormat f = new DecimalFormat("00");
                        long min = (l / 60000) % 60;
                        long sec = (l / 1000) % 60;
                        textView.setText(f.format(min) + ":" + f.format(sec));
                    }

                    @Override
                    public void onFinish() {
                        textView.setText("00:00");
                    }
                }.start();

                buttonPause.setOnClickListener(v1 -> {
                    buttonPause.setVisibility(View.GONE);
                    buttonPlay.setVisibility(View.VISIBLE);
                    textView.setText("00:00");
                    timer[0].cancel();
                });
            }
        });
    }
}

