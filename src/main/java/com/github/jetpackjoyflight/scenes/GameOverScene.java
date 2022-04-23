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

    private final GameLevel gameLevel;
    private Main main;

    /**
     * Constructor for the GameOverScene.
     * @param main
     * @param gameLevel
     */
    public GameOverScene(Main main, GameLevel gameLevel) {
        this.main = main;
        this.gameLevel = gameLevel;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/angry-cat-furious-cat.gif");
        setBackgroundAudio("audio/fail-trombone.mp3");
    }

    @Override
    public void setupEntities() {
        var gameOverText = new TextEntity(new Coordinate2D(getWidth() / 2, (getHeight() / 2) - 80), "Game Over");
        gameOverText.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        gameOverText.setFill(Color.RED);
        gameOverText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
        addEntity(gameOverText);

        var distance = gameLevel.getDistanceText();
        var distanceText = new TextEntity(new Coordinate2D(getWidth() / 2, (getHeight() / 2) - 10), distance);
        distanceText.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        distanceText.setFill(Color.GOLD);
        distanceText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
        addEntity(distanceText);

        var coins = gameLevel.getCoinText();
        var coinsText = new TextEntity(new Coordinate2D(getWidth() / 2, (getHeight() / 2) + 80), coins);
        coinsText.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        coinsText.setFill(Color.GOLD);
        coinsText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
        addEntity(coinsText);

        var playGameButton = new StartButton(new Coordinate2D((getWidth() / 2) - 40, (getHeight() / 2) + 90), main);
        playGameButton.setAnchorPoint(AnchorPoint.TOP_RIGHT);
        addEntity(playGameButton);

        var exitGameButton = new QuitButton(new Coordinate2D((getWidth() / 2) + 40, (getHeight() / 2) + 90), main);
        exitGameButton.setAnchorPoint(AnchorPoint.TOP_LEFT);
        addEntity(exitGameButton);
    }
}
