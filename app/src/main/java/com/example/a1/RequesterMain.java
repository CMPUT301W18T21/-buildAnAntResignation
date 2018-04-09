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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Represents the requester main activity.
 * A list of a user's task requests is displayed here.
 * From here a user can initiate any task requests, view tasks that have been assigned to other users, and view bidded tasks.
 */
public class RequesterMain extends AppCompatActivity {

    static private ArrayList<String> tasksInfo = new ArrayList<>(0);
    static private ArrayAdapter<String> adapter;
    private String username;
    private static User user;



    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_main);
        setTitle("Requester Main Page");
        Intent intent = getIntent();
        username = intent.getStringExtra("username");


        user = Server.UserController.get(username);
        if (user == null){
            Toast.makeText(RequesterMain.this, "User not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        ((TextView) findViewById(R.id.username)).setText(username);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tasksInfo);
        ListView listView = findViewById(R.id.p_assigned_list);
        listView.setAdapter(adapter);

        tasksInfo.clear();

        displayTasks();
        setupBiddedButton();
        setupRequestView();
        setupAssignedButton();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Intent intent = new Intent(RequesterMain.this, RequesterTaskDetail.class);
                        Task task = user.getRequestedTask((int)id);
                 //       RequesterTaskDetail.setTask(task);
                        intent.putExtra("username",username);
                        intent.putExtra("taskIndex",position);
                        startActivityForResult(intent, 1);
                    }
                }
        );

    }


    /**
     * Adds the user's requested tasks to the list view.
     */
    public void displayTasks(){
        user = Server.UserController.get(user.getUsername());
        if (user == null) return;
        tasksInfo.clear();
        ArrayList<Task> tasks = user.getRequestedTasks();
        for (Task task: tasks) {
            tasksInfo.add("Task: " + task.getTitle() + "\nStatus: " + task.getStatus());
        }
    }

    /**
     * when the button is clicked, it goes to viewBiddedButton screen, and then it passes the necessary information to that class.
     */
    private void setupBiddedButton() {
        ((Button) findViewById(R.id.viewBiddedButton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, RequesterBiddedTask.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }
    /**
     * when the button is clicked, it goes to viewAssignedButton screen, and then it passes the necessary information to that class.
     */
    private void setupAssignedButton(){
        ((Button) findViewById(R.id.viewAssignedButton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, RequesterAssignedTasks.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    /**
     * When request button is clicked jump to request view
     */
    private void setupRequestView() {
        ((Button) findViewById(R.id.requestButton)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, TaskRequest.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    /**
     * once the refresh button is clicked, it refreshes the screen to display the newest version.
     * @param view
     */

    public void onRefreshClick(View view){
        displayTasks();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tasksInfo);
        ListView listView = findViewById(R.id.p_assigned_list);
        listView.setAdapter(adapter);
    }

}
