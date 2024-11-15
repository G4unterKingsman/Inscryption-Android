package com.example.inscryption.cards.viewmodel;

// ShowPlayersViewModelFactory.java
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.inscryption.dao.PlayerDao;

public class ShowPlayersViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final PlayerDao playerDao;

    public ShowPlayersViewModelFactory(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ShowPlayersViewModel.class)) {
            return (T) new ShowPlayersViewModel(playerDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}