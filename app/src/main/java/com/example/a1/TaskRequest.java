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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Represents a task request activity.
 * Here a user can request a task, once a title and description have been provided.
 * @see Task
 */
public class TaskRequest extends AppCompatActivity {

    private Task task;
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
        setContentView(R.layout.activity_task_request);
        setTitle("Request Task");
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        user = Server.UserController.get(username);
        if (user == null){
            Toast.makeText(TaskRequest.this, "User not found!", Toast.LENGTH_SHORT).show();
            finish();
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
        if(user.checkRequestedTitle(title)) {
            Toast.makeText(TaskRequest.this, "You already have a task with this title.", Toast.LENGTH_SHORT).show();
            return;
        }
        String description = ((EditText) findViewById(R.id.descriptionEditText)).getText().toString();
        task = new Task(title, user.getUsername(), description);
        user.requestTask(task);
        Server.UserController.edit(user);
        Intent intent = new Intent(TaskRequest.this, RequesterMain.class);
        intent.putExtra("username",username);
        RequesterMain.displayTasks();
        startActivity(intent);
    }
}