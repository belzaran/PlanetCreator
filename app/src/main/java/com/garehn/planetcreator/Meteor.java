package com.garehn.planetcreator;

import android.util.Log;

public class Meteor extends Luminary {

    final int RADIUS_MAX = 100;
    final int RADIUS_MIN = 1;
    final int MASS_MAX= 5000000 ; //*E6
    final int MASS_MIN= 50; //*E6
    final int DENSITY_MAX = 15; //*E-1
    final int DENSITY_MIN = 5; //*E-1

    private String METEOR_INFO = "Radius = %s km"
            + "\nMass = %s kg"
            + "\nDensity = %s";

    public Meteor() {
        radius = createRandomInt(RADIUS_MIN, RADIUS_MAX);
        float f = (float) createRandomInt(DENSITY_MIN, DENSITY_MAX);
        density = f/10;
        mass = calculateMass();
        Log.i("PC_METEOR", String.format(METEOR_INFO, radius, mass, density));
    }

    public int calculateTemperatureImpact(){
        // Reference for the dinos : -15°C for a 10 km asteroid
        // if no atmosphere : temperature increases
        int t = (int)(-(1.5 * radius));
        Log.i("PC_METEOR", "T += " + t);
        return t;
    }
}
