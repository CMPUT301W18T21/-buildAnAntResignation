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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Represents the main activity (entry point) of the program.
 * From here a user can choose to request or provide a task, or view their user profile.
 * The current user is stored here.
 * @see User
 */
public class MainActivity  extends AppCompatActivity {

    private static User user = new User("name","Us3rn4m3", "male","7","@","img");

    private static Task task = new Task("none","none","none");

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Gets the current user.
     * @return The current user.
     */
    public static User getCurrentUser(){
        return user;
    }

    public static Task getSelectedTask(){return task;}


}




