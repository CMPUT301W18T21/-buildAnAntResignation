package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Provider_bid_task extends AppCompatActivity {

    private static Task task;
    private String username;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids;
    /**private static ArrayList<String> bid = new ArrayList<>(0); **/

    /**
     * a method that execute every time the activity is shown.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_bid_task);
        setTitle("Provider Bid Task");
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        //  Intent intent =getIntent();
        //  String curTitle= intent.getStringExtra(ProviderMainPage.Title);
        //  String curStatus= intent.getStringExtra(ProviderMainPage.Status);
        //  String curLowestBid= intent.getStringExtra(ProviderMainPage.LowestBid);
        //  String curDescription= intent.getStringExtra(ProviderMainPage.Description);

        //  ((TextView) findViewById(R.id.TitleView)).setText(curTitle);
        //((TextView) findViewById(R.id.StatusView)).setText(task.getStatus().toString());
        //((TextView) findViewById(R.id.LowestbidView)).setText(task.getLowestBid());
        //  ((TextView) findViewById(R.id.DescriptionView)).setText(curDescription);




        /** debugging
         bid = new ArrayList<>(0);
         bid.add(10);
         bid.add(20);
         bid.add(9);
         task = new Task("test task","username","test description","Location",Status.REQUESTED,bid);

         **/


        task = MainActivity.getSelectedTask();
        setTask(task);
        bids =task.getBids();


        ListView listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<Integer> (this,android.R.layout.simple_list_item_1,bids);

        listView.setAdapter(adapter);

    }

    /**
     * this onButtonCLick is to save the bid and check the input type.
     * if it is not int type, it says sorry invalid unput.
     * otherwise it passes to task.addBid
     * @param view
     */
    public void onButtonClick(View view){
        String num = ((EditText) findViewById(R.id.Bid_Value)).getText().toString();
        try{
            int Value = Integer.parseInt(num);

            User user = Server.UserController.get(username);
            if (user == null){
                Toast.makeText(Provider_bid_task.this, "User not found!", Toast.LENGTH_SHORT).show();
                return;
            }

            user.bidTask(task);
            task.addBid(Value);
            task.addBidder(username);

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


    /**
     * setTask is to set the selected task and display the information to the screen so that the user
     * can see the information of the task they selected.
     * @param task
     */


    public void setTask(Task task){
        this.task = task;
        ((TextView) findViewById(R.id.TitleView)).setText(task.getTitle());
        //((TextView) findViewById(R.id.StatusView)).setText(task.getStatus().toString());
        //((TextView) findViewById(R.id.LowestbidView)).setText(task.getLowestBid());
        ((TextView) findViewById(R.id.DescriptionView)).setText(task.getDescription());

    }



}