/*
 * ContactInfo
 *
 * CMPUT301W18T21
 *
 * March 10, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */

package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Represents the main activity (entry point) of the program.
 * From here a user can choose to request or provide a task, or view their user profile.
 * The current user is stored here.
 * @see User
 */
public class MainActivity  extends AppCompatActivity {
    Button viewProfile;
    Button RequestTask;
    Button ProvideTask;
    private Intent intent;
    private String username;
    private static User user;

    /********* add by jiahong ******/
    private static Task task = new Task("none","none","none");
    public static Task getSelectedTask(){return task;}
    /********** done adding ***********/


    public MainActivity() {
    }

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");
        setTitle("Main Screen");


        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();
        getUserProfileTask.execute(username);
        try{
            user = getUserProfileTask.get();
        }catch(Exception e){
            Log.i("user doesn't exist","user doesn't exist");
        }

        viewProfile = (Button) findViewById(R.id.viewProfileButton);
        viewProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });

        RequestTask = (Button) findViewById(R.id.requestButton);
        RequestTask.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequesterMain.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });


        ProvideTask = (Button) findViewById(R.id.provideButton);
        ProvideTask.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProviderMainPage.class);
                //intent.putExtra("username",username);
                startActivity(intent);

            }
        });
    }

}