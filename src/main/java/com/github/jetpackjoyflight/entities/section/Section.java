package com.github.jetpackjoyflight.entities.section;

import java.util.Random;

final public class Section {

    private final int minObjects = 5;
    private final int maxObjects = 15;
    private final String[] availableObjects = {"coin", "powerup", "laserwall"};
    private final String[] availableTypes = {"mixed", "walls", "laserbeams"};
    public final int duration = 8000;
    public final int objectCount = new Random().nextInt(maxObjects + 1 - minObjects) + minObjects;

    public String type;
    public String[] objects = new String[this.objectCount];
    public int number;

    /**
     * Generates a random section.
     *
     * @param number Number of the section in the order of appearance
     */
    public Section(int number) {
        this.number = number;
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
            String objectName = (new Random().nextInt(3) == 0 ? "wall" : this.getRandomObject()); // Adds an 1/3 chance of object being a wall
            this.objects[i] = objectName;
        }
    }

    private void setObjectsForTypeWalls() {
        for (int i = 0; i < this.objectCount; i++) {
            String objectName = (new Random().nextInt(5) == 0 ? "coin" : "wall"); // Adds an 1/5 chance of object being a coin
            this.objects[i] = objectName;
        }
    }

    private void setObjectsForTypeLaserBeams() {
        for (int i = 0; i < this.objectCount; i++) {
            this.objects[i] = "laserbeam";
        }
    }

    /**
     * Returns a random object from the available objects.
     *
     * @return Random object
     */
    private String getRandomObject() {
        int index = new Random().nextInt(this.availableObjects.length);

        return this.availableObjects[index];
    }
}
