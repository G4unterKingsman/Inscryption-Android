package com.example.inscryption.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "players")
public class Player {
    @PrimaryKey(autoGenerate = true)
    private long playerId;

    @ColumnInfo(name = "player_name")
    private String playerName;

    private transient DeckPlayer deckPlayer;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player() {
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public DeckPlayer getDeckPlayer() {
        return deckPlayer;
    }

    public void setDeckPlayer(DeckPlayer deckPlayer) {
        this.deckPlayer = deckPlayer;
    }
}
