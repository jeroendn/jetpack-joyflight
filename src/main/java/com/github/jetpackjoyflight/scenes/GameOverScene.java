package com.github.jetpackjoyflight.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.jetpackjoyflight.entities.buttons.QuitButton;
import com.github.jetpackjoyflight.entities.buttons.StartButton;
import com.github.jetpackjoyflight.Main;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GameOverScene extends StaticScene {

    private Main main;

    public GameOverScene(Main main) {
        this.main = main;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/angry-cat-furious-cat.gif");
//        setBackgroundAudio("audio/fail-trombone.mp3");
    }

    @Override
    public void setupEntities() {
        var gameOverText = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "Game Over");
        gameOverText.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        gameOverText.setFill(Color.DEEPPINK);
        gameOverText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
        addEntity(gameOverText);

        var playGameButton = new StartButton(new Coordinate2D((getWidth() / 2) - 20, getHeight() / 2), main);
        playGameButton.setAnchorPoint(AnchorPoint.TOP_RIGHT);
        addEntity(playGameButton);

        var exitGameButton = new QuitButton(new Coordinate2D((getWidth() / 2) + 20, getHeight() / 2), main);
        exitGameButton.setAnchorPoint(AnchorPoint.TOP_LEFT);
        addEntity(exitGameButton);
    }
}
