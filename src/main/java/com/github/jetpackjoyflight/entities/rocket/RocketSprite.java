package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class RocketSprite extends SpriteEntity {

    public RocketSprite(final Coordinate2D location) {
        super("sprites/missile.png", location);
    }
}
