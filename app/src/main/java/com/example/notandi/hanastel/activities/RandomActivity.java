package com.example.notandi.hanastel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.notandi.hanastel.R;
import com.example.notandi.hanastel.product.CocktailRecipe;

import java.util.Random;

/**
 * Created by Altaris on 23-Oct-15.
 */

public class RandomActivity extends MainActivity {

    //Sensor accelerometer;
    //SensorManager sm;
    ImageView imageView;
    Animation animRotate;

    //private String[] cocktails = {"The Juicy Lucy", "Sex on the beach", "Martini", "Volcano", "Sleipur Garpur"};

    //private static final int SHAKE_THRESHOLD = 300;
    //private long lastTime;
    //private float x, y, z;
    //private float last_x, last_y, last_z;
    //private boolean isShaking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        //sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        imageView = (ImageView)findViewById(R.id.animatedImage);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        imageView.startAnimation(animRotate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView.startAnimation(animRotate);
    }

    public void onRandomButtonClick(View view){
        Random r = new Random();
        final int i = r.nextInt(recipes.size()-1);
        CocktailRecipe cr = recipes.get(i);

        Intent intent = new Intent(getApplicationContext(), DrinkDetailActivity.class);
        intent.putExtra("clickedCocktail", cr);
        intent.putExtra("isRandom", true);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.expand);


    }

    /*public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_left);
    }*/

    /*public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        // only allow one update every 450ms.
        if (i == 0 || (curTime - lastTime) > 450) {
            long diffTime = (curTime - lastTime);
            lastTime = curTime;
            Random r = new Random();
            CocktailRecipe cr = new CocktailRecipe();
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
            if (speed > SHAKE_THRESHOLD) {
                //Log.d("sensor", "shake detected w/ speed: " + speed);
                //Toast.makeText(this, "shake detected w/ speed: " + speed + " and diffTime: " + diffTime, Toast.LENGTH_SHORT).show();
                // TODO: call function where we get random drink
                //Log.d("tag ", String.valueOf(i));
                final int i = r.nextInt(5);
                        Log.d("\ncocktail: ", cocktails[i] + "\n");
                        imageView.startAnimation(animRotate);
                        Toast.makeText(getApplicationContext(), cocktails[i], Toast.LENGTH_SHORT).show();
                        cr.setName(cocktails[i]);
                        cr.setImgName("cocktail");
                        cr.setDescription("blanda bara öllu saman");
                        Intent intent = new Intent(getApplicationContext(), DrinkDetailActivity.class);
                        intent.putExtra("clickedCocktail", cr);
                        intent.putExtra("isRandom", true);
                        startActivity(intent);
            }
            last_x = x;
            last_y = y;
            last_z = z;
            i++;
        }
    }*/
}
