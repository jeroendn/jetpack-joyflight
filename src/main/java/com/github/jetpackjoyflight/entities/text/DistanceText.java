package com.github.jetpackjoyflight.entities.text;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DistanceText extends TextEntity {

    public DistanceText(Coordinate2D initialPosition) {
        super(initialPosition);

        setFont(Font.font("Roboto", FontWeight.NORMAL, 30));
        setFill(Color.PINK);
    }

    public void setText(final int distance) {
        setText("Distance: " + distance);
    }
}

