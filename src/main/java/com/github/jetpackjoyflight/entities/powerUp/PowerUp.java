package com.github.jetpackjoyflight.entities.powerUp;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Random;

public class PowerUp extends Object {

    protected final String[] options = {"health", "coins", "mech"};

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
    }

    /**
     * @return random power up
     */
    public String getPowerUp() {
        int index = new Random().nextInt(this.options.length);
        String powerUp = this.options[index];

        switch (powerUp) {
            case "coins":
                handleCoinPowerUp();
                break;
            case "mech":
                handleMechPowerUp();
                break;
            default:
            case "health":
                handleHealthPowerUp();
                break;
        }

        return powerUp;
    }

    /**
     * Handle what happens when the player collects a health power up
     */
    protected void handleHealthPowerUp() {
        this.player.addHealth(1);
    }

    /**
     * Handle what happens when the player collects a mech power up
     */
    protected void handleMechPowerUp() {
        // TODO
    }

    /**
     * Handle what happens when the player collects a coin power up
     */
    protected void handleCoinPowerUp() {
       this.player.addCoins(20);
    }
}
