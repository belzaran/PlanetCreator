package com.garehn.planetcreator;

import android.util.Log;

public class Atmosphere {

    /* VARIABLES ---------------------------------------------------------------------------------*/

    private float pressure;
    private float density;
    private boolean exist;
    private int temperature;
    final int R = 287; //𝑅 = 287J⋅K-1⋅kg-1
    private String ATMO_INFO = "Pressure : %s bar\nDensity : %s";

    /* CONSTRUCTORS ------------------------------------------------------------------------------*/

    public Atmosphere(){
        temperature = 0;
        density = 1.3f;
        exist = true;
        pressure = calculatePressure();
        Log.i("PLANET_ATMO", String.format(ATMO_INFO, pressure, density));
    }

    public Atmosphere(int t){
        temperature = t;
        density = 1.3f;
        exist = true;
        pressure = calculatePressure();
        Log.i("PLANET_ATMO", String.format(ATMO_INFO, pressure, density));
    }

    /* GETTERS AND SETTERES ------------------------------------------------------------------------------*/

    public float getPressure() {
        pressure = calculatePressure();
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public boolean exists() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public float calculatePressure(){
        if (!exist){
            pressure = 0;
        }
        else{
            pressure = (density*R*temperature)/100000;
        }
        return pressure;
    }

}
