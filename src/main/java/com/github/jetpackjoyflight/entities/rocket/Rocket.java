package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Random;

public class Rocket extends DynamicCompositeEntity implements SceneBorderCrossingWatcher {

    private final Player player;

    public Rocket(final Coordinate2D initialLocation, Player player) {
        super(initialLocation);
        setMotion(9, Direction.LEFT);

        this.player = player;
    }

    @Override
    protected void setupEntities() {
        var swordFishSprite = new RocketSprite(new Coordinate2D(0, 0));
        addEntity(swordFishSprite);

        var hitBox = new HitBox(new Coordinate2D(88, 41));
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
        System.out.println(this.player);
        setAnchorLocation(new Coordinate2D(getSceneWidth(), new Random().nextInt((int) getSceneHeight() - 150)));
    }
}
