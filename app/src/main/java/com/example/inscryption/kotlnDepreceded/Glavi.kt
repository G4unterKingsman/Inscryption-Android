package com.example.inscryption.kotlnDepreceded

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.inscryption.R

class Glavi: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glavs)

        val button1: ImageButton = findViewById(R.id.imageButton)
        val button2: ImageButton = findViewById(R.id.imageButton2)
        val button3: ImageButton = findViewById(R.id.imageButton3)


        button1.setOnClickListener {
            val intent1 = Intent(this, Staratel::class.java)
            intent1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent1)
        }

        button2.setOnClickListener {
            val intent2 = Intent(this, Trader::class.java)
            intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent2)
        }

        button3.setOnClickListener {
            val intent3 = Intent(this, Angeler::class.java)
            intent3.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent3)
        }

    }
}