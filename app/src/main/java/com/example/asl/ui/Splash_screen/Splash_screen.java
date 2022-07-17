package com.example.asl.ui.Splash_screen;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.asl.R;
import com.example.asl.controller.new_screen.New_screen;

public class Splash_screen extends AppCompatActivity {

    private Animation splash_top_animation, splash_bottom_animation;

    private ImageView splash_screen_logo;

    private TextView splash_screen_subtitle;

    private Button splash_screen_button;

    private final New_screen translator_screen = new New_screen(0, this, this);

    /**
     * Called when android creates an activity with Splash_screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // sets the current view to be splash_screen xml resource file
        setContentView(R.layout.splash_screen);

        // set the window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadAnimations();

        getScreenElements();

        setSplashScreenAnimations();

        setGoOnClick();


    }

    /**
     * Load the animation for diff components on the splash screen from resource folder
     */
    private void loadAnimations(){
        // loads the animation for elements to appear from top of screen
        splash_top_animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_top_animation);

        // loads the animation for elements to appear from bottom of screen
        splash_bottom_animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_bottom_animation);
    }

    /**
     * function gets the elements from the splash screen based on the ID of the elements
     */
    private void getScreenElements(){
        // get the logo of the splash screen
        splash_screen_logo = findViewById(R.id.splash_screen_logo);

        // get the title of the splash screen
        splash_screen_subtitle = findViewById(R.id.splash_screen_subtitle);

        // get the "Go" button on the splash screen
        splash_screen_button = findViewById(R.id.splash_screen_button);
    }

    /**
     * Set the animation for elements on the splash screen
     */
    private void setSplashScreenAnimations(){
        // set the animation for the logo on splash screen
        splash_screen_logo.setAnimation(splash_top_animation);

        // set the animation for the title on the splash screen
        splash_screen_subtitle.setAnimation(splash_top_animation);

        // set the go button's animation
        splash_screen_button.setAnimation(splash_bottom_animation);

    }

    /**
     * Set the onclick of "Go" button
     * Allows user to go to the translator screen
     */
    private void setGoOnClick(){
        splash_screen_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                // calls method to go to translator screen
                translator_screen.go_to_new_screen("com.example.asl.ui.Home_screen.Home_screen", true);
            }
        });

    }



}
