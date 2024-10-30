package com.example.inscryption.kotlnDepreceded

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.inscryption.R

class Staratel : AppCompatActivity() {
    private lateinit var sPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staratel)

        sPlayer = MediaPlayer.create(this, R.raw.prospector)
        val buttonPlay: ImageButton = findViewById(R.id.buttonplay)
        val buttonPause: ImageButton = findViewById(R.id.buttonpause)

        buttonPause.isVisible = false
        buttonPlay.setOnClickListener {
            sPlayer.start()
            buttonPlay.isVisible = false
            buttonPause.isVisible = true

            buttonPause.setOnClickListener{
                sPlayer.pause()
                buttonPause.isVisible = false
                buttonPlay.isVisible = true
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sPlayer.pause()
        sPlayer.release()
    }
}