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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Represents a task request activity.
 * Here a user can request a task, once a title and description have been provided.
 * @see Task
 */
public class TaskRequest extends AppCompatActivity {

    private Task task;
    Intent intent;
    private static User user;
    private String username;

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_request);
        setTitle("Request Task");
        intent = new Intent();
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
    }

    /**
     * A method that executes once the user presses the Request button.
     * Task title and description are saved and added to the user's tasks requests.
     * @param view The caller view.
     */

    public void onRequestClick(View view){
        String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        String description = ((EditText) findViewById(R.id.descriptionEditText)).getText().toString();
        if (title.length() <= 30 && description.length() <= 300) {
            task = new Task(title, user.getUsername(), description);
            user.requestTask(task);
            finish();
        }
    }
}

