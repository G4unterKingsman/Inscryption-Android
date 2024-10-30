package com.example.inscryption.kotlnDepreceded

import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.inscryption.R
import java.text.DecimalFormat
import java.text.NumberFormat


class Timer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(applicationContext, "не тот класс", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_timer)

        var timer: CountDownTimer
        val textView: TextView = findViewById(R.id.textView)
        val buttonPlay: ImageButton = findViewById(R.id.buttonplay)
        val buttonPause: ImageButton = findViewById(R.id.buttonpause)
        buttonPause.isVisible = false

        val editText: EditText = findViewById(R.id.editText)
        editText.setInputType(InputType.TYPE_CLASS_NUMBER)
        textView.text = editText.toString()

        buttonPlay.setOnClickListener {
            if (editText.getText().isEmpty()) Toast.makeText(applicationContext, "пусто", Toast.LENGTH_SHORT).show()
            //else if (editText.getText().is)//проеврить если не число https://questu.ru/questions/27042478/
            else {
                val tex: String = editText.toString()
                val tex2: Long = tex.toLongOrNull()!! * 60000

                buttonPlay.isVisible = false
                buttonPause.isVisible = true

                timer = object : CountDownTimer(tex2, 1000) {
                    override fun onTick(l: Long) {
                        val f: NumberFormat = DecimalFormat("00")
                        val min: Long = l / 60000 % 60
                        val sec: Long = l / 1000 % 60
                        textView.text = f.format(min) + ":" + f.format(sec);
                    }

                    override fun onFinish() {
                        textView.text = "00:00"
                    }
                }.start()

                buttonPause.setOnClickListener() {
                    buttonPause.isVisible = false
                    buttonPlay.isVisible = true
                    textView.text = "00:00"
                    timer.cancel()

                }
            }
        }
    }
}

