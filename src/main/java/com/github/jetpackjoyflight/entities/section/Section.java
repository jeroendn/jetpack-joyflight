package com.github.jetpackjoyflight.entities.section;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.jetpackjoyflight.entities.Player;
import com.github.jetpackjoyflight.entities.coin.Coin;
import com.github.jetpackjoyflight.entities.laserBeam.LaserBeam;
import com.github.jetpackjoyflight.entities.laserWall.LaserWall;
import com.github.jetpackjoyflight.entities.powerUp.PowerUp;

import java.util.Random;

final public class Section {

    private final int minObjects = 5;
    private final int maxObjects = 15;
    private final String[] availableObjects = {"coin", "powerup", "laserwall"};
    private final String[] availableTypes = {"mixed", "walls", "laserbeams"};
    public final int duration = 8000;
    public final int objectCount = new Random().nextInt(maxObjects + 1 - minObjects) + minObjects;

    public String type;
    public Object[] objects = new Object[this.objectCount];
    public int number;

    private final Player player;
    private final int maxSpawnY;
    private final int spawnX;

    /**
     * Generates a random section.
     *
     * @param number Number of the section in the order of appearance
     */
    public Section(int number, Player player, int spawnX, int maxSpawnY) {
        this.number = number;
        this.player = player;
        this.maxSpawnY = maxSpawnY;
        this.spawnX = spawnX;
        this.setRandomType();

        switch (this.type) {
            case "walls":
                this.setObjectsForTypeWalls();
                break;
            case "laserbeams":
                this.setObjectsForTypeLaserBeams();
                break;
            default:
            case "mixed":
                this.setObjectsForTypeMixed();
                break;
        }
    }

    /**
     * Sets a random type for the section.
     */
    private void setRandomType() {
        if (new Random().nextInt(3) == 0) { // Add a 1/3 chance of a mixed section
            this.type = "mixed";
            return;
        }

        int index = new Random().nextInt(this.availableTypes.length);
        this.type = this.availableTypes[index];
    }

    private void setObjectsForTypeMixed() {
        for (int i = 0; i < this.objectCount; i++) {
            if (new Random().nextInt(3) == 0) { // Adds an 1/3 chance of object being a wall
                this.objects[i] = new LaserWall(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
            } else {
                this.objects[i] = this.getRandomObject();
            }
        }
    }

    private void setObjectsForTypeWalls() {
        for (int i = 0; i < this.objectCount; i++) {
            if (new Random().nextInt(5) == 0) { // Adds an 1/5 chance of object being a coin
                this.objects[i] = new Coin(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
            } else {
                this.objects[i] = new LaserWall(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
            }
        }
    }

    private void setObjectsForTypeLaserBeams() {
        for (int i = 0; i < this.objectCount; i++) {
            this.objects[i] = new LaserBeam(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
        }
    }

    /**
     * Returns a random object from the available objects.
     *
     * @return Random object
     */
    private Object getRandomObject() {
        int index = new Random().nextInt(this.availableObjects.length);

        switch (this.availableObjects[index]) {
            case "coin":
                return new Coin(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
            case "powerup":
                return new PowerUp(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
            default:
            case "laserwall":
                return new LaserWall(new Coordinate2D(this.spawnX, this.getRandomHeight()), this.player);
        }
    }

    private int getRandomHeight() {
        return new Random().nextInt((int) this.maxSpawnY) - 100;
    }
}
