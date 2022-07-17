package com.example.asl.ui.Lesson_screen.Topic;

import com.example.asl.controller.app_data.Intent_key;

import java.io.Serializable;

public interface Lesson_topics extends Serializable {
    //new instance of operation that will be performed
    public Intent_key intent_key = new Intent_key();
    //get constant string of screen component
    public String screen_component = intent_key.getScreen_component();
    //get constant string of translator label
    public String translator_label = intent_key.getTranslator_label();
    //get constant string of the translator's topic
    public String translator_lesson_topics = intent_key.getTranslator_lesson_topics();
}
