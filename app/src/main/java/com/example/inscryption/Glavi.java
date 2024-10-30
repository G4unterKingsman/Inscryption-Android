package com.example.inscryption;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.bosses.Angeler;
import com.example.inscryption.bosses.Staratel;
import com.example.inscryption.bosses.Trader;

public class Glavi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavs);

        ImageButton button1 = findViewById(R.id.imageButton);
        ImageButton button2 = findViewById(R.id.imageButton2);
        ImageButton button3 = findViewById(R.id.imageButton3);

        button1.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, Staratel.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent1);
        });

        button2.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, Trader.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent2);
        });

        button3.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, Angeler.class);
            intent3.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent3);
        });
    }
}

