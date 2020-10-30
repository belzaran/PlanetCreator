package com.garehn.planetcreator;

import android.util.Log;

import java.util.Random;

public class Planet extends Luminary{

    /* VARIABLES ---------------------------------------------------------------------------------*/

    private long distance;
    private float gravity;
    private int temperature; // K
    private Atmosphere atmosphere;
    private float albedo = 0.3f; // between 0 and 1 / https://fr.wikipedia.org/wiki/Alb%C3%A9do
    // boundaries for planet values
    private int RADIUS_MAX = 10000; // Earth = 6000 km
    private int RADIUS_MIN = 2000; // Mercury = 2400 km
    // for distance, * 100 000
    private int DISTANCE_MAX = 500; // Mars = 250 000 000km / Jupiter = 800 000 000 km
    private int DISTANCE_MIN = 50; // Mercury = 50 000 000 km
    // for mass, * 10E23
    private int MASS_MAX = 100; // EARTH : 6.10E24;
    private int MASS_MIN = 1; // MOON : 7.10E22

    private String PLANET_INFO = "Radius = %s km"
            + "\nDistance = %s km"
            + "\nMass = %s kg"
            + "\nDensity = %s"
            + "\nGravity = %s"
            + "\nTemperature = %s K";

    /* CONSTRUCTORS ------------------------------------------------------------------------------*/
    public Planet() {
        createPlanet();
        createAtmosphere();
        this.temperature = calculateTemperature();
    }


    /* GETTERS & SETTERS--------------------------------------------------------------------------*/

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int temperature) {
        if(temperature > 0) {
            this.temperature = temperature;
        }
    }

    public void addTemperature(int t) {
        Log.i("PC_PLANET", "OLD T° = " + this.temperature);
        this.temperature += t;
        Log.i("PC_PLANET", "NEW T° = " + this.temperature);
        // temperature in K is always positive
        if(this.temperature < 0){
            this.temperature = 0;
        }
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public float getAlbedo() {
        return albedo;
    }

    public void setAlbedo(float albedo) {
        this.albedo = albedo;
    }

    public void addMass(double m){ this.mass += m;}


    /* METHODS -----------------------------------------------------------------------------------*/

    public void createPlanet() {
        this.radius = createRandomInt(RADIUS_MIN, RADIUS_MAX);
        this.distance = createRandomInt(DISTANCE_MIN, DISTANCE_MAX) * 1000000;
        //this.mass = createRandomInt(MASS_MIN, MASS_MAX);
        double masse = (double) createRandomInt(MASS_MIN, MASS_MAX);
        //this.mass = masse*Math.pow(10.0,23.0);
        double pow = Math.pow(10.0, 23.0);
        this.mass = masse * pow;
        this.density = calculateDensity();
        this.gravity = (float) calculateGravity();
        this.temperature = calculateInitialTemperature();
        Log.i("PC_PLANET", String.format(PLANET_INFO, radius, distance, mass, density, gravity, temperature));
    }

    public void createAtmosphere() {
        atmosphere = new Atmosphere(temperature);
        atmosphere.calculatePressure();
    }


    public double calculateGravity() {
        double g = 0f;
        g = G * (mass / Math.pow(radius * 1000, 2));
        return g;
    }

    public boolean checkSynchronicity() {
        boolean check = false;
        if (distance < 80000000) {

        }
        return check;
    }

    public int calculateInitialTemperature() {
        double dist = convertDistanceToUA(distance);
        // calculate on Kelvin
        temperature = (int) (280 * Math.pow((1 - albedo) / Math.pow(dist, 2), 0.25)); //280×((1-a)/d²)1/4
        return temperature;
    }

    public int calculateTemperature() {
        double dist = convertDistanceToUA(distance);
        // calculate on Kelvin
        temperature = (int) (280 * Math.pow((1 - albedo) / Math.pow(dist, 2), 0.25)); //280×((1-a)/d²)1/4
        Log.i("PLANET_PLANET", "Temperature 1 = " + temperature);
        if(atmosphere.exists()){
            temperature += (int) (atmosphere.getPressure()*40);
        }
        Log.i("PLANET_PLANET", "Temperature 2 = " + temperature);
        return temperature;
    }

    public double convertDistanceToUA(long d) {
        double dist = 0f;
        dist = (float) d / 150000000;
        return dist;
    }

    public int convertTemperatureToCelsius(double T) {
        int t = (int) (T - 273.15);
        return t;
    }

    public boolean checkLiquidWater() {
        boolean check = false;
        if (temperature > 273 && temperature < 373) {
            check = true;
        }
        return check;
    }

    public boolean checkSolidWater(){
        boolean check = false;
        if (temperature < 273) {
            check = true;
        }
        return check;
    }

    // Calculate temperature extreme. Depends of atmosphere and average temperature.
    public int calculateTemperatureDifference(){
        int t = 0;
        return t;
    }

}

