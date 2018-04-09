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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Represents a view-provider's-assigned-task activity.
 * Here a user can see a list of their assigned tasks, as well as
 * the tasks' details.
 *
 * @see Task
 */
public class ProviderAssignedTasks extends AppCompatActivity {

    private String username;
    private User user;

    private ArrayList<String> titleList;
    private ArrayList<String> nameList;
    private ArrayList<String> statusList;
    private ArrayList<Integer> acceptBid;
    private ArrayList<Task>  taskHolder;

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
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        user = Server.UserController.get(username);
        if (user.getProvidedTasks() == null){
            Toast.makeText(ProviderAssignedTasks.this, "No Assigned Tasks!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            taskHolder = user.getProvidedTasks();
            for (Task eachtask:taskHolder){
                titleList.add(eachtask.getTitle().toString());
                nameList.add(eachtask.getRequesterName());
                statusList.add(eachtask.getStatus().toString());

                //----check this part cuz there is no such getter to get proper bid ------------------------------------------
                acceptBid.add(eachtask.getLowestBid());

            }
        }
        ((TextView) findViewById(R.id.username)).setText(username);
        CustomAdapter customAdapter = new CustomAdapter();
        ListView providerassignedList = findViewById(R.id.p_assigned_list);
        providerassignedList.setAdapter(customAdapter);
        setupBackButton();

    }
    private void setupBackButton() {
        /** when add button is clicked jump back to requester's main page
         */

        Button BackButton = (Button) findViewById(R.id.viewBack);
        BackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ProviderAssignedTasks.this, RequesterMain.class);
                startActivityForResult(intent, 1);

            }
        });

    }
    class CustomAdapter extends BaseAdapter {

        @Override

        public int getCount(){
            return 0;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout2,null);
            TextView textView_task=(TextView)view.findViewById(R.id.textView_task);
            TextView textView_username=(TextView)view.findViewById(R.id.textView_username);
            TextView textView_lowestBid=(TextView)view.findViewById(R.id.textView_lowestBid);
            TextView textView_status=(TextView)view.findViewById(R.id.textView_status);

            //textView_username.setText(user.getName());
            textView_username.setText(nameList.get(i));
            textView_status.setText(statusList.get(i));
            textView_lowestBid.setText(acceptBid.get(i).toString());
            textView_task.setText(titleList.get(i));



            return view;
        }



    }
}
