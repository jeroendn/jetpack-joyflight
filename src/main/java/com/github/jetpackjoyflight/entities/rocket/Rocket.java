package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Rocket extends Object {

    protected final int triggerTime = 2000;

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

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (player.getHealth() > 0) {
                    setAnchorLocation(new Coordinate2D(getSceneWidth(), player.getAnchorLocation().getY()));
                }
            }
        }, 2, triggerTime);
    }
}
