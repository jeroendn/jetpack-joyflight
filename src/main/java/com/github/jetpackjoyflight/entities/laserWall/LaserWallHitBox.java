package com.github.jetpackjoyflight.entities.laserWall;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.jetpackjoyflight.entities.HitBox;
import com.github.jetpackjoyflight.entities.Object;
import javafx.scene.paint.Color;

public class LaserWallHitBox extends HitBox implements Collider {

    /**
     * The constructor for the LaserWallHitBox.
     * @param initialPosition
     * @param object The object that this hit box belongs to
     */
    public LaserWallHitBox(final Coordinate2D initialPosition, Object object) {
        super(initialPosition, object);

        int width;
        int height;

        switch (((LaserWall)object).direction) {
            case "vertical":
                width = 20;
                height = 115;
                break;
            case "horizontal":
                width = 115;
                height = 20;
                break;
            default:
            case "diagonal":
                width = 110;
                height = 110;
                break;
        }

        setWidth(width);
        setHeight(height);

        setFill(Color.TRANSPARENT);
    }
}
