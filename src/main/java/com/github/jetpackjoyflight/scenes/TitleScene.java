package com.github.jetpackjoyflight.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.jetpackjoyflight.entities.buttons.StartButton;
import com.github.jetpackjoyflight.Main;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleScene extends StaticScene {

    private Main main;

    public TitleScene(Main main) {
        this.main = main;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/background-static.jpg");
    }

    @Override
    public void setupEntities() {
        var JJText = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "Jetpack Joyflight");
        JJText.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        JJText.setFill(Color.LIGHTBLUE);
        JJText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
        addEntity(JJText);

        var playGameText = new StartButton(new Coordinate2D(getWidth() / 2, getHeight() / 2), main);
        playGameText.setAnchorPoint(AnchorPoint.TOP_CENTER);
        addEntity(playGameText);
    }
}
