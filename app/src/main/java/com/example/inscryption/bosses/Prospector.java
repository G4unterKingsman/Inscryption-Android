package com.example.inscryption.bosses;

import com.example.inscryption.R;

public class Prospector extends BaseClassBosses {
    @Override
    protected int getAudioResourceId() {
        return R.raw.prospectormp3;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_prospector;
    }
}

