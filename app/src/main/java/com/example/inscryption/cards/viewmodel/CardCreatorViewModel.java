package com.example.inscryption.cards.viewmodel;// CardCreatorViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inscryption.dao.PlayerDao;
import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.DeckPlayer;
import com.example.inscryption.entity.Player;

import java.util.concurrent.Executors;

public class CardCreatorViewModel extends ViewModel {
    private final PlayerDao playerDao;
    private final MutableLiveData<String> saveResult = new MutableLiveData<>();

    public CardCreatorViewModel(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public LiveData<String> getSaveResult() {
        return saveResult;
    }

    public void saveCard(String playerName, String cardName, long price, long damage, long hp) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Player player = playerDao.getPlayerByName(playerName);
                if (player == null) {
                    player = new Player(playerName);
                    player.setPlayerId(playerDao.insertPlayer(player));
                }

                Card card = new Card(cardName, damage, hp, price);
                card.setCardId(playerDao.insertCard(card));

                DeckPlayer deckPlayer = new DeckPlayer(player.getPlayerId(), card.getCardId());
                playerDao.insertDeckPlayer(deckPlayer);

                saveResult.postValue("Карточка успешно сохранена!");
            } catch (Exception e) {
                saveResult.postValue("Ошибка при сохранении карточки: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}