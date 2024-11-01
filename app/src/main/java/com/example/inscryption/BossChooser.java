package com.example.inscryption;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.bosses.Fisherman;
import com.example.inscryption.bosses.Prospector;
import com.example.inscryption.bosses.Trader;

public class BossChooser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bosses);

        ImageButton prospectorButton = findViewById(R.id.prospectorButton);
        ImageButton traderButton = findViewById(R.id.traderButton);
        ImageButton fishermanButton = findViewById(R.id.fishermanButton);

        prospectorButton.setOnClickListener(v -> startProspectorActivity());
        traderButton.setOnClickListener(v -> startTraderActivity());
        fishermanButton.setOnClickListener(v -> startFishermanActivity());
    }

    private void startProspectorActivity(){
        Intent starIntent = new Intent(this, Prospector.class);
        starIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(starIntent);
    }

    private void startTraderActivity(){
        Intent traderIntent = new Intent(this, Trader.class);
        traderIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(traderIntent);
    }

    private void startFishermanActivity(){
        Intent fishermanIntent = new Intent(this, Fisherman.class);
        fishermanIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(fishermanIntent);
    }
}

