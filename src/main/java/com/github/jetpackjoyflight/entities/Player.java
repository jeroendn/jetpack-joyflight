package com.github.jetpackjoyflight.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.jetpackjoyflight.entities.coin.Coin;
import com.github.jetpackjoyflight.entities.powerUp.PowerUp;
import com.github.jetpackjoyflight.entities.powerUp.PowerUpHitBox;
import com.github.jetpackjoyflight.entities.text.CoinText;
import com.github.jetpackjoyflight.entities.text.DistanceText;
import com.github.jetpackjoyflight.entities.text.HealthText;
import com.github.jetpackjoyflight.Main;
import javafx.scene.input.KeyCode;

import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends DynamicSpriteEntity implements SceneBorderTouchingWatcher, KeyListener, Collided, Collider, Newtonian {

    private final HealthText healthText;
    private final DistanceText distanceText;
    private final CoinText coinText;
    private final Main main;
    private int health = 1;
    private int distance = 0;
    private Long lastHit = null;

    /**
     * Constructs a new Player with the given parameters.
     * @param location the initial location of the player
     * @param healthText the health text
     * @param distanceText the distance text
     * @param main
     */
    public Player(final Coordinate2D location, final HealthText healthText, final DistanceText distanceText, final CoinText coinText, final Main main) {
        super("sprites/player.png", location, new Size(200, 100), 1, 1);

        this.healthText = healthText;
        this.distanceText = distanceText;
        this.coinText = coinText;
        this.main = main;
        healthText.setText(health);
        distanceText.setText(distance);

        setGravityConstant(20);
        setFrictionConstant(0.9);
        this.addDistance();
    }

    /**
     * @param pressedKeys the keys that are pressed
     */
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (
                pressedKeys.contains(KeyCode.UP)
                ||
                pressedKeys.contains(KeyCode.SPACE)
                ||
                pressedKeys.contains(KeyCode.W)
        ) {
            setMotion(100, Direction.UP);
        }
    }

    /**
     * Handle what happens when the player collides with an object
     * @param collidingObject the object that is colliding with the player
     */
    @Override
    public void onCollision(Collider collidingObject) {
        if ((Objects.isNull(lastHit) || (lastHit + 2000) < System.currentTimeMillis()) && collidingObject instanceof HitBox) {
            if (((HitBox) collidingObject).object.isHostile) {

                healthText.setText(--health);

                if (health == 0) {
                    this.main.setActiveScene(2);
                }

            } else {
                if (((HitBox) collidingObject).object instanceof Coin) {
                    var coins = ((Coin) ((HitBox) collidingObject).object).isBig ? 5 : 1;
                    addCoins(coins);
                }

                if (collidingObject instanceof PowerUpHitBox) {
                    ((PowerUp)((PowerUpHitBox) collidingObject).object).getPowerUp();
                }
            }
            lastHit = System.currentTimeMillis();
        }
    }

    /**
     * @param border the border that is touched
     */
    @Override
    public void notifyBoundaryTouching(final SceneBorder border) {
        setSpeed(0);

        switch (border) {
            case TOP:
                setAnchorLocationY(1);
                break;
            case BOTTOM:
                setAnchorLocationY(getSceneHeight() - getHeight() - 1);
                break;
            case LEFT:
                setAnchorLocationX(1);
                break;
            case RIGHT:
                setAnchorLocationX(getSceneWidth() - getWidth() - 1);
            default:
                break;
        }
    }

    /**
     * Updates the player's distance
     */
    private void addDistance() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (health > 0) {
                    distanceText.setText(++distance);
                }
            }
        }, 0, 500);
    }

    /**
     * @return player's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Updates the player's health.
     * @param amount how much health to add
     * @return updated health value
     */
    public int addHealth(int amount) {
        this.health += amount;
        this.healthText.setText(this.health);

        return this.health;
    }

    /**
     *  Add the coin bonus for hitting a coin power up
     * @param coins
     */
    public void addCoins(int coins){
        this.coinText.addCoins(coins);
    }
}
