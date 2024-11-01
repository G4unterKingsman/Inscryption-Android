package com.example.inscryption.bosses;

import com.example.inscryption.R;

public class Fisherman extends BaseClassBosses {

    @Override
    protected int getAudioResourceId() {
        return R.raw.fichermanmp3;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ficherman;
    }
}

