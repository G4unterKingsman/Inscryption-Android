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
import com.example.inscryption.cards.viewmodel.ShowPlayersViewModel;
import com.example.inscryption.cards.viewmodel.ShowPlayersViewModelFactory;
import com.example.inscryption.dao.PlayerDao;
import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.DeckPlayer;
import com.example.inscryption.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ShowPlayers.java
import androidx.lifecycle.ViewModelProvider;

// ShowPlayers.java
import androidx.lifecycle.ViewModelProvider;

public class ShowPlayers extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private RecyclerView recyclerViewPlayers;
    private PlayerAdapter playerAdapter;
    private ShowPlayersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        PlayerDao playerDao = MainActivity.getDatabase().playerDao();
        ShowPlayersViewModelFactory factory = new ShowPlayersViewModelFactory(playerDao);
        viewModel = new ViewModelProvider(this, factory).get(ShowPlayersViewModel.class);

        editText = findViewById(R.id.editTextSearchName);
        button = findViewById(R.id.buttonSearchPlayer);
        recyclerViewPlayers = findViewById(R.id.recyclerViewPlayers);
        recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(this));

        playerAdapter = new PlayerAdapter(this::onPlayerClick);
        recyclerViewPlayers.setAdapter(playerAdapter);

        // Наблюдение за изменениями в списке игроков
        viewModel.getPlayers().observe(this, players -> playerAdapter.setPlayers(players));

        // Наблюдение за изменениями в сообщениях
        viewModel.getMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // Наблюдение за изменениями в колоде игрока
        viewModel.getPlayerDeck().observe(this, cards -> {
            if (cards != null) {
                showPlayerDeck(cards);
            }
        });

        // Загрузка всех игроков при открытии Activity
        viewModel.loadAllPlayers();

        // Обработчик для кнопки поиска игрока по имени
        button.setOnClickListener(v -> searchPlayerByName());
    }

    // Поиск игрока по имени
    private void searchPlayerByName() {
        String playerName = editText.getText().toString().trim();
        viewModel.searchPlayerByName(playerName);
    }

    // Обработчик клика на игрока
    private void onPlayerClick(Player player) {
        viewModel.loadPlayerDeck(player);
    }

    // Отображение колоды выбранного игрока
    private void showPlayerDeck(List<Card> cards) {
        StringBuilder deckInfo = new StringBuilder();
        deckInfo.append("Колода:\n");
        for (Card card : cards) {
            deckInfo.append(card.getCardName()).append(" - Урон: ")
                    .append(card.getDamage()).append(", Здоровье: ")
                    .append(card.getHealthPoint()).append(", Цена: ")
                    .append(card.getPrice()).append("\n");
        }
        Toast.makeText(this, deckInfo.toString(), Toast.LENGTH_LONG).show();
    }
}