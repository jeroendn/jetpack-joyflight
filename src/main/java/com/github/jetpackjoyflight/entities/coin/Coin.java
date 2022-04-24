package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;
import com.github.jetpackjoyflight.entities.text.CoinText;

import java.util.Random;

public class Coin extends Object {

    private final CoinText coinText;
    protected boolean isBig;

    public Coin(Coordinate2D initialLocation, Player player, CoinText coinText) {
        super(initialLocation, player);
        this.isHostile = false;
        this.coinText = coinText;
        this.isBig = new Random().nextBoolean();
    }

    @Override
    protected void setupEntities() {
        var sprite = new CoinSprite(new Coordinate2D(0, 0), this.isBig);
        addEntity(sprite);

        var hitBox = new CoinHitBox(new Coordinate2D(0, 0), this);
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
    }

    public void addCoin() {
        if (this.isBig) {
           coinText.addCoins(5);
        } else {
            coinText.addCoins(1);
        }
    }
}
