package com.github.jetpackjoyflight.entities.coin;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.jetpackjoyflight.entities.HitBox;
import com.github.jetpackjoyflight.entities.Object;
import javafx.scene.paint.Color;

public class CoinHitBox extends HitBox implements Collider {

    public CoinHitBox(final Coordinate2D initialPosition, Object object) {
        super(initialPosition, object);

//        setWidth(0);
//        setHeight(0);
        setFill(Color.TRANSPARENT);
    }
}
