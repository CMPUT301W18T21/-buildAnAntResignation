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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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
    private static User user = new User("name","Us3rn4m3", "male","7","@","img");

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======
        setupViewProfileButton();
        setupRequestTaskButton();
        setupProvideTaskButton();

>>>>>>> completed half of add-photo task
    }

    /**
     * Gets the current user.
     * @return The current user.
     */
    public static User getCurrentUser(){
        return user;
    }
    private void setupViewProfileButton() {
        /** when add button is clicked jump back to requester's main page
         */

        viewProfile = (Button) findViewById(R.id.viewProfileButton);
        viewProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);
                startActivityForResult(intent, 1);

            }
        });

    }
    private void setupRequestTaskButton() {
        /** when add button is clicked jump back to requester's main page
         */

        RequestTask = (Button) findViewById(R.id.requestButton);
        RequestTask.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);
                startActivityForResult(intent, 1);

            }
        });

    }
    private void setupProvideTaskButton() {
        /** when add button is clicked jump back to requester's main page
         */

        ProvideTask = (Button) findViewById(R.id.provideButton);
        ProvideTask.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);
                startActivityForResult(intent, 1);

            }
        });

    }

}
