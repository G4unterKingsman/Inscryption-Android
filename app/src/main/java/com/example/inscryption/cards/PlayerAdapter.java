package com.example.inscryption.cards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inscryption.R;
import com.example.inscryption.entity.Player;

import java.util.ArrayList;
import java.util.List;

// Адаптер для отображения списка игроков
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    // Интерфейс для обработки клика по игроку
    public interface OnPlayerClickListener {
        void onPlayerClick(Player player);
    }

    private List<Player> players = new ArrayList<>();
    private final OnPlayerClickListener listener;

    // Конструктор принимает обработчик кликов
    public PlayerAdapter(OnPlayerClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Привязка макета элемента игрока
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        // Получаем игрока по позиции и привязываем данные
        Player player = players.get(position);
        holder.bind(player);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    // Метод для обновления списка игроков в адаптере
    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    // ViewHolder для отображения данных игрока
    class PlayerViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewPlayerName;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPlayerName = itemView.findViewById(R.id.textViewPlayerName);
        }

        public void bind(Player player) {
            textViewPlayerName.setText(player.getPlayerName());
            itemView.setOnClickListener(v -> listener.onPlayerClick(player));
        }
    }
}