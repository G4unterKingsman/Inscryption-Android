package com.example.inscryption;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Scale extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        ImageView imageView = findViewById(R.id.scale2);
        int[] array = {
                R.drawable.minus5, R.drawable.minus4, R.drawable.minus3, R.drawable.minus2, R.drawable.minus1,
                R.drawable.zero,
                R.drawable.plus1, R.drawable.plus2, R.drawable.plus3, R.drawable.plus4, R.drawable.plus5
        };

        final int[] count = {5};  // Используем массив, чтобы хранить изменяемое значение count
        imageView.setImageResource(array[count[0]]);

        button1.setOnClickListener(v -> {
            try {
                count[0] -= 1;
                imageView.setImageResource(array[count[0]]);
                button2.setClickable(true);
            } catch (ArrayIndexOutOfBoundsException e) {
                button1.setClickable(false);
                Toast.makeText(getApplicationContext(), "дальше нельзя", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(v -> {
            try {
                count[0] += 1;
                imageView.setImageResource(array[count[0]]);
                button1.setClickable(true);
            } catch (ArrayIndexOutOfBoundsException e) {
                button2.setClickable(false);
                Toast.makeText(getApplicationContext(), "дальше нельзя", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
