package com.example.asl.ui.Lesson_screen.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.asl.R;
import com.example.asl.controller.app_data.Category_elements;
import com.example.asl.controller.sub_action_bar.Sub_action_bar;
import com.example.asl.ui.Lesson_screen_description.Adverbs.Almost_screen_components;
import com.example.asl.ui.Lesson_screen_description.Adverbs.Later_screen_components;
import com.example.asl.ui.Lesson_screen_description.Adverbs.No_screen_components;
import com.example.asl.ui.Lesson_screen_description.Adverbs.Yes_screen_components;
import com.example.asl.ui.Lesson_screen_description.Alphabets.A_screen_components;
import com.example.asl.ui.Lesson_screen_description.Alphabets.B_screen_components;
import com.example.asl.ui.Lesson_screen_description.Alphabets.C_screen_components;
import com.example.asl.ui.Lesson_screen_description.Alphabets.Y_screen_components;
import com.example.asl.ui.Lesson_screen_description.Lesson_screen_description;

import java.util.ArrayList;

public class Alphabets extends Sub_action_bar implements Lesson_topics{
    //constant for button
    private View button;

    private ArrayList<String> category_elements;

    //Constant for the all of the Lesson's category elements
    private Category_elements all_category_elements;

    //component for A sign
    private A_screen_components a_screen_components= new A_screen_components();
    //component for B sign
    private B_screen_components b_screen_components= new B_screen_components();
    //component for C sign
    private C_screen_components c_screen_components= new C_screen_components();
    //component for Y sign
    private Y_screen_components y_screen_components= new Y_screen_components();

    /**
     * Initialize the signs for Adverbs
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alphabets_menu);

        set_buttons_on_click();
        get_screen_elements();


        set_back_button_onclick();
        set_title_text(this.toString()+" Syllabus");

        init_category_elements();

    }

    /**
     * initialize the sign's elements
     */
    private void init_category_elements(){
        all_category_elements = new Category_elements();
        category_elements = all_category_elements.getCategory_elements().get(toString());
    }

    /**
     * manipulate the button what to do
     */
    private void set_buttons_on_click(){

        //A button
        button =  findViewById(R.id.a_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, a_screen_components)
                        .putExtra(translator_label,category_elements.get(0))
                        .putExtra(translator_lesson_topics,get_model_category())

                );
            }
        });
        //B button
        button =  findViewById(R.id.b_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, b_screen_components)
                        .putExtra(translator_label,category_elements.get(1))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //C button
        button =  findViewById(R.id.c_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, c_screen_components)
                        .putExtra(translator_label,category_elements.get(2))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //Y button
        button =  findViewById(R.id.y_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, y_screen_components)
                        .putExtra(translator_label,category_elements.get(3))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });

    }

    /**
     * get the string of the model category
     * @return model_category - string to represent model to be loaded in to tflite
     */
    public String get_model_category(){
        return toString().toLowerCase();
    }

    /**
     *  get the string of the sign from data file
     * @return category - string that represents the current category
     */
    @Override
    public String toString(){

        return "Alphabets";
    }
}