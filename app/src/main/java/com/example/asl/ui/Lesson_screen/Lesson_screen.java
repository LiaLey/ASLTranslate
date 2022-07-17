package com.example.asl.ui.Lesson_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.asl.R;
import com.example.asl.ui.Lesson_screen.Topic.Adverbs;
import com.example.asl.ui.Lesson_screen.Topic.Alphabets;
import com.example.asl.ui.Lesson_screen.Topic.Attachments;
import com.example.asl.ui.Lesson_screen.Topic.Numbers;
import com.example.asl.ui.Lesson_screen.Topic.Pronouns;

import java.util.ArrayList;

public class Lesson_screen extends Fragment {

    //view that contains all display elements
    private View root;

    //view that contains Lesson display elements
    private View lesson_root;

    //the context of the Lesson system
    private static Context lesson_context;

    //button for the category
    private ArrayList<View> category_buttons;

    /**
     *
     * @param context Shows the context of Lesson
     */
    public Lesson_screen(Context context){
        this.lesson_context = context;

    }

    /**
     * This method is called when the view is created for the first time, or each time user comes to "translator" winndow
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return root - Initialized view with all the elements in it.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.lesson_screen, container, false);

        //Show all the Lesson display elements
        lesson_root = root;

        //initialize the topic button
        init_category_buttons();

        return root;
    }

    /**
     *called to initialize the category buttons
     */
    private void init_category_buttons(){
        lesson_root.findViewById(R.id.adverbs_button).setOnClickListener(new View.OnClickListener() {
            /**
             * set to allow clicking on the change camera button will allow user to switch camera
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Adverbs.class);
                lesson_context.startActivity(intent);


            }
        });

        lesson_root.findViewById(R.id.alphabets_button).setOnClickListener(new View.OnClickListener() {
            /**
             * set to allow clicking on the change camera button will allow user to switch camera
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Alphabets.class);
                lesson_context.startActivity(intent);


            }
        });

        lesson_root.findViewById(R.id.attachments_button).setOnClickListener(new View.OnClickListener() {
            /**
             * set to allow clicking on the change camera button will allow user to switch camera
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Attachments.class);
                lesson_context.startActivity(intent);


            }
        });

        lesson_root.findViewById(R.id.numbers_button).setOnClickListener(new View.OnClickListener() {
            /**
             * set to allow clicking on the change camera button will allow user to switch camera
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Numbers.class);
                lesson_context.startActivity(intent);


            }
        });

        lesson_root.findViewById(R.id.pronoun_button).setOnClickListener(new View.OnClickListener() {
            /**
             * set to allow clicking on the change camera button will allow user to switch camera
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Pronouns.class);
                lesson_context.startActivity(intent);


            }
        });



    }

    /**
     *method is called to get the Lesson context
     * @return context  - context of lesson screen
     */
    public static Context getLesson_context(){
        return lesson_context;
    }


}

