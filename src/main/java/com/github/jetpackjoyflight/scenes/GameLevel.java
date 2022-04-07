package com.github.jetpackjoyflight.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.jetpackjoyflight.entities.Hanny;
import com.github.jetpackjoyflight.entities.Sharky;
import com.github.jetpackjoyflight.entities.map.CoralTileMap;
import com.github.jetpackjoyflight.entities.swordfish.SwordFish;
import com.github.jetpackjoyflight.entities.text.BubblesPoppedText;
import com.github.jetpackjoyflight.entities.text.HealthText;
import com.github.jetpackjoyflight.spawners.BubbleSpawner;
import com.github.jetpackjoyflight.Main;

public class GameLevel extends DynamicScene implements EntitySpawnerContainer, TileMapContainer {

    private Main main;

    public GameLevel(Main main) {
        this.main = main;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/bg.png");
        setBackgroundAudio("audio/coconut_mall.mp3");
    }

    @Override
    public void setupEntities() {
        var healthText = new HealthText(new Coordinate2D(0, 0));
        addEntity(healthText);

        var bubblesPoppedText = new BubblesPoppedText(new Coordinate2D(0, 30));
        addEntity(bubblesPoppedText);

        addEntity(new Hanny(new Coordinate2D(1, 1), healthText, bubblesPoppedText, main));
        addEntity(new SwordFish(new Coordinate2D(200, 300)));
        addEntity(new Sharky(new Coordinate2D(0, 100)));
    }

    @Override
    public void setupEntitySpawners() {
        addEntitySpawner(new BubbleSpawner(getWidth(), getHeight()));
    }

    @Override
    public void setupTileMaps() {
        addTileMap(new CoralTileMap());
    }
}
