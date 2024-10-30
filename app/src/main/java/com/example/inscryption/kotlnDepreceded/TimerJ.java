package com.example.inscryption.kotlnDepreceded;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.R;

public class TimerJ extends AppCompatActivity {
    CountDownTimer timer;
    EditText editText;
    TextView textView;
    ImageButton buttonPlay,buttonPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        textView = findViewById(R.id.textView);
        buttonPlay = findViewById(R.id.buttonplay);
        buttonPause = findViewById(R.id.buttonpause);
        buttonPause.setVisibility(View.INVISIBLE);

        editText = findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                editText.setVisibility(View.INVISIBLE);
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "пусто", Toast.LENGTH_SHORT).show();
                }
                else if (!isNumeric(editText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "не число", Toast.LENGTH_SHORT).show();
                }
                else {
                    long tex = (Long.parseLong(editText.getText().toString()))*60000;

                    buttonPlay.setVisibility(View.INVISIBLE);
                    buttonPause.setVisibility(View.VISIBLE);

                    timer = new CountDownTimer(tex, 1000) {
                        @Override
                        public void onTick(long l) {
                            NumberFormat f = new DecimalFormat("00");
                            long min = l / 60000 % 60;
                            long sec = l / 1000 % 60;
                            textView.setText(f.format(min) + ":" + f.format(sec));
                        }

                        @Override
                        public void onFinish() {
                            textView.setText("00:00");
                            editText.setVisibility(View.VISIBLE);
                        }
                    };
                    timer.start();

                    buttonPause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            buttonPause.setVisibility(View.INVISIBLE);
                            buttonPlay.setVisibility(View.VISIBLE);
                            editText.setVisibility(View.VISIBLE);
                            textView.setText("00:00");
                            timer.cancel();
                        }
                    });
                }
            }
        });
    }

    private boolean isNumeric(String str) {
            try {
                Long.parseLong(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
    }
}
