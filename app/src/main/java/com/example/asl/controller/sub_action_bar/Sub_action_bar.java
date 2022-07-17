package com.example.asl.controller.sub_action_bar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asl.R;

public class Sub_action_bar extends AppCompatActivity {
    //text to display points and title text
    private TextView title_text;

    //image button to allow user to go to previous screen
    private ImageView back_button;

    /**
     * get the screen elements to be processed
     */
    public void get_screen_elements(){
        title_text = findViewById(R.id.subaction_bar_title);
        back_button = findViewById(R.id.subaction_bar_back_button);

        //set what happends when back button is clicked
        set_back_button_onclick();
    }

    /**
     * equivalent to clicking back to go to previous screen
     */
    private void back_button_pressed(){
        super.onBackPressed();
    }

    /**
     * set when back button is pressed it goes to previous screen
     */
    public void set_back_button_onclick(){
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls the back function to go back to previous screen
                back_button_pressed();
            }
        });
    }

    /**
     * Sets the titie text to a string to be displayed as a title
     * @param title - string to be displayed as a title on the screen
     */
    public void set_title_text(String title){
        title_text.setText(title);
    }

    /**
     * Sets thje title text to a string based on the id in R.id
     * @param title_id - id that corresponds to a text in R
     */
    public void set_title_text(int title_id){
        title_text.setText(title_id);
    }



    /**
     * Equivalent to back button pressed
     */
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}