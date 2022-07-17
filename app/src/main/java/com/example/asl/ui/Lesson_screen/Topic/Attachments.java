package com.example.asl.ui.Lesson_screen.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.asl.R;
import com.example.asl.controller.app_data.Category_elements;
import com.example.asl.controller.sub_action_bar.Sub_action_bar;
import com.example.asl.ui.Lesson_screen_description.Attachments.Adore_screen_components;
import com.example.asl.ui.Lesson_screen_description.Attachments.Dislike_screen_components;
import com.example.asl.ui.Lesson_screen_description.Attachments.Iloveyou_screen_components;
import com.example.asl.ui.Lesson_screen_description.Attachments.Like_screen_components;
import com.example.asl.ui.Lesson_screen_description.Lesson_screen_description;

import java.util.ArrayList;

public class Attachments extends Sub_action_bar implements Lesson_topics{
    //constant for button
    private View button;

    private ArrayList<String> category_elements;

    //Constant for the all of the Lesson's category elements
    private Category_elements all_category_elements;

    //component for dislike sign
    private Dislike_screen_components dislike_screen_components = new Dislike_screen_components();
    //component for I love you sign
    private Iloveyou_screen_components iloveyou_screen_components = new Iloveyou_screen_components();
    //component for like sign
    private Like_screen_components like_screen_components = new Like_screen_components();
    //component for adore sign
    private Adore_screen_components adore_screen_components = new Adore_screen_components();

    /**
     * Initialize the signs for Adverbs
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attachments_menu);

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

        //Like button
        button =  findViewById(R.id.like_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, like_screen_components)
                        .putExtra(translator_label,category_elements.get(0))
                        .putExtra(translator_lesson_topics,get_model_category())

                );
            }
        });
        //dislike button
        button =  findViewById(R.id.dislike_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, dislike_screen_components)
                        .putExtra(translator_label,category_elements.get(1))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //Iloveyou button
        button =  findViewById(R.id.iloveyou_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, iloveyou_screen_components)
                        .putExtra(translator_label,category_elements.get(2))
                        .putExtra(translator_lesson_topics,get_model_category())
                );
            }
        });
        //Adore button
        button =  findViewById(R.id.adore_button);
        button.setOnClickListener(new View.OnClickListener() {
            //upon clicking the button, go to openActivity
            @Override
            public void onClick(View v) {
                openActivity();
            }
            //display elements of description for the sign
            public void openActivity() {
                startActivity(new Intent(getApplicationContext(), Lesson_screen_description.class)
                        .putExtra(screen_component, adore_screen_components)
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

        return "Attachments";
    }
}