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

/**
 * Represents a view-provider's-assigned-task activity.
 * Here a user can see a list of their assigned tasks, as well as
 * the tasks' details.
 *
 * @see Task
 */
public class ProviderAssignedTasks extends AppCompatActivity {

    private Button BackButton;
    private String username;
    private User user;

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_assigned_tasks);
        setTitle("Assigned Tasks");
        Intent intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();
        getUserProfileTask.execute(username);
        try{
            user = getUserProfileTask.get();
        }catch(Exception e){
            Log.i("user doesn't exist","user doesn't exist");
        }
        ((TextView) findViewById(R.id.username)).setText(username);
        setupBackButton();

    }
    private void setupBackButton() {
        /** when add button is clicked jump back to requester's main page
         */

        BackButton = (Button) findViewById(R.id.viewBack);
        BackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ProviderAssignedTasks.this, RequesterMain.class);
                startActivityForResult(intent, 1);

            }
        });

    }
}
