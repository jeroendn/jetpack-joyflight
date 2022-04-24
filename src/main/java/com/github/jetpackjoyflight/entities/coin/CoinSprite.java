package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class CoinSprite extends SpriteEntity {

    public CoinSprite(final Coordinate2D location, final Boolean isBig) {
        super("sprites/coin.png", location, (isBig) ? new Size(75, 75) : new Size(50, 50));
    }
}
