package com.github.jetpackjoyflight.entities.powerUp;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class PowerUpSprite extends SpriteEntity {

    public PowerUpSprite(final Coordinate2D location) {
        super("sprites/powerup.png", location, new Size(75, 75));
    }
}
