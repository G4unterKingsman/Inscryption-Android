package com.example.inscryption.bosses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;
import com.example.inscryption.service.ButtonService;
import com.example.inscryption.service.MusicService;

public abstract class BaseClassBosses extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageButton buttonPlay;
    private ImageButton buttonPause;
    private ImageButton buttonStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        mediaPlayer = MediaPlayer.create(this, getAudioResourceId());
        buttonPlay = findViewById(R.id.buttonplay);
        buttonPause = findViewById(R.id.buttonpause);
        buttonStop = findViewById(R.id.buttonstop);
        ButtonService.setupButtonsVisibility(buttonPlay, buttonPause, buttonStop, "init");
        buttonPlay.setOnClickListener(v -> playMusic());
        buttonPause.setOnClickListener(v -> pauseMusic());
        buttonStop.setOnClickListener(v -> stopMusic());
    }

    private void playMusic(){
        MusicService.startMusic(mediaPlayer);
        ButtonService.setupButtonsVisibility(buttonPlay, buttonPause, buttonStop, "play");
    }
    private void pauseMusic(){
        MusicService.pauseMusic(mediaPlayer);
        ButtonService.setupButtonsVisibility(buttonPlay, buttonPause, buttonStop, "pause");
    }
    private void stopMusic(){
        MusicService.stopMusic(mediaPlayer);
        ButtonService.setupButtonsVisibility(buttonPlay, buttonPause, buttonStop, "stop");
        mediaPlayer = MediaPlayer.create(this, getAudioResourceId());
    }
    protected abstract int getAudioResourceId();
    protected abstract int getLayoutResourceId();


    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        mediaPlayer.release();
        MainActivity.resumeMainMusic();
    }
}
