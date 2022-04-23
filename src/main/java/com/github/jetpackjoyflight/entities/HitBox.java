package com.github.jetpackjoyflight.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

public abstract class HitBox extends RectangleEntity implements Collider {

    public Object object;

    /**
     * Create a new HitBox.
     * @param initialPosition
     * @param object The object that this HitBox belongs to.
     */
    public HitBox(final Coordinate2D initialPosition, Object object) {
        super(initialPosition);
        this.object = object;
    }
}
