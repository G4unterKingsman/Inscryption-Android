package com.example.inscryption.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "cards")
public class Card {

    @PrimaryKey(autoGenerate = true)
    private long cardId;

    private String cardName;
    private long damage;
    private long healthPoint;
    private long price;

    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                ", damage=" + damage +
                ", healthPoint=" + healthPoint +
                ", price=" + price +
                '}';
    }

    public Card(String cardName, long damage, long hp, long price) {
        this.cardName = cardName;
        this.damage = damage;
        this.healthPoint = hp;
        this.price = price;
    }

    public Card() {
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public long getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(long healthPoint) {
        this.healthPoint = healthPoint;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
