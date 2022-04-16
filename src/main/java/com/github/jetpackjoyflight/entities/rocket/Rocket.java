package com.github.jetpackjoyflight.entities.rocket;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.jetpackjoyflight.entities.Object;
import com.github.jetpackjoyflight.entities.Player;

public class Rocket extends Object {

    public Rocket(Coordinate2D initialLocation, Player player) {
        super(initialLocation, player);
        this.isHostile = true;
    }

    @Override
    protected void setupEntities() {
        var swordFishSprite = new Sprite(new Coordinate2D(0, 0));
        addEntity(swordFishSprite);

        var hitBox = new HitBox(new Coordinate2D(0, 0));
        addEntity(hitBox);
    }

    @Override
    public void notifyBoundaryCrossing(final SceneBorder border) {
        final Player player = this.player;

        new Thread(new Runnable() {
            private final Player p = player;

            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setAnchorLocation(new Coordinate2D(getSceneWidth(), this.p.getAnchorLocation().getY()));
            }
        }).start();
    }
}
