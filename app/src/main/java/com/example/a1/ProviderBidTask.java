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

public class ProviderBidTask extends AppCompatActivity {

    private static Task task;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids;

    /** textview list **/
    private String taskname;
    private String username;
    private String currentUser;
    private User requesterUwantToBid;
    private User user;
    private Integer index = 0;
    /**private static ArrayList<String> bid = new ArrayList<>(0); **/

    @Override
    /**
     * a method that execute every time the activity is shown.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_bid_task);
        setTitle("Provider Bid Task");


        Intent intent = getIntent();
        taskname = intent.getStringExtra("taskname");
        username = intent.getStringExtra("username");
        currentUser = intent.getStringExtra("currentuser");


        requesterUwantToBid = Server.UserController.get(username);  // the user that we want to bid
        user = Server.UserController.get(currentUser);   //login user himself

        ArrayList<Task> taskList = requesterUwantToBid.getRequestedTasks();

        Integer tempI = 0;
        for (Task eachTask: taskList){
            if (eachTask.getTitle().equals(taskname)){
                task = eachTask;
                Log.i("show me the name:",taskname);
                index = tempI;
            }
            tempI++;
        }

        Log.i("give me task name ",task.getTitle());


        TextView viewtitle =(TextView)findViewById(R.id.ViewTitle);
        TextView viewdescription = (TextView)findViewById(R.id.textView5);
        TextView viewlowestbid = (TextView)findViewById(R.id.LBidInfo);
        TextView viewstatus = (TextView)findViewById(R.id.StatusInfo);
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(currentUser);

        viewtitle.setText(task.getTitle());
        viewdescription.setText(task.getDescription());
        if (task.getLowestBid() == -1){
            viewlowestbid.setText("No bid");
        } else {
            Integer temLowest = task.getLowestBid();
            viewlowestbid.setText(temLowest.toString());
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
        setupMapButton();

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

            requesterUwantToBid.getRequestedTask(index).addBid(Value);
            requesterUwantToBid.getRequestedTask(index).addBidder(currentUser);

            Server.UserController.edit(requesterUwantToBid);


            user.getBiddedTasks().add(task);
            Server.UserController.edit(user);


        }
        catch(NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(ProviderBidTask.this, "Sorry. Invalid Input", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(ProviderBidTask.this, "Sorry. Error occur", Toast.LENGTH_SHORT).show();
        }

    }
    private void setupMapButton(){
        Button Map = (Button) findViewById(R.id.ViewOnMap);
        Map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(task.getLongitude()== null){
                    Toast.makeText(ProviderBidTask.this, "The location of this task is not added", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = new Intent(ProviderBidTask.this, ShowLocationOfATask.class);
//                   taskPackage.putString("taskName",taskname);
//                   taskPackage.putString("userName",username);
                    intent.putExtra("taskname", taskname);
                    intent.putExtra("requesterName", username);

                    startActivity(intent);
                }

            }
        });
    }





}