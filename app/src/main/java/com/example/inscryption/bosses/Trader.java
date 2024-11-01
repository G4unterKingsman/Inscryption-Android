package com.example.inscryption.bosses;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;
import com.example.inscryption.service.ButtonService;
import com.example.inscryption.service.MusicService;

public class Trader extends BaseClassBosses {
    @Override
    protected int getAudioResourceId() {
        return R.raw.tradermp3;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_trader;
    }
}

