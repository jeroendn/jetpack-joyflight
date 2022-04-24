package com.github.jetpackjoyflight.entities.laserWall;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class LaserWallSprite extends SpriteEntity {

    /**
     * @param location
     * @param imgPath location of the image
     */
    public LaserWallSprite(final Coordinate2D location, String imgPath) {
        super(imgPath, location, new Size(150, 150));
    }
}
