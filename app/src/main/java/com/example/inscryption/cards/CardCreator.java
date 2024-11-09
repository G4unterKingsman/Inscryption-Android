package com.example.inscryption.cards;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;
import com.example.inscryption.dao.PlayerDao;
import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.DeckPlayer;
import com.example.inscryption.entity.Player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CardCreator extends AppCompatActivity {

    private EditText editTextPrice, editTextHP, editTextDamage, editTextCardName, editTextPlayerName;

    PlayerDao playerDao = MainActivity.getDatabase().playerDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatorcards);

        Button buttonSave = findViewById(R.id.buttonsave);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDamage = findViewById(R.id.editTextDamage);
        editTextHP = findViewById(R.id.editTextHP);
        editTextCardName = findViewById(R.id.editTextCardName);
        editTextPlayerName = findViewById(R.id.editTextPlayerName);

        buttonSave.setVisibility(View.VISIBLE);
        buttonSave.setOnClickListener(v -> saveCard());
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

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Player player = playerDao.getPlayerByName(playerName);
                if (player == null) {
                    player = new Player(playerName);
                    player.setPlayerId(playerDao.insertPlayer(player));
                }

                // Создание карты
                Card card = new Card(cardName, damage, hp, price);
                card.setCardId(playerDao.insertCard(card));

                // Связывание карты с игроком
                DeckPlayer deckPlayer = new DeckPlayer(player.getPlayerId(), card.getCardId());
                playerDao.insertDeckPlayer(deckPlayer);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Карточка успешно сохранена!", Toast.LENGTH_SHORT).show();
                    clearFields();
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Ошибка при сохранении карточки: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                });
            }
        });
    }

    private void clearFields() {
        editTextPrice.setText("");
        editTextDamage.setText("");
        editTextHP.setText("");
        editTextCardName.setText("");
        editTextPlayerName.setText("");
    }
}
