package com.example.inscryption;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(this, R.raw.deathcardreprice);
            mPlayer.setLooping(true);
            mPlayer.start();
        }

        ImageButton bosses = findViewById(R.id.Bosses);
        ImageButton timer = findViewById(R.id.Timer);
        ImageButton scale = findViewById(R.id.Scale);

        bosses.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, BossChooser.class);
            startActivity(intent1);
        });

        timer.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, TimerJ.class);
            startActivity(intent2);
        });

        scale.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, Scale.class);
            startActivity(intent3);
        });
    }

    public static void resumeMainMusic() {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.start();
        }
    }

    public static void pauseMainMusic() {
        if (MainActivity.mPlayer != null && MainActivity.mPlayer.isPlaying()) {
            MainActivity.mPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}

