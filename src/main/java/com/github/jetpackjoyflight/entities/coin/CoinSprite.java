package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class CoinSprite extends SpriteEntity {

    public CoinSprite(final Coordinate2D location) {
        super("sprites/coin.png", location, new Size(75, 75));
    }
}
