package com.github.jetpackjoyflight.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.jetpackjoyflight.entities.map.Coral;
import com.github.jetpackjoyflight.entities.text.BubblesPoppedText;
import com.github.jetpackjoyflight.entities.text.HealthText;
import com.github.jetpackjoyflight.Main;
import javafx.scene.input.KeyCode;

import java.util.Objects;
import java.util.Set;

public class Player extends DynamicSpriteEntity implements SceneBorderTouchingWatcher, KeyListener, Collided, Collider, Newtonian {

    private final HealthText healthText;
    private final BubblesPoppedText bubblesPoppedText;
    private final Main main;
    private int health = 10;
    private int bubblesPopped = 0;
    private Long lastHit = null;

    public Player(final Coordinate2D location, final HealthText healthText, final BubblesPoppedText bubblesPoppedText, final Main main) {
        super("sprites/player.png", location, new Size(200, 100), 1, 1);

        this.healthText = healthText;
        this.bubblesPoppedText = bubblesPoppedText;
        this.main = main;
        healthText.setText(health);
        bubblesPoppedText.setText(bubblesPopped);

        setGravityConstant(20);
        setFrictionConstant(0.9);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(KeyCode.UP)) {
            setMotion(100, Direction.UP);
        }
    }

    @Override
    public void onCollision(Collider collidingObject) {
        if (collidingObject instanceof Coral) {
            setSpeed(0);
        } else if (collidingObject instanceof AirBubble) {
            bubblesPoppedText.setText(++bubblesPopped);
        } else {
            if (Objects.isNull(lastHit) || (lastHit + 2000) < System.currentTimeMillis()) {
                healthText.setText(--health);

                if (health == 0) {
                    this.main.setActiveScene(2);
                }
                lastHit = System.currentTimeMillis();
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
}
