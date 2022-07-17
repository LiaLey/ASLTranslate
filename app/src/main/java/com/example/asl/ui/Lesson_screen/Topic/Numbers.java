package com.example.asl.ui.Lesson_screen.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import com.example.asl.R;
import com.example.asl.controller.app_data.Category_elements;
import com.example.asl.controller.sub_action_bar.Sub_action_bar;
import com.example.asl.ui.Lesson_screen_description.Lesson_screen_description;
import com.example.asl.ui.Lesson_screen_description.Numbers.Num10_screen_components;
import com.example.asl.ui.Lesson_screen_description.Numbers.Num1_screen_components;
import com.example.asl.ui.Lesson_screen_description.Numbers.Num2_screen_components;
import com.example.asl.ui.Lesson_screen_description.Numbers.Num3_screen_components;
import com.example.asl.ui.Lesson_screen_description.Numbers.Num4_screen_components;

import java.util.ArrayList;

public class Numbers extends Sub_action_bar implements Lesson_topics{
    //constant for button
    private View button;

    private ArrayList<String> category_elements;

    //Constant for the all of the Lesson's category elements
    private Category_elements all_category_elements;

    //component for 1 sign
    private Num1_screen_components num1_screen_components = new Num1_screen_components();
    //component for 2 sign
    private Num2_screen_components num2_screen_components = new Num2_screen_components();
    //component for 10 sign
    private Num10_screen_components num10_screen_components = new Num10_screen_components();
    //component for 3 sign
    private Num3_screen_components num3_screen_components = new Num3_screen_components();
    //component for 4 sign
    private Num4_screen_components num4_screen_components = new Num4_screen_components();

    /**
     * Initialize the signs for Adverbs
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers_menu);

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

        //One button
        button =  findViewById(R.id.one_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, num1_screen_components)
                        .putExtra(translator_label,category_elements.get(0))
                        .putExtra(translator_lesson_topics,get_model_category())

                );
            }
        });
        //Two button
        button =  findViewById(R.id.two_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, num2_screen_components)
                        .putExtra(translator_label,category_elements.get(1))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //Three button
        button =  findViewById(R.id.three_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, num3_screen_components)
                        .putExtra(translator_label,category_elements.get(2))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //Four button
        button =  findViewById(R.id.four_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, num4_screen_components)
                        .putExtra(translator_label,category_elements.get(3))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //Ten button
        button =  findViewById(R.id.ten_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, num10_screen_components)
                        .putExtra(translator_label,category_elements.get(4))
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

        return "Numbers";
    }
}
