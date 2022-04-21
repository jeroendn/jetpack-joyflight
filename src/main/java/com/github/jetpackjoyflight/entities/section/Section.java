package com.github.jetpackjoyflight.entities.section;

import java.util.Random;

final public class Section {

    private final int minObjects = 2;
    private final int maxObjects = 5;
    private final String[] availableObjects = {"coin", "powerup", "laserwall"};
    public final int duration = 10000;

    private String type; // TODO Type of section. Options: regular or laserbeam(s)
    public String[] objects;
    public int number; // Number of the section in the order of appearance

    public Section(int number) {
        this.number = number;
        int objectCount = new Random().nextInt(maxObjects + 1 - minObjects) + minObjects;
        this.objects = new String[objectCount];

        for (int i = 0; i < objectCount; i++) {
            this.objects[i] = getRandomObject();
        }

        System.out.println(this.objects);
    }

    private String getRandomObject() {
        int index = new Random().nextInt(this.availableObjects.length);

        return this.availableObjects[index];
    }

    public void draw() {

    }
}
