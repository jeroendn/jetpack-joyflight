package com.github.jetpackjoyflight.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.jetpackjoyflight.entities.Player;
import com.github.jetpackjoyflight.entities.coin.Coin;
import com.github.jetpackjoyflight.entities.laserWall.LaserWall;
import com.github.jetpackjoyflight.entities.powerUp.PowerUp;
import com.github.jetpackjoyflight.entities.rocket.Rocket;
import com.github.jetpackjoyflight.entities.section.Section;
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

    /**
     * @param main
     */
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

        this.timer.schedule(new TimerTask() {
            public void run() {
                addEntity(new Rocket(new Coordinate2D(getWidth(), getRandomHeight()), player));
            }
        }, new Random().nextInt(5000));

        this.timer.schedule(new TimerTask() {
            public void run() {
                addEntity(new Rocket(new Coordinate2D(getWidth(), getRandomHeight()), player));
            }
        }, new Random().nextInt(5000));

        this.generateSections();
    }

    @Override
    public void setupEntitySpawners() {
    }

    @Override
    public void setupTileMaps() {
    }

    /**
     * @return
     */
    public String getDistanceText() {
        return this.player.getDistanceText();
    }

    /**
     * @return Random height in range of screen height
     */
    private int getRandomHeight() {
        return new Random().nextInt((int)getHeight());
    }

    /**
     * Generate sections with objects for the level
     * @return list of generated sections
     */
    private Section[] generateSections() {
        int sectionCount = 5;
        Section[] sections = new Section[sectionCount];

        for (int i = 0; i < sectionCount; i++) {
            sections[i] = new Section(i + 1);
            System.out.println(i);
        }

        for (Section section : sections) {
            this.timer.schedule(new TimerTask() {
                public void run() {
                    System.out.println("new SECTION");

                    for (String object : section.objects) {
                        timer.schedule(new TimerTask() {
                            public void run() {

                                System.out.println(object);
                                switch (object) {
                                    case "coin":
                                        addEntity(new Coin(new Coordinate2D(getWidth(), getRandomHeight()), player));
                                        break;
                                    case "powerup":
                                        addEntity(new PowerUp(new Coordinate2D(getWidth(), getRandomHeight()), player));
                                    default:
                                    case "laserwall":
                                        addEntity(new LaserWall(new Coordinate2D(getWidth(), getRandomHeight()), player));
                                        break;
                                }

                            }
                        }, new Random().nextInt(section.duration * section.number));
                    }

                }
            }, (long) section.duration * section.number);
        }

        return sections;
    }
}
