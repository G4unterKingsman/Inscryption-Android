package com.example.inscryption.cards;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;
import com.example.inscryption.cards.viewmodel.CardCreatorViewModel;
import com.example.inscryption.cards.viewmodel.CardCreatorViewModelFactory;
import com.example.inscryption.dao.PlayerDao;

public class CardCreator extends AppCompatActivity {

    private EditText editTextPrice, editTextHP, editTextDamage, editTextCardName, editTextPlayerName;
    private CardCreatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatorcards);

        PlayerDao playerDao = MainActivity.getDatabase().playerDao();
        CardCreatorViewModelFactory factory = new CardCreatorViewModelFactory(playerDao);
        viewModel = new ViewModelProvider(this, factory).get(CardCreatorViewModel.class);

        Button buttonSave = findViewById(R.id.buttonsave);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDamage = findViewById(R.id.editTextDamage);
        editTextHP = findViewById(R.id.editTextHP);
        editTextCardName = findViewById(R.id.editTextCardName);
        editTextPlayerName = findViewById(R.id.editTextPlayerName);

        buttonSave.setOnClickListener(v -> saveCard());

        viewModel.getSaveResult().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (message.equals("Карточка успешно сохранена!")) {
                clearFields();
            }
        });
    }

    private void saveCard() {
        String priceStr = editTextPrice.getText().toString();
        String damageStr = editTextDamage.getText().toString();
        String hpStr = editTextHP.getText().toString();
        String cardName = editTextCardName.getText().toString();
        String playerName = editTextPlayerName.getText().toString();

        if (priceStr.isEmpty() || damageStr.isEmpty() || hpStr.isEmpty() || cardName.isEmpty() || playerName.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        long price = Integer.parseInt(priceStr);
        long damage = Integer.parseInt(damageStr);
        long hp = Integer.parseInt(hpStr);

        viewModel.saveCard(playerName, cardName, price, damage, hp);
    }

    private void clearFields() {
        editTextPrice.setText("");
        editTextDamage.setText("");
        editTextHP.setText("");
        editTextCardName.setText("");
        editTextPlayerName.setText("");
    }
}