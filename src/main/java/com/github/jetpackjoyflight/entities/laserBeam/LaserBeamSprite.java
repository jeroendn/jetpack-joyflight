package com.github.jetpackjoyflight.entities.laserBeam;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class LaserBeamSprite extends SpriteEntity {

    /**
     * The constructor for the LaserBeamSprite class.
     * @param location
     */
    public LaserBeamSprite(final Coordinate2D location) {
        super("sprites/noimg.png", location);
    }
}
