package com.github.jetpackjoyflight.entities.laserWall;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

public class LaserWall extends Object {

    protected int length;
    protected String direction;

    public LaserWall(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = true;
    }

    @Override
    protected void setupEntities() {
        var sprite = new LaserWallSprite(new Coordinate2D(0, 0));
        addEntity(sprite);

        var hitBox = new LaserWallHitBox(new Coordinate2D(20, 20), this);
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {

    }
}
