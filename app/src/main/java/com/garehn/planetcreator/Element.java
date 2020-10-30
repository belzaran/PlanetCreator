package com.garehn.planetcreator;

public enum Element {

    H ("Hydrogen", "H", 1, 1.01f, 76),
    HE ("Helium", "He", 2, 4.00f, 125),
    LI ("Lithium", "Li", 3, 6.94f, 534),
    BE ("Beryllium", "Be", 4, 9.01f, 1848),
    B ("Boron", "B", 5, 10.81f, 2340),
    C ("Carbon", "C", 6, 12.01f, 3513),
    N ("Nitrogen", "N", 7, 14.01f, 1026),
    O ("Oxygen", "O", 8, 16.00f, 2000),
    F ("Fluorine", "F", 9, 19.00f, 1516),
    NE ("Neon", "Ne", 10, 20.18f, 1444),
    NA ("Sodium", "Na", 11, 22.99f, 971),
    MG ("Magnesium", "Mg", 12, 42.30f, 1738),
    AL ("Aluminium", "Al", 13, 26.98f, 2698),
    SI ("Silicon", "Si", 14, 28.09f, 2329),
    P ("Phosphor", "P", 15, 30.97f, 1820),
    S ("Sulfur", "S", 16, 32.07f, 2070),
    CL ("Chlorine", "Cl", 17, 35.45f, 2030),
    AR ("Argon", "Ar", 18, 29.95f, 1656),
    K ("Potassium", "K", 19, 39.10f, 862),
    CA ("Calcium", "Ca", 20, 40.08f, 1550),
    SC ("Scandium", "Sc", 21, 44.96f, 2989),
    TI ("Titanium", "Ti", 22, 47.88f, 4540),
    V ("Vanadium", "V", 23, 50.94f, 6110),
    CR ("Chromium", "Cr", 24, 52.00f, 7190),
    MN ("Manganese", "Mn", 25, 54.94f, 7440),
    FE ("Iron", "Fe", 26, 55.85f, 7874);

    private int Z;
    private String name;
    private String symbol;
    private float mass;
    private float density;

        Element(String n, String s, int z, float m, int d){
        this.name = n;
        this.symbol = s;
        this.Z = z;
        this.mass = m;
        this.density = d;
    }

    public String toString(){
        return name;
    }
    public String getSymbole(){return symbol;}
    public int getZ(){return Z;}
    public float getMass(){return mass;}

}

