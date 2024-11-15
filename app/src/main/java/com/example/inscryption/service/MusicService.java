package com.example.inscryption.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.inscryption.MainActivity;

public class MusicService extends Service {

    public static void startMusic(MediaPlayer mediaPlayer){
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            MainActivity.pauseMainMusic();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    public static void stopMusic(MediaPlayer mediaPlayer){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }


    public static void pauseMusic(MediaPlayer mediaPlayer){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
