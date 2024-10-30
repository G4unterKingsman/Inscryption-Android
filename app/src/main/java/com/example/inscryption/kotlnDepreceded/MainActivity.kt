package com.example.inscryption.kotlnDepreceded

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.inscryption.R

class MainActivity : AppCompatActivity() {

    private lateinit var mPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPlayer = MediaPlayer.create(this, R.raw.deathcardreprice)
        mPlayer.start()
        mPlayer.isLooping = true

        val glad: ImageButton = findViewById(R.id.Glav)
        val timer: ImageButton  = findViewById(R.id.Timer)
        val scale: ImageButton  = findViewById(R.id.Scale)

        glad.setOnClickListener{
            val intent1 = Intent(this, Glavi::class.java)
            startActivity(intent1)
        }

        timer.setOnClickListener{
            val intent2 = Intent(this, TimerJ::class.java)
            startActivity(intent2)
        }

        scale.setOnClickListener{
            val intent3 = Intent(this, Scale::class.java)
            startActivity(intent3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.stop()
    }
}