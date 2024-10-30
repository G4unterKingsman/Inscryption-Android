package com.example.inscryption.kotlnDepreceded

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.inscryption.R

class Angeler : AppCompatActivity(){
    private lateinit var aPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_angeler)

        aPlayer = MediaPlayer.create(this, R.raw.angler)
        val buttonPlay: ImageButton = findViewById(R.id.buttonplay)
        val buttonPause: ImageButton = findViewById(R.id.buttonpause)

        buttonPause.isVisible = false
        buttonPlay.setOnClickListener {
            aPlayer.start()
            buttonPlay.isVisible = false
            buttonPause.isVisible = true

            buttonPause.setOnClickListener{
                aPlayer.pause()
                buttonPause.isVisible = false
                buttonPlay.isVisible = true
            }
        }
    }

    override fun onPause() {
        super.onPause()
        aPlayer.pause()
        aPlayer.release()
    }
}