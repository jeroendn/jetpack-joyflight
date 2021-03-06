package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.jetpackjoyflight.entities.HitBox;
import com.github.jetpackjoyflight.entities.Object;
import javafx.scene.paint.Color;

public class CoinHitBox extends HitBox implements Collider {

    /**
     * @param initialPosition
     * @param object The coin that this hit box belongs to.
     */
    public CoinHitBox(final Coordinate2D initialPosition, Object object) {
        super(initialPosition, object);

        setWidth(70);
        setHeight(80);
        setFill(Color.TRANSPARENT);
    }
}
