package com.example.inscryption.kotlnDepreceded

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.inscryption.R

class Scale : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)

        val button1: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)
        val imageView: ImageView = findViewById(R.id.scale2)
        val array  = arrayOf(
            R.drawable.minus5,R.drawable.minus4,R.drawable.minus3,R.drawable.minus2,R.drawable.minus1,
            R.drawable.zero,
            R.drawable.plus1, R.drawable.plus2,R.drawable.plus3,R.drawable.plus4,R.drawable.plus5
        )

        var count = 5
        imageView.setImageResource(array[count])

        button1.setOnClickListener{
            try{
                count -= 1
                imageView.setImageResource(array[count])
                button2.isClickable = true
            }
            catch (e: ArrayIndexOutOfBoundsException) {
                button1.isClickable = false
                Toast.makeText(applicationContext, "дальше нельзя", Toast.LENGTH_SHORT).show()
            }
        }

        button2.setOnClickListener{
            try{
                count += 1
                imageView.setImageResource(array[count])
                button1.isClickable = true
            }
            catch (e: ArrayIndexOutOfBoundsException) {
                button2.isClickable = false
                Toast.makeText(applicationContext, "дальше нельзя", Toast.LENGTH_SHORT).show()
            }
        }

    }



}