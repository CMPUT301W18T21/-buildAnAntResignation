package com.example.a1;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Provider_bid_task extends AppCompatActivity {

    private static Task task;
    private User user;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids = task.getBids();
    /**private static ArrayList<String> bids = new ArrayList<>(0); **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_bid_task);
        setTitle("Provider Bid Task");
        setTask(task);

        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Integer> (this,android.R.layout.simple_list_item_1,bids);

        listView.setAdapter(adapter);

    }

    public void onButtonClick(View view){
        String num = ((EditText) findViewById(R.id.Bid_Value)).getText().toString();
        try{
            int Value = Integer.parseInt(num);
            user.bidTask(task);
            user.bids(Value);


        }
        catch(NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(Provider_bid_task.this, "Sorry. Invalid Input", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(Provider_bid_task.this, "Sorry. Error occur", Toast.LENGTH_SHORT).show();
        }

    }





    public void setTask(Task task){
        this.task = task;
        ((TextView) findViewById(R.id.TitleView)).setText(task.getTitle());
        ((TextView) findViewById(R.id.StatusView)).setText(task.getStatus().toString());
        ((TextView) findViewById(R.id.LowestbidView)).setText(task.getLowestBid());
        ((TextView) findViewById(R.id.DescriptionView)).setText(task.getDescription());

    }



}