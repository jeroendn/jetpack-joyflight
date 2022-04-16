package com.github.jetpackjoyflight.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;

public abstract class Object extends DynamicCompositeEntity implements SceneBorderCrossingWatcher {

    protected boolean isHostile;
    protected final Player player;

    public Object(final Coordinate2D initialLocation, Player player) {
        super(initialLocation);

        this.player = player;
        setMotion(9, Direction.LEFT);
    }
}
