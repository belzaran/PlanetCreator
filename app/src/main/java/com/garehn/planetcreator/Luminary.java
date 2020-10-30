package com.garehn.planetcreator;

import java.util.Random;

abstract class Luminary {

    protected String name;
    protected int radius;
    protected double mass;
    protected float density;

    final double G = 6.67 * Math.pow(10, -11);

    public float calculateDensity() {
        int mv = 0; // volumic mass
        float d = 0f; // density
        double v = calculateVolume();
        mv = (int) (mass / v);
        d = (float) mv / 1000;
        return d;
    }

    public double calculateVolume() {
        double v = 0;
        v = (double) (1.33 * 3.14 * Math.pow(radius * 1000, 3.0));
        return v;
    }

    public double calculateMass(){
        mass = (double) (density*calculateVolume());
        return mass;
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int createRandomInt(int min, int max) {
        int nb;
        Random random = new Random();
        nb = min + random.nextInt(max - min);
        return nb;
    }




}
