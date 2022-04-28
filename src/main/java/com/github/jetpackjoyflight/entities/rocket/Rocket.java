package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Rocket extends Object {

    protected final int spawnIntervalMin = 0;
    protected final int spawnIntervalMax = 10000;
    protected boolean triggered = false;

    /**
     *
     * @param initialLocation the initial location of the rocket
     * @param player The player which can interact with the rocket
     */
    public Rocket(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = true;
    }

    @Override
    protected void setupEntities() {
        var sprite = new RocketSprite(new Coordinate2D(0, 0));
        addEntity(sprite);

        var hitBox = new RocketHitBox(new Coordinate2D(0, 0), this);
        addEntity(hitBox);
    }

    /**
     * @param border the border of the scene
     */
    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
        if (!this.triggered) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (player.getHealth() > 0) {
                        setAnchorLocation(new Coordinate2D(getSceneWidth(), player.getAnchorLocation().getY()));
                    }
                    triggered = false;
                }
            }, new Random().nextInt(this.spawnIntervalMax + 1 - this.spawnIntervalMin) + this.spawnIntervalMin);
            this.triggered = true;
        }
    }
}
