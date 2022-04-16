package com.github.jetpackjoyflight.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
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
    private final Main main;
    private int health = 1;
    private int distance = 0;
    private Long lastHit = null;
    private Timer timer;

    public Player(final Coordinate2D location, final HealthText healthText, final DistanceText distanceText, final Main main) {
        super("sprites/player.png", location, new Size(200, 100), 1, 1);

        this.healthText = healthText;
        this.distanceText = distanceText;
        this.main = main;
        healthText.setText(health);
        distanceText.setText(distance);

        setGravityConstant(20);
        setFrictionConstant(0.9);
        this.addDistance();
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(KeyCode.UP)) {
            setMotion(100, Direction.UP);
        }
    }

    @Override
    public void onCollision(Collider collidingObject) {
        if ((Objects.isNull(lastHit) || (lastHit + 2000) < System.currentTimeMillis()) && collidingObject instanceof HitBox) {
            if (((HitBox) collidingObject).object.isHostile) {

                healthText.setText(--health);

                if (health == 0) {
                    this.main.setActiveScene(2);
                }
                lastHit = System.currentTimeMillis();

            } else {

            }
        }
    }

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

    private void addDistance() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (health > 0) {
                    distanceText.setText(++distance);
                    System.out.println(distance + " " + health);
                }
            }
        }, 0, 1000);
    }

    public int getHealth() {
        return health;
    }
}
