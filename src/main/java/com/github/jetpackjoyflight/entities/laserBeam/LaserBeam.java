package com.github.jetpackjoyflight.entities.laserBeam;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

public class LaserBeam extends Object {

    protected int position;
    protected final int triggerTime = 0;
    protected int time = 0;

    /**
     * Create a new LaserBeam object.
     * @param initialLocation
     * @param player
     */
    public LaserBeam(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = true;
    }

    @Override
    protected void setupEntities() {
        var sprite = new LaserBeamSprite(new Coordinate2D(0, 0));
        addEntity(sprite);

        var hitBox = new LaserBeamHitBox(new Coordinate2D(0, 0), this);
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
    }
}
