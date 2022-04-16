package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Rocket extends Object {

    protected final int triggerTime = 2000;
    private Timer timer;

    public Rocket(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = true;
    }

    @Override
    protected void setupEntities() {
        var swordFishSprite = new Sprite(new Coordinate2D(0, 0));
        addEntity(swordFishSprite);

        var hitBox = new HitBox(new Coordinate2D(0, 0));
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (player.getHealth() > 0) {
                    setAnchorLocation(new Coordinate2D(getSceneWidth(), player.getAnchorLocation().getY()));
                }
            }
        }, 2, triggerTime);
    }
}
