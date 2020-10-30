package com.garehn.planetcreator;

public enum Event {

    METEOR ("meteor", 10, 10),
    DEGASSING ("meteor", 10, 10),
    ERUPTION ("eruption", 10, 10);

    /* VARIABLES ---------------------------------------------------------------------------------*/

    public String name;
    public int cost;
    public int duration;

    /* CONSTRUCTOR -------------------------------------------------------------------------------*/

    Event(String n, int c, int d){
        name = n;
        cost = c;
        duration = d;
    }

    /* GETTERS & SETTERS--------------------------------------------------------------------------*/

    public String toString(){
        return name;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
