package edu.polytech.td4ex3;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This application has two activities:
 *
 *     MainActivity, which contains a frame-by-frame animation.
 *     ControlActivity, which contains two fragments:
 *         Screen1Fragment (dynamic fragment)
 *         MenuFragment (static fragment)
 *
 *     for using with JACKSON and PICASSO
 *      implementation 'com.squareup.retrofit2:converter-jackson:2.7.2'
 *     implementation 'com.fasterxml.jackson.core:jackson-databind:2.10.3'
 *     implementation 'com.fasterxml.jackson.core:jackson-core:2.10.3'
 *     implementation 'com.fasterxml.jackson.core:jackson-annotations:2.10.3'
 *     implementation 'com.squareup.picasso:picasso:2.71828'
 *
 * @author F. Rallo - march 2025
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "frallo "+getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(getApplicationContext(), ControlActivity.class);

        ImageView image = findViewById(R.id.imageView);
        image.setBackgroundResource(R.drawable.rotation_animation);
        AnimationDrawable animation = (AnimationDrawable)image.getBackground();
        animation.start();
        Log.d(TAG,"MainActivity is running");

        //default button
        findViewById(R.id.goDefault).setOnClickListener(clic -> {
            startActivity(intent);
        });

        //optional button --> goto menu6
        findViewById(R.id.option1).setOnClickListener(clic -> {
            int menuNumber = 2;  //this is number, not index
            intent.putExtra(getString(R.string.index),menuNumber);
            Log.d(TAG,"send menu#"+menuNumber);
            startActivity(intent);
        });
    }
}