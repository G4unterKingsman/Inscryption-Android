package com.example.inscryption.bosses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;

public class Trader extends AppCompatActivity {
    private MediaPlayer tPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader);

        tPlayer = MediaPlayer.create(this, R.raw.tapper);
        ImageButton buttonPlay = findViewById(R.id.buttonplay);
        ImageButton buttonPause = findViewById(R.id.buttonpause);

        buttonPause.setVisibility(View.GONE);
        buttonPlay.setOnClickListener(v -> {
            if (MainActivity.mPlayer != null && MainActivity.mPlayer.isPlaying()) {
                MainActivity.mPlayer.pause();
            }
            tPlayer.start();
            buttonPlay.setVisibility(View.GONE);
            buttonPause.setVisibility(View.VISIBLE);

            buttonPause.setOnClickListener(v1 -> {
                tPlayer.pause();
                buttonPause.setVisibility(View.GONE);
                buttonPlay.setVisibility(View.VISIBLE);
                MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
            });
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        tPlayer.pause();
        tPlayer.release();
        MainActivity.resumeMusic(); // воспроизводим музыку из Главного Меню
    }
}

