package com.example.asl.controller.toast;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.asl.MainActivity;
import com.example.asl.R;

public class Custom_toasts {
    // context to display toast to user
    private Context context;

    // default text colour of toast is white
    private final int TOAST_TEXT_COLOR = R.color.white;

    //default toast is located in center
    private final int TOAST_TEXT_GRAVITY = Gravity.CENTER;

    //delay between each shown toast is 1.1 seconds
    private final int DELAY_BETWEEN_TOAST = 1100;  // this is in milliseconds

    //instance of toast to display new toast on the screen
    private Toast toast;

    //string of toast to be shown to user
    private String message;

    /**
     * Constructor for custom toast, needs a context to display the toast in
     * @param context - context to show the toast to user in
     */
    public Custom_toasts(Context context, String message){
        //set the current context to the context given into the constructor as input
        this.context = context;

        this.message = message;
    }

    /**
     * Show toast to user depending on input toast message and colour
     * @param long_toast - Boolean to represent wether the toast showned to user needs to be long or short toast
     */
    public void show_toast(boolean long_toast){
        //if another toast is being showned to user
        if(!MainActivity.toast_shown){

            int toast_duration;
            //define the toast duration to be showned to user

            if(long_toast){

                //if it is a long toast, set duration to length of long
                toast_duration = Toast.LENGTH_LONG;
            }
            else{

                //if it is a short toast, set dueation to length of short
                toast_duration = Toast.LENGTH_SHORT;
            }
            //make toast to be showed to user
            toast = Toast.makeText(context, this.message, toast_duration);

            //show toast to user
            toast.show();

            //set toast to be shown so no new toast can be shown be user
            MainActivity.toast_shown = true;

            // start countdown timer for one toast to be displayed at a time only.
            CountDownTimer countDownTimer = new CountDownTimer(DELAY_BETWEEN_TOAST, 1000) {

                @Override
                public void onTick(long l) {

                }

                /**
                 * Set once the countdown timer finished, set toast_shown to false and another new toast
                 * can be shown to user
                 */
                public void onFinish() {
                    MainActivity.toast_shown = false;
                }
            };

            //start the countdown timer
            countDownTimer.start();
        }

    }


}
