package com.github.jetpackjoyflight.entities.powerUp;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends Object {

    protected final String[] options = {"health", "coins", "mech"};
    protected final int spawnIntervalMin = 10000;
    protected final int spawnIntervalMax = 30000;
    protected boolean triggered = false;

    public PowerUp(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = false;
    }

    @Override
    protected void setupEntities() {
        var sprite = new PowerUpSprite(new Coordinate2D(0, 0));
        addEntity(sprite);

        var hitBox = new PowerUpHitBox(new Coordinate2D(0, 0), this);
        addEntity(hitBox);
    }

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

    public String getPowerUp() {
        // TODO return a random item from this.options
        return "";
    }
}
