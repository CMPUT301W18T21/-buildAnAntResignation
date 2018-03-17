package com.example.a1;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;


import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Provider_bid_task extends AppCompatActivity {

    private static Task task;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids =  task.getBids();
    /**private static ArrayList<String> bids = new ArrayList<>(0); **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_bid_task);
        setTitle("Provider Bid Task");

        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Integer> (this,android.R.layout.simple_list_item_1,bids);

        listView.setAdapter(adapter);

    }





    public void setTask(Task task){
        this.task = task;
        ((TextView) findViewById(R.id.TitleView)).setText(task.getTitle());
        ((TextView) findViewById(R.id.StatusView)).setText(task.getStatus());
        ((TextView) findViewById(R.id.LowestbidView)).setText(task.getLowestBid());
        ((TextView) findViewById(R.id.DescriptionView)).setText(task.getDescription());

    }



}