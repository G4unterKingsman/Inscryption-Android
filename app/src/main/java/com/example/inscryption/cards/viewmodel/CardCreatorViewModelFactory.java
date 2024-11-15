package com.example.inscryption.cards.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.inscryption.dao.PlayerDao;

public class CardCreatorViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final PlayerDao playerDao;

    public CardCreatorViewModelFactory(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CardCreatorViewModel.class)) {
            return (T) new CardCreatorViewModel(playerDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}