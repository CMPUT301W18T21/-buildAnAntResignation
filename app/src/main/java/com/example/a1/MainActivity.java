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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Represents the main activity (entry point) of the program.
 * From here a user can choose to request or provide a task, or view their user profile.
 * The current user is stored here.
 * @see User
 */
public class MainActivity  extends AppCompatActivity {

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
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        setTitle("Main Screen");

        ((TextView) findViewById(R.id.username)).setText(username);

        user = Server.UserController.get(username);
        if (user == null){
            Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        //Server.UserController.delete("nik1");
        //Task task1 = Server.TaskController.getRequested("1","DO NOT DELETE");
        //user.provideTask(task1);


        Button viewProfile = (Button) findViewById(R.id.viewProfileButton);
        viewProfile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactInfo.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });

        Button requestTask = (Button) findViewById(R.id.requestButton);
        requestTask.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequesterMain.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });


        Button provideTask = (Button) findViewById(R.id.provideButton);
        provideTask.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProviderMainPage.class);
                //intent.putExtra("username",username);
                startActivity(intent);

            }
        });
    }

}