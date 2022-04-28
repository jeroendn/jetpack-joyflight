package com.github.jetpackjoyflight.entities.text;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CoinText extends TextEntity {

    private int coins;

    /**
     * @param initialPosition
     */
    public CoinText(Coordinate2D initialPosition) {
        super(initialPosition);

        setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
        setFill(Color.GOLD);
        this.coins = 0;
        setText();
    }

    public void setText() {
        setText("Coins: " + this.coins);
    }

    /**
     * Add coins to the score and update the text
     * @param coins
     */
    public void addCoins(int coins) {
        this.coins = this.coins + coins;
        setText();
    }
}
