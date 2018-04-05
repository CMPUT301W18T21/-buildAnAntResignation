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
import android.widget.EditText;
import android.widget.TextView;

/**
 * Represents a contact-info-activity activity.
 * A user's name, gender, email, and phone can be edited here.
 * User's username and profile picture are also shown.
 *
 * @see User
 */
public class ContactInfo extends AppCompatActivity {

    private User user;
    private String username;

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        setTitle("Contact Info");
        Intent intent = getIntent();
        username = intent.getStringExtra("username");


        //Gets the user from the server
        user = Server.UserController.get(username);

        setUser(user);

    }

    /**
     * Sets the user who's contact info will be displayed.
     *
     * @param user The user who's contact info is to be displayed.
     */
    public void setUser(User user) {
        this.user = user;

        ((TextView) findViewById(R.id.username)).setText(username);
        ((EditText) findViewById(R.id.nameEditText)).setText(user.getName());
        ((EditText) findViewById(R.id.genderEditText)).setText(user.getGender());
        ((EditText) findViewById(R.id.phoneEditText)).setText(user.getPhone());
        ((EditText) findViewById(R.id.emailEditText)).setText(user.getEmail());
    }

    /**
     * Finish viewing or editing a user profile.
     * If the user displayed matches the current user, any changes to the contact
     * information will be saved. Otherwise they are ignored.
     */
    public void onButtonClick(View view){

        user.setName(((TextView) findViewById(R.id.nameEditText)).getText().toString());
        user.setGender(((TextView) findViewById(R.id.genderEditText)).getText().toString());
        user.setPhone(((TextView) findViewById(R.id.phoneEditText)).getText().toString());
        user.setEmail(((TextView) findViewById(R.id.emailEditText)).getText().toString());

        //Uploads the edited user to the server
        Server.UserController.edit(user);

        finish();
    }


}
