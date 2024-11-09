package com.example.inscryption.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.DeckPlayer;
import com.example.inscryption.entity.Player;

import java.util.List;

@Dao
public interface PlayerDao {
    // Вставка игрока
    @Insert
    long insertPlayer(Player player);

    // Вставка карты
    @Insert
    long insertCard(Card card);

    // Вставка колоды
    @Insert
    void insertDeckPlayer(DeckPlayer deckPlayer);

    // Получение игрока по имени
    @Query("SELECT * FROM players WHERE player_name = :name LIMIT 1")
    Player getPlayerByName(String name);

    @Query("SELECT * FROM players WHERE playerId = :id LIMIT 1")
    Player getPlayerById(long id);

    // Получение всех игроков
    @Query("SELECT * FROM players")
    List<Player> getAllPlayers();


    // Получение всех карт игрока
    @Query("SELECT c.* FROM cards c INNER JOIN deck_players dp ON c.cardId = dp.cardId WHERE dp.playerId = :playerId")
    List<Card> getDeckForPlayer(long playerId);

    // Удаление колоды игрока
    @Query("DELETE FROM deck_players WHERE playerId = :playerId")
    void deleteDeckForPlayer(long playerId);
}