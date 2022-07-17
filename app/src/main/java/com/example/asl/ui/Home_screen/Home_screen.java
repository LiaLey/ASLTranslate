package com.example.asl.ui.Home_screen;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.asl.R;
import com.example.asl.ui.Lesson_screen.Lesson_screen;
import com.example.asl.ui.Translator_screen.Translator_screen;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Home_screen extends AppCompatActivity {
    // animated navigation
    private ChipNavigationBar nav_fragment;

    // fragment to show to user
    private Fragment fragment;

    /**
     * Called when android creates an activity with Home_screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // sets the current view to be splash_screen xml resource file
        setContentView(R.layout.activity_main);

        get_screen_components();

        init_navigation_bottom();

    }

    private void get_screen_components(){
        nav_fragment = findViewById(R.id.nav_view);

    }

    /**
     * Initializes navigation at the bottom of screen
     * Code adopted from Chip Navigation Bar: https://github.com/ismaeldivita/chip-navigation-bar
     */
    private void init_navigation_bottom(){
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        nav_fragment.setItemSelected(R.id.navigation_lesson,true);
        new Translator_screen(Home_screen.this,Home_screen.this);
        fragment = new Lesson_screen(Home_screen.this);


        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        // set what happends when element on navigation at bottom of menu is clicked
        nav_fragment.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.navigation_translator:

                        fragment = new Translator_screen(Home_screen.this,Home_screen.this);
                        break;
                    case R.id.navigation_lesson:
                        fragment = new Lesson_screen(Home_screen.this);
                        break;

                }

                if(fragment !=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

                }
            }
        });

    }
}
