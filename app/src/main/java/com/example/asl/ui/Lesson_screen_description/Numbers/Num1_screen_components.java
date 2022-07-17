package com.example.asl.ui.Lesson_screen_description.Numbers;

import com.example.asl.R;
import com.example.asl.ui.Lesson_screen_description.Lesson_screen_components;


public class Num1_screen_components implements Lesson_screen_components {
    /**
     * Getter for gif_id
     * @return gif_id - id for gif to be inserted as gif to be viewed by user
     */
    public int get_gif_id(){
        return R.drawable.one;
    }
    /**
     * Getter for title_id
     * @return title_id - id for title to be inserted into screen to let user know what syllabus user is un
     */
    public int get_title_id(){
        return R.string.num1_title;
    }
    /**
     * getter for content_id
     * @return content_id - id for content to be inserted into screen, to teach user about the syllabus
     */
    public int get_content_id(){
        return R.string.num1_content;
    }

}
