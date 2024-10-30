package com.example.inscryption;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.bosses.Angeler;
import com.example.inscryption.bosses.Prospector;
import com.example.inscryption.bosses.Trader;

public class Glavi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavs);

        ImageButton prospectorButton = findViewById(R.id.prospectorButton);
        ImageButton traderButton = findViewById(R.id.traderButton);
        ImageButton anglerButton = findViewById(R.id.anglerButton);

        prospectorButton.setOnClickListener(v -> {
            Intent starIntent = new Intent(this, Prospector.class);
            starIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(starIntent);
        });

        traderButton.setOnClickListener(v -> {
            Intent traderIntent = new Intent(this, Trader.class);
            traderIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(traderIntent);
        });

        anglerButton.setOnClickListener(v -> {
            Intent anglerIntent = new Intent(this, Angeler.class);
            anglerIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(anglerIntent);
        });
    }
}

