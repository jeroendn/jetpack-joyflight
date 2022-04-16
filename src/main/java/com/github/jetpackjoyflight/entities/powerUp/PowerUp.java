package com.github.jetpackjoyflight.entities.powerUp;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Timer;
import java.util.TimerTask;

public class PowerUp extends Object {

    protected final String[] options = {}; // TODO fill in options

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
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (player.getHealth() > 0) {
                    setAnchorLocation(new Coordinate2D(getSceneWidth(), player.getAnchorLocation().getY()));
                }
            }
        }, 2, 2000); // TODO make random
    }

    public String getPowerUp() {
        // TODO return a random item from this.options
        return "";
    }
}
