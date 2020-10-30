package com.garehn.planetcreator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* VARIABLES ---------------------------------------------------------------------------------*/
    TextView textName;
    TextView textDistance;
    String TEXT_DISTANCE = "%s km";
    TextView textMass;
    String TEXT_MASS = "%sE23 kg";
    TextView textRadius;
    TextView textGravity;
    String TEXT_GRAVITY = "%s N/kg";
    TextView textTemperature;
    String TEXT_TEMPERATURE = "%s K | %s °C";
    TextView textTime;
    String TEXT_TIME = "%s years";
    TextView textMoney;
    String TEXT_MONEY = "%s points";
    ImageView imagePlanet;

    private int nbButton = 3;
    Button button[] = new Button[nbButton];

    private Game game;
    private Planet planet;
    private long time;
    private long startTime;
    private Handler timeHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        planet = game.getPlanet();
        createAssets();
        startTime = System.currentTimeMillis();
        Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                time = (long) (millis / 1000);
                timeHandler.postDelayed(this, 1000);
                game.nextTurn();
                updateGraphics();
            }
        };
        timeHandler.postDelayed(timerRunnable, 0);
    }

    public void createAssets(){
        textName = findViewById(R.id.text_name);
        textDistance = findViewById(R.id.text_distance_2);
        textMass = findViewById(R.id.text_mass_2);
        textRadius = findViewById(R.id.text_radius_2);
        textGravity = findViewById(R.id.text_gravity_2);
        textTemperature = findViewById(R.id.text_temperature_2);
        textTime = findViewById(R.id.text_time_2);
        textMoney = findViewById(R.id.text_money_2);

        //buttons
        button[0] = findViewById(R.id.button_0);
        button[1] = findViewById(R.id.button_1);
        button[2] = findViewById(R.id.button_2);
        for(int i = 0; i<nbButton; i++){
            button[i].setOnClickListener(this);
        }

        imagePlanet = findViewById(R.id.image_planet);
    }

    public void updateGraphics(){
        updateDistance();
        updateGravity();
        updateRadius();
        updateMass();
        updateTemperature();
        updateTime();
        updateMoney();
    }

    public void updateMoney(){
        game.getMoney();
        textMoney.setText(String.format(TEXT_MONEY, game.getMoney()));
    }

    public void updateTime(){
        game.setTime(time);
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        String t = df.format(time*1000);
        textTime.setText(String.format(TEXT_TIME, t));
    }

    public void updateDistance(){
        long l = planet.getDistance();
        DecimalFormat df = new DecimalFormat("###,###,###,###");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        String distance = df.format(l);
        textDistance.setText(String.format(TEXT_DISTANCE, distance));
    }

    public void updateGravity(){
        float f = planet.getGravity();
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        textGravity.setText(String.format(TEXT_GRAVITY, df.format(f)));
    }

    public void updateRadius(){
        int r = planet.getRadius();
        DecimalFormat df = new DecimalFormat("#,###");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        textRadius.setText(String.format(TEXT_DISTANCE,df.format(r)));
    }

    public void updateMass(){
        double d = planet.getMass()*Math.pow(10,-23);
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        textMass.setText(String.format(TEXT_MASS,df.format(d)));
    }

    public void updateTemperature() {
        int i = planet.getTemperature();
        int j = planet.convertTemperatureToCelsius(planet.getTemperature());
        textTemperature.setText(String.format(TEXT_TEMPERATURE,i,j));
    }

    @Override
    public void onClick(View v) {
        if(v==button[0]){
            Log.i("PC_MAIN", "T° = " + planet.getTemperature());
            game.sendMeteor(new Meteor(),planet);
        }
        else if(v== button[1]){
            if(game.sendEruption(planet)){
                sendToast(game.getLastAction(), Toast.LENGTH_SHORT);
            };
        }
        //updateGraphics();
    }

    public void sendToast(String message, int time) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, time);
        int x = (int) (imagePlanet.getX());
        int y = (int) (imagePlanet.getY() + (imagePlanet.getHeight()/2));
        toast.setGravity(Gravity.TOP|Gravity.LEFT, x, y);
        toast.show();
    }

}