package com.github.jetpackjoyflight.entities.laserWall;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class LaserWallSprite extends SpriteEntity {

    public LaserWallSprite(final Coordinate2D location) {
        super("sprites/laserwall.png", location, new Size(150, 150));
    }
}
