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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Represents the requester main activity.
 * A list of a user's task requests is displayed here.
 * From here a user can initiate any task requests, view tasks that have been assigned to other users, and view bidded tasks.
 */
public class RequesterMain extends AppCompatActivity {

    private static ArrayList<String> tasksInfo = new ArrayList<>(0);
    private static ArrayAdapter<String> adapter;
    Button BiddedButton;
    ListView taskList;
    Button AssignedButton;
    String username;
    private static User user;
    private Intent intent;
    Button requestButton;



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

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tasksInfo);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        /* Remove Later*/
        tasksInfo.clear();
        Task task = new Task("Task1",username,"Description of task");
        user.requestTask(task);

        displayTasks();
        setupBiddedButton();
        setupRequestView();
        setupAssignedButton();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Intent intent = new Intent(RequesterMain.this, RequesterTaskDetail.class);
                        Task task = user.getRequestedTask((int)id);
                        RequesterTaskDetail.setTask(task);
                        intent.putExtra("username",username);
                        startActivityForResult(intent, 1);
                    }
                }
        );

    }

    /**
     * Adds the user's requested tasks to the list view.
     */
    private void displayTasks(){
        tasksInfo.clear();
        ArrayList<Task> tasks =user.getRequestedTasks();
        for (Task task: tasks) {
            tasksInfo.add(task.getTitle() + "\n" + task.getStatus());
        }
    }

    private void setupBiddedButton() {


        BiddedButton = (Button) findViewById(R.id.viewBiddedButton);
        BiddedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, RequesterBiddedTask.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    private void setupAssignedButton(){
        AssignedButton = (Button) findViewById(R.id.viewAssignedButton);
        AssignedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, RequesterAssignedTasks.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    private void setupRequestView() {
        /** when add button is clicked jump to addsubscription View
         */

        requestButton = (Button) findViewById(R.id.requestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, TaskRequest.class);
                intent.putExtra("username",username);
                startActivity(intent);


            }
        });

    }


}
