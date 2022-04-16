package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Sprite extends SpriteEntity {

    public Sprite(final Coordinate2D location) {
        super("sprites/missile.png", location);
    }
}
