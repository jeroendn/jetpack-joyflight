package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

public class Coin extends Object {

    protected int score;
    protected boolean isBig;

    public Coin(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = false;
    }

    @Override
    protected void setupEntities() {
        var powerUpSprite = new Sprite(new Coordinate2D(0, 0));
        addEntity(powerUpSprite);

        var hitBox = new HitBox(new Coordinate2D(0, 0));
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {

    }
}
