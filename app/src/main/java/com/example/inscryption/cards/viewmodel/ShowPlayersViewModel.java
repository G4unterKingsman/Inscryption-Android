package com.example.inscryption.cards.viewmodel;// ShowPlayersViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inscryption.dao.PlayerDao;
import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.Player;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowPlayersViewModel extends ViewModel {
    private final PlayerDao playerDao;
    private final MutableLiveData<List<Player>> players = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();
    private final MutableLiveData<List<Card>> playerDeck = new MutableLiveData<>();

    private final ExecutorService executorService = Executors.newSingleThreadExecutor(); // Пул для фоновых задач

    public ShowPlayersViewModel(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public LiveData<List<Player>> getPlayers() {
        return players;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public LiveData<List<Card>> getPlayerDeck() {
        return playerDeck;
    }

    // Загрузка всех игроков
    public void loadAllPlayers() {
        executorService.execute(() -> {
            try {
                List<Player> allPlayers = playerDao.getAllPlayers();
                players.postValue(allPlayers);  // обновление LiveData на основном потоке
            } catch (Exception e) {
                message.postValue("Ошибка при загрузке игроков: " + e.getMessage());
            }
        });
    }

    // Поиск игрока по имени
    public void searchPlayerByName(String playerName) {
        if (playerName.isEmpty()) {
            message.setValue("Введите имя для поиска");
            return;
        }
        executorService.execute(() -> {
            try {
                Player player = playerDao.getPlayerByName(playerName);
                if (player != null) {
                    players.postValue(List.of(player));  // Обновление списка только с одним найденным игроком
                } else {
                    message.postValue("Игрок не найден");
                }
            } catch (Exception e) {
                message.postValue("Ошибка при поиске игрока: " + e.getMessage());
            }
        });
    }

    // Загрузка колоды для выбранного игрока
    public void loadPlayerDeck(Player player) {
        executorService.execute(() -> {
            try {
                List<Card> deck = playerDao.getDeckForPlayer(player.getPlayerId());
                playerDeck.postValue(deck);  // Обновление LiveData колоды
            } catch (Exception e) {
                message.postValue("Ошибка при загрузке колоды: " + e.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown(); // Освобождаем ресурсы при завершении ViewModel
    }
}