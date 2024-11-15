package com.example.inscryption.scale;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.inscryption.R;

public class Scale extends AppCompatActivity {
    ScaleViewModel scaleVM;
    ImageView imageView;
    Button buttonMinus, buttonPlus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        imageView = findViewById(R.id.scale2);

        scaleVM = new ViewModelProvider(this).get(ScaleViewModel.class);

        scaleVM.getCount().observe(this, count -> {
            imageView.setImageResource(scaleVM.getImages()[count]);
            buttonMinus.setEnabled(count > 0);
            buttonPlus.setEnabled(count < scaleVM.getImages().length - 1);
        });

        buttonMinus.setOnClickListener(v -> scaleVM.decrement());
        buttonPlus.setOnClickListener(v -> scaleVM.increment());
    }
}
