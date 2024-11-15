package com.example.inscryption.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;


@Entity(
        tableName = "deck_players",
        primaryKeys = {"playerId", "cardId"},
        foreignKeys = {
                @ForeignKey(entity = Player.class, parentColumns = "playerId", childColumns = "playerId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Card.class, parentColumns = "cardId", childColumns = "cardId", onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("playerId"), @Index("cardId")}
)
public class DeckPlayer {

    private long playerId;

    private long cardId;

    public long getPlayerId() {
        return playerId;
    }

    public DeckPlayer(long playerId, long cardId) {
        this.playerId = playerId;
        this.cardId = cardId;
    }

    public long getCardId() {
        return cardId;
    }

}
