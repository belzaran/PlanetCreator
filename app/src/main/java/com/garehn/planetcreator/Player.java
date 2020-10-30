package com.garehn.planetcreator;

public class Player {

    /* VARIABLES ---------------------------------------------------------------------------------*/

    private int points;
    private int money;
    private String name;

    /* CONSTRUCTOR -------------------------------------------------------------------------------*/

    public Player(){
        this.points = 0;
        this.money = 0;
        this.name = "Player";
    }

    /* GETTER AND SETTERS-------------------------------------------------------------------------*/

    public String toString(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /* METHODS-------------------------------------------------------------------------*/

}
