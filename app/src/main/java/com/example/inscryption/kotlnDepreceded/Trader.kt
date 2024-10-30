package com.example.inscryption.kotlnDepreceded

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.inscryption.R

class Trader : AppCompatActivity() {
    private lateinit var tPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader)

        tPlayer = MediaPlayer.create(this, R.raw.tapper)
        val buttonPlay: ImageButton = findViewById(R.id.buttonplay)
        val buttonPause: ImageButton = findViewById(R.id.buttonpause)

        buttonPause.isVisible = false
        buttonPlay.setOnClickListener {
            tPlayer.start()
            buttonPlay.isVisible = false
            buttonPause.isVisible = true

            buttonPause.setOnClickListener{
                tPlayer.pause()
                buttonPause.isVisible = false
                buttonPlay.isVisible = true
            }
        }
    }

    override fun onPause() {
        super.onPause()
        tPlayer.pause()
        tPlayer.release()
    }
}