package com.github.jetpackjoyflight.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.jetpackjoyflight.entities.Player;
import com.github.jetpackjoyflight.entities.coin.Coin;
import com.github.jetpackjoyflight.entities.laserBeam.LaserBeam;
import com.github.jetpackjoyflight.entities.laserWall.LaserWall;
import com.github.jetpackjoyflight.entities.powerUp.PowerUp;
import com.github.jetpackjoyflight.entities.rocket.Rocket;
import com.github.jetpackjoyflight.entities.text.CoinText;
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
    DistanceText distanceText;
    CoinText coinText;

    /**
     * @param main
     */
    public GameLevel(Main main) {
        this.main = main;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/background-fast.gif");
        setBackgroundAudio("audio/coconut_mall.mp3");
        setBackgroundAudioVolume(.05);
    }

    @Override
    public void setupEntities() {
        var healthText = new HealthText(new Coordinate2D(0, 0));
        addEntity(healthText);

        this.distanceText = new DistanceText(new Coordinate2D(0, 30));
        addEntity(this.distanceText);

        this.coinText = new CoinText(new Coordinate2D(0, 60));
        addEntity(this.coinText);

        this.player = new Player(new Coordinate2D(50, 1), healthText, distanceText, coinText, main);
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
        return this.distanceText.getText();
    }

    /**
     * @return
     */
    public String getCoinText() {
        return this.coinText.getText();
    }

    /**
     * @return Random height in range of screen height
     */
    private int getRandomHeight() {
        return new Random().nextInt((int) getHeight()) - 100;
    }

    /**
     * Generate sections with objects for the level
     *
     * @return list of generated sections
     */
    private Section[] generateSections() {
        int sectionCount = 100;
        Section[] sections = new Section[sectionCount];

        for (int i = 0; i < sectionCount; i++) {
            sections[i] = new Section(i + 1, this.player, (int) getWidth(), (int) getHeight());
        }

        for (Section section : sections) {
            this.timer.schedule(new TimerTask() {
                public void run() {

                    int spawnTimeMin = (section.number != 1) ? section.duration * (section.number - 1) : 0;
                    int spawnTimeMax = section.duration * section.number;

                    for (Object object : section.objects) {
                        int spawnTime = new Random().nextInt(spawnTimeMax - spawnTimeMin) + spawnTimeMin;
                        timer.schedule(new TimerTask() {
                            public void run() {
                                if (object instanceof LaserBeam) {
                                    System.out.println("Spawning Object LaserBeam -- Not yet implemented!");
                                } else {
                                    addEntity((YaegerEntity) object);
                                }
                            }
                        }, spawnTime);
                    }

                }
            }, (long) section.duration * section.number);
        }

        return sections;
    }
}
