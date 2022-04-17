package com.github.jetpackjoyflight.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.jetpackjoyflight.entities.Player;
import com.github.jetpackjoyflight.entities.powerUp.PowerUp;
import com.github.jetpackjoyflight.entities.rocket.Rocket;
import com.github.jetpackjoyflight.entities.text.DistanceText;
import com.github.jetpackjoyflight.entities.text.HealthText;
import com.github.jetpackjoyflight.Main;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameLevel extends DynamicScene implements EntitySpawnerContainer, TileMapContainer {

    private final Main main;
    private Player player;
    private final Timer timer = new Timer();

    public GameLevel(Main main) {
        this.main = main;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/background-fast.gif");
//        setBackgroundAudio("audio/coconut_mall.mp3");
//        setBackgroundAudioVolume(.05);
    }

    @Override
    public void setupEntities() {
        var healthText = new HealthText(new Coordinate2D(0, 0));
        addEntity(healthText);

        var distanceText = new DistanceText(new Coordinate2D(0, 30));
        addEntity(distanceText);

        this.player = new Player(new Coordinate2D(50, 1), healthText, distanceText, main);
        addEntity(player);

        for (int i = 0; i < 100; i++) {
            this.timer.schedule(new TimerTask() {
                public void run() {
                    addEntity(new Rocket(new Coordinate2D(getWidth(), new Random().nextInt((int) getHeight())), player));
                    System.out.println("Rocket spawned 1");
                }
            }, new Random().nextInt(100000));
        }


        this.timer.schedule(new TimerTask() {
            public void run() {
                addEntity(new PowerUp(new Coordinate2D(getWidth(), 300), player));
            }
        }, new Random().nextInt(5000));
    }

    @Override
    public void setupEntitySpawners() {
//        addEntitySpawner(new BubbleSpawner(getWidth(), getHeight()));
    }

    @Override
    public void setupTileMaps() {
//        addTileMap(new CoralTileMap());
    }

    public String getDistanceText() {
        return this.player.getDistanceText();
    }
}
