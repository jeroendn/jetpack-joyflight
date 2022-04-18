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

    /**
     * @param initialLocation the initial location of the power up
     * @param player
     */
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

    /**
     * @param border
     */
    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
        if (!this.triggered) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (player.getHealth() > 0) {
                        setAnchorLocation(new Coordinate2D(getSceneWidth(), new Random().nextInt((int)getSceneHeight())));
                    }
                    triggered = false;
                }
            }, new Random().nextInt(this.spawnIntervalMax + 1 - this.spawnIntervalMin) + this.spawnIntervalMin);
            this.triggered = true;
        }
    }

    /**
     * @return random power up
     */
    public String getPowerUp() {
        int index = new Random().nextInt(this.options.length);
        String powerUp = this.options[index];

        switch (powerUp) {
            case "health" -> handleHealthPowerUp();
            case "mech" -> handleMechPowerUp();
            case "coins" -> handleCoinPowerUp();
            default -> handleHealthPowerUp(); // Even if no power up was found we give the health power up as fallback
        }

        return powerUp;
    }

    protected void handleHealthPowerUp() {
        this.player.addHealth(1);
    }

    protected void handleMechPowerUp() {
        // TODO
    }

    protected void handleCoinPowerUp() {
        // TODO
    }
}
