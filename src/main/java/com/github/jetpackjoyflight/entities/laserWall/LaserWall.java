package com.github.jetpackjoyflight.entities.laserWall;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

import java.util.Random;

public class LaserWall extends Object {

    protected final String[] directionOptions = {"diagonal", "vertical", "horizontal"};
    protected String direction;
//    protected int length;

    /**
     * Create a new LaserWall object.
     * @param initialLocation
     * @param player
     */
    public LaserWall(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = true;
        this.setRandomDirection();
    }

    @Override
    protected void setupEntities() {
        String imgPath = "";

        switch (this.direction) {
            case "vertical":
                imgPath = "sprites/laserWallVertical.png";
                break;
            case "horizontal":
                imgPath = "sprites/laserWallHorizontal.png";
                break;
            default:
            case "diagonal":
                imgPath = (new Random().nextBoolean() ? "sprites/laserWallDiagonalUp.png" : "sprites/laserWallDiagonalDown.png");
                break;
        }

        var sprite = new LaserWallSprite(new Coordinate2D(0, 0), imgPath);
        addEntity(sprite);

        var hitBox = new LaserWallHitBox(new Coordinate2D(20, 20), this);
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
    }

    protected void setRandomDirection() {
        int index = new Random().nextInt(this.directionOptions.length);
        this.direction = this.directionOptions[index];
    }
}
