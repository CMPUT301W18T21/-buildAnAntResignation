/*
 * RequesterBiddedTask
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.a1.Status.BIDDED;

/**
 * An activity that shows whihc of the users task have bids.
 */
public class RequesterBiddedTask extends AppCompatActivity {

    private ArrayList<Task> biddedTasks = new ArrayList<>(0);
    private User user;
    private String username;



    /**
     * This method runs on the activity creation.
     * It sets up some of the UI elements.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_bidded_task);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        setTitle("Bidded Tasks");

        user = Server.UserController.get(username);

        getBiddedTasks();
        TextView textView=(TextView)findViewById(R.id.username);
        textView.setText(username);
        ListView listView = (ListView)findViewById(R.id.viewBiddedTask);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    /**
     * Updates the array list (that the list view adapter uses)
     * to have all the bidded tasks of the user.
     */
    public void getBiddedTasks(){
        biddedTasks = new ArrayList<>(0);
        ArrayList<Task> allTasks = user.getRequestedTasks();
        for(Task task : allTasks){
            if (task.getBids().size() > 0 ) biddedTasks.add(task);
        }

    }

    /**
     * A custom display adapter for displaying task information.
     */
    class CustomAdapter extends BaseAdapter{
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
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            for (Integer k = 0; k < biddedTasks.get(i).getBidders().size();k++) {
                ((TextView) view.findViewById(R.id.textView_task)).setText(biddedTasks.get(i).getTitle());
                ((TextView) view.findViewById(R.id.textView_name)).setText(biddedTasks.get(i).getBidder(k));
            }
            return view;
        }

        @Override

        public int getCount(){
            return biddedTasks.size();
        }

    }



}

