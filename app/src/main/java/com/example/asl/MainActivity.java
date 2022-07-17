package com.example.asl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.asl.controller.new_screen.New_screen;

public class MainActivity extends AppCompatActivity {
    //check if there is a custom toast being shown on the screen.
    // Default there is no toast is shown
    public static boolean toast_shown = false;

    // creates new splash screen goto instance to allow user to go to splash screen
    private final New_screen splash_screen = new New_screen(0, this, this);

    /**
     * calls when the main activity is reached by android
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // go to splash screen when reaches main activity
        splash_screen.go_to_new_screen("com.example.asl.ui.Splash_screen.Splash_screen", true);


    }
}