package com.example.inscryption.cards;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inscryption.MainActivity;
import com.example.inscryption.R;
import com.example.inscryption.dao.PlayerDao;
import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.DeckPlayer;
import com.example.inscryption.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowPlayers extends AppCompatActivity {
    EditText editText;
    Button button;
    RecyclerView recyclerViewPlayers;
    PlayerDao playerDao = MainActivity.getDatabase().playerDao();
    PlayerAdapter playerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        editText = findViewById(R.id.editTextSearchName);
        button = findViewById(R.id.buttonSearchPlayer);
        recyclerViewPlayers = findViewById(R.id.recyclerViewPlayers);
        // Установка LayoutManager для RecyclerView
        recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(this));

        // Настройка адаптера для списка игроков
        playerAdapter = new PlayerAdapter(this::onPlayerClick);
        recyclerViewPlayers.setAdapter(playerAdapter);

        // Загрузка всех игроков при открытии Activity
        loadAllPlayers();

        // Обработчик для кнопки поиска игрока по имени
        button.setOnClickListener(v -> searchPlayerByName());
    }

    // Загрузка всех игроков из базы данных
    private void loadAllPlayers() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Player> players = playerDao.getAllPlayers();
            runOnUiThread(() -> playerAdapter.setPlayers(players));
        });
    }

    // Поиск игрока по имени
    private void searchPlayerByName() {
        String playerName = editText.getText().toString().trim();
        if (playerName.isEmpty()) {
            Toast.makeText(this, "Введите имя для поиска", Toast.LENGTH_SHORT).show();
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Player player = playerDao.getPlayerByName(playerName);
            if (player != null) {
                List<Player> singlePlayerList = List.of(player);
                runOnUiThread(() -> playerAdapter.setPlayers(singlePlayerList));
            } else {
                runOnUiThread(() -> Toast.makeText(this, "Игрок не найден", Toast.LENGTH_SHORT).show());
            }
        });
    }

    // Обработчик клика на игрока
    private void onPlayerClick(Player player) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<Card> cards = playerDao.getDeckForPlayer(player.getPlayerId());
            runOnUiThread(() -> showPlayerDeck(player, cards));
        });
    }

    // Отображение колоды выбранного игрока
    private void showPlayerDeck(Player player, List<Card> cards) {
        StringBuilder deckInfo = new StringBuilder();
        deckInfo.append("Колода игрока ").append(player.getPlayerName()).append(":\n");
        for (Card card : cards) {
            deckInfo.append(card.getCardName()).append(" - Урон: ")
                    .append(card.getDamage()).append(", Здоровье: ")
                    .append(card.getHealthPoint()).append(", Цена: ")
                    .append(card.getPrice()).append("\n");
        }
        Toast.makeText(this, deckInfo.toString(), Toast.LENGTH_LONG).show();
    }

}
