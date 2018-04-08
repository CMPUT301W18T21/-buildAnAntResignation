package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Provider_bid_task extends AppCompatActivity {

    private static Task task;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids;



    /** textview list **/

    private TextView viewtitle;
    private TextView viewdescription;
    private TextView viewlowestbid;
    private TextView viewstatus;
    private Button ADDBid;
    private Button Map;
    private String taskname;
    private String username;
    private String currentUser;
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
//        Bundle getBundle = intent.getExtras();
//        taskname = getBundle.getString("taskName");
//        username = getBundle.getString("userName");
        taskname = intent.getStringExtra("taskname");
        username = intent.getStringExtra("username");
        currentUser = intent.getStringExtra("currentuser");


//        Log.d("requester name",username);
//        Log.d("requested task name",taskname);
//        Log.d("currentUser",currentUser);

        User requesterUwantToBid = Server.UserController.get(username);  // the user that we want to bid
        User user = Server.UserController.get(currentUser);   //login user himself

        ArrayList<Task> taskList = requesterUwantToBid.getRequestedTasks();
        for (Task eachTask: taskList){
            if (eachTask.getTitle().equals(taskname)){
                task = eachTask;
                Log.i("show me the name:",taskname);
            }
        }

        Log.i("give me task name ",task.getTitle());


        viewtitle =(TextView)findViewById(R.id.ViewTitle);
        viewdescription = (TextView)findViewById(R.id.textView5);
        viewlowestbid = (TextView)findViewById(R.id.LBidInfo);
        viewstatus = (TextView)findViewById(R.id.StatusInfo);


        //  Intent intent =getIntent();
        //  String curTitle= intent.getStringExtra(ProviderMainPage.Title);
        //  String curStatus= intent.getStringExtra(ProviderMainPage.Status);
        //  String curLowestBid= intent.getStringExtra(ProviderMainPage.LowestBid);
        //  String curDescription= intent.getStringExtra(ProviderMainPage.Description);

        viewtitle.setText(task.getTitle());
        viewdescription.setText(task.getDescription());
        if (task.getLowestBid() == -1){
            viewlowestbid.setText("No bid");
        } else {
            viewlowestbid.setText(task.getLowestBid());
        }
        viewstatus.setText(task.getStatus().toString());





        /** debugging
         bid = new ArrayList<>(0);
         bid.add(10);
         bid.add(20);
         bid.add(9);
         task = new Task("test task","username","test description","Location",Status.REQUESTED,bid);

         **/


//check
        if (task.getBids()==null){

            Log.i("NO bid found","No bid found");
            finish();
        } else {
            bids = task.getBids();
            ListView listView = findViewById(R.id.BidList);
            adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, bids);

            listView.setAdapter(adapter);
        }

    }

    /**
     * this onButtonCLick is to save the bid and check the input type.
     * if it is not int type, it says sorry invalid unput.
     * otherwise it passes to task.addBid
     * @param view
     */
    public void onButtonClick(View view){
        String num = ((EditText) findViewById(R.id.BidValue)).getText().toString();
        try{
            int Value = Integer.parseInt(num);

            User user = Server.UserController.get(username);
            if (user == null){
                Toast.makeText(Provider_bid_task.this, "User not found!", Toast.LENGTH_SHORT).show();
                return;
            }


            task.addBid(Value);
            task.setBidded();
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
    private void setupMapButton(){
        Map = (Button) findViewById(R.id.ViewOnMap);
        Map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Provider_bid_task.this,ShowLocationOfATask.class);
                Bundle taskPackage = new Bundle();
                taskPackage.putString("taskName",taskname);
                taskPackage.putString("userName",username);
                intent.putExtra("taskBundle", taskPackage);
                startActivity(intent);
                */
            }
        });
    }





}