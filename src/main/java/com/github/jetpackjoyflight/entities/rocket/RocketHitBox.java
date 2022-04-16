package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.jetpackjoyflight.entities.HitBox;
import com.github.jetpackjoyflight.entities.Object;
import javafx.scene.paint.Color;

public class RocketHitBox extends HitBox implements Collider {

    public RocketHitBox(final Coordinate2D initialPosition, Object object) {
        super(initialPosition, object);

        setWidth(0);
        setHeight(0);
        setFill(Color.TRANSPARENT);
    }
}
