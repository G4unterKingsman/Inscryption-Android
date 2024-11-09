package com.example.inscryption.bosses;

import com.example.inscryption.R;

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

