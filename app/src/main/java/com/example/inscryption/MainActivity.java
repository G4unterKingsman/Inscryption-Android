package com.example.inscryption;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.inscryption.cards.CardCreator;
import com.example.inscryption.cards.ShowPlayers;
import com.example.inscryption.db.AppDatabase;
import com.example.inscryption.scale.Scale;
import com.example.inscryption.service.MusicService;
import com.example.inscryption.timer.Timer;

public class MainActivity extends AppCompatActivity {
    public static MediaPlayer mPlayer;
    private static AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app_database")
                .build();
        // deleteDatabase("app_database");
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(this, R.raw.deathcardreprice);
            mPlayer.setLooping(true);
            mPlayer.start();
        }

        ImageButton bosses = findViewById(R.id.Bosses);
        ImageButton timer = findViewById(R.id.Timer);
        ImageButton scale = findViewById(R.id.Scale);
        ImageButton test1 = findViewById(R.id.test1);
        ImageButton test2 = findViewById(R.id.test2);

        // TODO - создал новую вьюху - добавь в Manifest
        test1.setOnClickListener(v -> {
            Intent intent4 = new Intent(this, CardCreator.class);
            startActivity(intent4);
        });
        test2.setOnClickListener(v -> {
            Intent intent4 = new Intent(this, ShowPlayers.class);
            startActivity(intent4);
        });
        bosses.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, BossChooser.class);
            startActivity(intent1);
        });

        timer.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, Timer.class);
            startActivity(intent2);
        });

        scale.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, Scale.class);
            startActivity(intent3);
        });
    }

    public static void resumeMainMusic() {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            MusicService.startMusic(mPlayer);
        }
    }

    public static void pauseMainMusic() {
        if (MainActivity.mPlayer != null && MainActivity.mPlayer.isPlaying()) {
            MusicService.pauseMusic(mPlayer);
        }
    }

    public static AppDatabase getDatabase() {
        return database;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }
}

