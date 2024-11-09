package com.example.inscryption.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.inscryption.entity.Card;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public DeckPlayer() {
    }

    public DeckPlayer(long playerId, long cardId) {
        this.playerId = playerId;
        this.cardId = cardId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
}
