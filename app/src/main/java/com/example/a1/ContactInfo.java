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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private Integer RESULT_LOAD_IMAGE = 1;

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
        ((ImageView) findViewById(R.id.userImage)).setImageURI(user.getImage());
    }

    /**
     * Save edited cahnges to a user profile.
     *
     * @param view The caller view.
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

    /**
     * Uploads a picture to the user profile.
     * @param view The caller view.
     */
    public void onPictureClick(View view){

        Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();

            ((ImageView)findViewById(R.id.userImage)).setImageURI(selectedImage);

            //user.setImage(selectedImage);
        }
    }


}
