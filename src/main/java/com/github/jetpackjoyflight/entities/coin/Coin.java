package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Random;

public class Coin extends Object {

    public boolean isBig;

    /**
     * Constructor for a coin.
     * @param initialLocation
     * @param player
     */
    public Coin(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = false;
        this.isBig = new Random().nextBoolean();
    }

    @Override
    protected void setupEntities() {
        var sprite = new CoinSprite(new Coordinate2D(0, 0), this.isBig);
        addEntity(sprite);

        var hitBox = new CoinHitBox(new Coordinate2D(0, 0), this);
        addEntity(hitBox);
    }

    /**
     * @param border
     */
    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
    }
}
