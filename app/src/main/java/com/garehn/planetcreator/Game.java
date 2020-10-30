package com.garehn.planetcreator;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;

public class Game {

    /* VARIABLES ---------------------------------------------------------------------------------*/

    //private double time = 0;
    private Planet planet;
    private Element[] element = new Element[27];
    private ArrayList<Event> events = new ArrayList();
    private int money = 0;
    private long time = 0;
    private String lastAction = "";

    /* CONSTRUCTOR -------------------------------------------------------------------------------*/

    public Game(){
        planet = new Planet();
    }

    public void createElements(){
       /* element[1] = new Element("Hydrogen", "H", 1, 1.01f, 76);
        element[2] = new Element("Helium", "He", 2, 4.00f, 125);
        element[3] = new Element("Lithium", "Li", 3, 6.94f, 534);
        element[4] = new Element("Beryllium", "Be", 4, 9.01f, 1848);
        element[5] = new Element("Boron", "B", 5, 10.81f, 2340);
        element[6] = new Element("Carbon", "C", 6, 12.01f, 3513);
        element[7] = new Element("Nitrogen", "N", 7, 14.01f, 1026);
        element[8] = new Element("Oxygen", "O", 8, 16.00f, 2000);
        element[9] = new Element("Fluorine", "F", 9, 19.00f, 1516);
        element[10] = new Element("Neon", "Ne", 10, 20.18f, 1444);
        element[11] = new Element("Sodium", "Na", 11, 22.99f, 971);
        element[12] = new Element("Magnesium", "Mg", 12, 42.30f, 1738);
        element[13] = new Element("Aluminium", "Al", 13, 26.98f, 2698);
        element[14] = new Element("Silicon", "Si", 14, 28.09f, 2329);
        element[15] = new Element("Phosphor", "P", 15, 30.97f, 1820);
        element[16] = new Element("Sulfur", "S", 16, 32.07f, 2070);
        element[17] = new Element("Chlorine", "Cl", 17, 35.45f, 2030);
        element[18] = new Element("Argon", "Ar", 18, 29.95f, 1656);
        element[19] = new Element("Potassium", "K", 19, 39.10f, 862);
        element[20] = new Element("Calcium", "Ca", 20, 40.08f, 1550);
        element[21] = new Element("Scandium", "Sc", 21, 44.96f, 2989);
        element[22] = new Element("Titanium", "Ti", 22, 47.88f, 4540);
        element[23] = new Element("Vanadium", "V", 23, 50.94f, 6110);
        element[24] = new Element("Chromium", "Cr", 24, 52.00f, 7190);
        element[25] = new Element("Manganese", "Mn", 25, 54.94f, 7440);
        element[26] = new Element("Iron", "Fe", 26, 55.85f, 7874);*/

        element[1] = Element.H;
        element[2] = Element.HE;
        element[3] = Element.LI;
        /*element[4] = new Element("Beryllium", "Be", 4, 9.01f, 1848);
        element[5] = new Element("Boron", "B", 5, 10.81f, 2340);
        element[6] = new Element("Carbon", "C", 6, 12.01f, 3513);
        element[7] = new Element("Nitrogen", "N", 7, 14.01f, 1026);
        element[8] = new Element("Oxygen", "O", 8, 16.00f, 2000);
        element[9] = new Element("Fluorine", "F", 9, 19.00f, 1516);
        element[10] = new Element("Neon", "Ne", 10, 20.18f, 1444);
        element[11] = new Element("Sodium", "Na", 11, 22.99f, 971);
        element[12] = new Element("Magnesium", "Mg", 12, 42.30f, 1738);
        element[13] = new Element("Aluminium", "Al", 13, 26.98f, 2698);
        element[14] = new Element("Silicon", "Si", 14, 28.09f, 2329);
        element[15] = new Element("Phosphor", "P", 15, 30.97f, 1820);
        element[16] = new Element("Sulfur", "S", 16, 32.07f, 2070);
        element[17] = new Element("Chlorine", "Cl", 17, 35.45f, 2030);
        element[18] = new Element("Argon", "Ar", 18, 29.95f, 1656);
        element[19] = new Element("Potassium", "K", 19, 39.10f, 862);
        element[20] = new Element("Calcium", "Ca", 20, 40.08f, 1550);
        element[21] = new Element("Scandium", "Sc", 21, 44.96f, 2989);
        element[22] = new Element("Titanium", "Ti", 22, 47.88f, 4540);
        element[23] = new Element("Vanadium", "V", 23, 50.94f, 6110);
        element[24] = new Element("Chromium", "Cr", 24, 52.00f, 7190);
        element[25] = new Element("Manganese", "Mn", 25, 54.94f, 7440);
        element[26] = new Element("Iron", "Fe", 26, 55.85f, 7874);*/

    }


    /* GETTERS & SETTERS--------------------------------------------------------------------------*/

    public Planet getPlanet() {
        return planet;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long t) {time = t;}

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public int getMoney(){
        return this.money;
    }

    public String getLastAction(){
        return lastAction;
    }

    /* GAMEPLAY ----------------------------------------------------------------------------------*/

    public boolean sendMeteor(Meteor m, Planet p){
        boolean b = false;
        if(money >= Event.METEOR.getCost()) {
            b = true;
            money -= Event.METEOR.getCost();
            p.addMass(m.getMass());
            p.addTemperature(m.calculateTemperatureImpact());
            lastAction = "Mass becomes " + planet.getMass() + "\nTemperature becomes " + planet.getTemperature();
            Log.i("PC_GAME", "Mass becomes " + planet.getMass() + "\nTemperature becomes " + planet.getTemperature());
        }
        return b;
    }

    public boolean sendEruption(Planet p){
        boolean b = false;
        if(money >= Event.ERUPTION.getCost()) {
            b = true;
            money -= Event.ERUPTION.getCost();
            p.addTemperature(5);
            lastAction = "Temperature becomes " + planet.getTemperature();
            Log.i("PC_GAME", "Temperature becomes " + planet.getTemperature());
        }
        return b;
    }

    public void nextTurn() {
        money ++;
    }

}
