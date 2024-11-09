package com.example.inscryption.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.inscryption.dao.PlayerDao;
import com.example.inscryption.entity.Card;
import com.example.inscryption.entity.DeckPlayer;
import com.example.inscryption.entity.Player;

@Database(entities = {Player.class, Card.class, DeckPlayer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();


}
