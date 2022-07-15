package com.example.asl.ui.Translator_screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.asl.R;
import com.example.asl.controller.camera.Camera_handle;
import com.example.asl.controller.translator.Translator;
import com.example.asl.controller.translator.Translator_categories;

import java.util.HashMap;

public class Translator_screen extends AppCompatActivity {

    //layout components
    public FrameLayout framelayout;

    //button to change camera
    private ImageView change_camera;

    private AutoCompleteTextView spinner_text_view;

    //button to get user's camera permission
    private Button camera_permission;

    // text to show classified text and camera type on the screen to users
    private TextView classitext;

    // string to store the classified cateogory of the input image
    private String classify_category;


    // handles the camera, as well as frames of the camera
    private Camera_handle camera_handle;

    // handles translation of sign language to english text
    private Translator translator;

    // handles all categories that is in sign language
    private Translator_categories translator_categories;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translator_screen);

        // get components displayed on screen
        set_layout_components();

        //set onclick function of switch camera
        set_on_click_switch_camera();

        //set onclick function to get user's camera permission
        set_on_click_camera_permission();

        //creating new instance of camera_handle to handle camera connections
        camera_handle = new Camera_handle(this,this,framelayout,classitext);

        //getting the camera_handle's connection to translator (TFLITE)
        translator = camera_handle.getTranslator();

        //creating new class of translator categories
        translator_categories = new Translator_categories();

        //initialize the spinner for user to choose different categories of sign language
        init_drop_down();

        // the first category is the category loaded into the translator by default
        classify_category  =translator_categories.get_Beginning_Category();

        //loading the default category (the first category) into the translator
        translator.load_model_tflite(classify_category);

        //check if need to request for user's permission
        start_request_permission();


    }


    /**
     * This function initializes the component inside the drop-down, as well as customize
     * the look of drop down
     */
    private void init_drop_down(){
        //generating arrayadapter for spinner to customize spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_dropdown_icon,translator_categories.getItems());

        //set the view of the drop down to look better with the theme of App
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);

        //set the adapter of drop down to our custom drop down
        spinner_text_view.setAdapter(adapter);

        //set what happens when a category is clicked by user
        spinner_text_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            /**
             * set what happends when a category is selected by user
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get a hashmap that maps all categories of the app to number
                HashMap<Integer,String> items_with_number =translator_categories.getItems_with_number();

                // get the category that is clicked by the user in string
                classify_category = items_with_number.get((Integer)(int)id);

                //load new category into translator
                translator.load_model_tflite(classify_category);

                //reset the interval before next translation takes place
                // this allows the translator to start checking new sign based on new category
                camera_handle.setInterval_counter(camera_handle.getTranslate_interval());
            }

        });

    }


    /**
     * This method gets all components from the user's view
     *
     */
    private void set_layout_components(){
        //getting framelayout to show camera contents
        framelayout = findViewById(R.id.camera_frame);

        //get the text to show user texts after translator has classified sign language
        classitext = findViewById(R.id.label_classification_text);

        //get the button that allows user to switch camera inside the app.
        change_camera = findViewById(R.id.switch_camera_icon);

        // find button that allows app to get user's permission
        camera_permission = findViewById(R.id.allow_camera_permission_button);

        spinner_text_view = findViewById(R.id.spinner_text_view);
    }

    /**
     * set the switch camera button to allow user to change to different camera
     */
    private void set_on_click_switch_camera(){
        change_camera.setOnClickListener(new View.OnClickListener() {
            /**
             * set to allow clicking on the change camera button will allow user to switch camera
             * @param v
             */
            @Override
            public void onClick(View v) {
                camera_handle.switch_camera_met();
            }
        });
    }

    /**
     * Allow clicking on camera permission button to allow user to get camera permission
     */
    private void set_on_click_camera_permission(){
        camera_permission.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                request_permission();
            }
        });
    }



    //permissions, as dealing with fragments is too complicated, and errorous
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void start_request_permission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            //permission is not granted by the user
            permission_not_granted(false);

            //request for permission from user for the camera
            request_permission();
        } else {

            //permission is already granted by user.
            permission_granted(true);

        }
    }

    /**
     * If the camera permission is granted, it will start the camera and start to classify hand signs
     * if the camera permission was recently granted by user, a toast will be shown
     * @param permission_initially_granted - boolean for if the camera permission was initially granted or not
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void permission_granted(boolean permission_initially_granted) {

        if(!permission_initially_granted){
            //permission was not initially granted by user, but recently granted by user
            Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();

        }

        //start the camera handle to start loading frames from camera onto screen and
        //sending frames into the TensorFlowLite API
        camera_handle.start_camera_met();

        //disable button to get camera permission from user, since camera permission is granted
        enable_or_disable_view(camera_permission,false);

        //show the framelayout and other components of translator to be viewed by user.
        //These elements are hidden by default
        show_translator_elements(true);

    }

    /**
     * This function is called when permission is not granted for camera
     * @param user_clicked_no - User has clicked deny permission or not
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void permission_not_granted(boolean user_clicked_no) {

        // if user has clicked not to grant permission when dialog box appears
        if(user_clicked_no){
            // show a toast saying camera permission is not granted to user
            Toast.makeText(this, "Camera Permission not Granted", Toast.LENGTH_SHORT).show();
        }

        // enable camera permission button so that user can show toast to grant permission of app to camera
        enable_or_disable_view(camera_permission,true);

        // hide translator elements, that is elements related to translator such as switch camera button
        show_translator_elements(false);

    }

    /**
     * request for permission from user using a thread by calling request_permission_thread
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void request_permission() {
        request_permission_thread();

    }

    /**
     * request for camera permission from user.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void request_permission_thread() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
    }


    /**
     * this function is called when user has either clicked allow or deny on the user permission
     * for the user's cameras
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // if the user has granted permission

                permission_granted(false);
            } else {
                //if the user has not granted camera permission
                permission_not_granted(true);
            }
        }
    }



    /**
     * this function controls the visibility of translator elements, such as switch camera button.
     * When translator elements are hidden, user cannot interact or click it.
     * @param show - boolean, if it's true, translator elements are shown. If not they are hidden
     */
    private void show_translator_elements(boolean show){
        if(show){
            //show translator elements

            //show switch_camera button
            enable_or_disable_view(change_camera,true);

            //show text which shows the result of classification of symbols
            classitext.setVisibility(View.VISIBLE);

            //shows spinner for user to change to different category for translating sign language
            spinner_text_view.setVisibility(View.VISIBLE);

            //allow user to change category by setting it to be enabled
            spinner_text_view.setEnabled(true);

        }
        else{
            //hide translator elements

            //hide switch camera button
            enable_or_disable_view(change_camera,false);

            //hide text which shows the result of classification of symbols
            classitext.setVisibility(View.GONE);

            //hides spinner for user to change to different category for translating sign language
            spinner_text_view.setVisibility(View.GONE);

            //disables user drom change category by setting it to be false
            spinner_text_view.setEnabled(false);
        }
    }

    /**
     * this function either sets a button to be visible, and enables it
     * or sets a button not to be visible, and disables it
     * @param view - view to be set
     * @param enable - boolean wether to enable it, and allow it to be seen, or disable it, and disallow it from being seen
     */
    private void enable_or_disable_view(View view,boolean enable){

        if(enable){
            //enables a button, and allow it to be visible

            //sets a button to be visible
            view.setVisibility(View.VISIBLE);

            //sets a button to be enabled to allow user to interact with it
            view.setEnabled(true);
        }
        else{
            //disables a button, and allow it to be not visible

            //sets a button to be not visible
            view.setVisibility(View.GONE);

            //sets a button to be disabled to not allow user to interact with it
            view.setEnabled(false);
        }
    }
}
