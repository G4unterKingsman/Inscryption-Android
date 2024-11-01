package com.example.inscryption.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class ButtonService extends Service {

    public static void setupButtonsVisibility(ImageButton buttonPlay, ImageButton buttonPause, ImageButton buttonStop, String action) {
        switch (action) {
            case "init":
            case "pause":
            case "stop":
                buttonPlay.setVisibility(View.VISIBLE);
                buttonPause.setVisibility(View.GONE);
                buttonStop.setVisibility(View.GONE);
                break;
            case "play":
                buttonPlay.setVisibility(View.GONE);
                buttonPause.setVisibility(View.VISIBLE);
                buttonStop.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
