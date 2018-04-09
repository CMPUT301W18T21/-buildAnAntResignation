package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * this dialog is for RequesterTaskDetail
 */

public class DialogSelectBid extends AppCompatActivity {

    private Task task;
    private String username;
    private Integer BidderPosition;
    private Integer taskIndex;


    private ArrayList<String> testcase = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_select_bid);

        Intent intent = getIntent();

        testcase.add("a");
        testcase.add("b");
        testcase.add("c");
        testcase.add("d");
        testcase.add("e");


        BidderPosition = intent.getIntExtra("position",999);
        username = intent.getStringExtra("username");
        taskIndex = intent.getIntExtra("taskIndex",999);

        Log.d("test bidderPos",BidderPosition.toString());
        Log.d("test Username",username);
        Log.d("test taskIndex",taskIndex.toString());


        Button accept = (Button)findViewById(R.id.buttonAccept);
        Button decline = (Button)findViewById(R.id.buttonDecline);


        //accept botton be clicked keep the bid and remove other bids of the Task and jump back to TaskDetailScreen
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialogSelectBid.this,RequesterTaskDetail.class);

                startActivity(intent);

            }
        });


        //decline botton be clicked remove the bid of the task and jump back to TaskDetailScreen
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DialogSelectBid.this,RequesterTaskDetail.class);
                DeclineHandler();
                intent.putExtra("username",username);
                intent.putExtra("taskIndex",taskIndex);
                startActivity(intent);

            }
        });
    }

    //public static void setTask2(Task task){DialogSelectBid.task = task;}
    private void DeclineHandler (){
        User loginUser = Server.UserController.get(username);
        String requesterTaskTitle = loginUser.getRequestedTask(taskIndex).getTitle();
        String privider = loginUser.getRequestedTask(taskIndex).getBidder(BidderPosition);

        //-----------------------testing----------------------------------

        //ArrayList<String> bidderList = loginUser.getRequestedTask(taskIndex).getBidders()


        //----------------------------------------------------------------

        int ix = Integer.parseInt(BidderPosition.toString());
        loginUser.getRequestedTask(taskIndex).deleteBid(ix);
        loginUser.getRequestedTask(taskIndex).deleteBidder(ix);

        Server.UserController.edit(loginUser);

        User provider = Server.UserController.get(privider);

        ArrayList<Task> biddedTask = provider.getBiddedTasks();

        int count = 0;
        int marker = 0;
        for (Task eachTask:biddedTask){
            if (eachTask.getTitle().equals(requesterTaskTitle)){
                marker = count;
            }
            count++;
        }
        provider.getBiddedTasks().remove(marker);
        Server.UserController.edit(provider);


//        Log.d("Before remove",testcase.toString());
//        Integer removetest = 3;
//        int re = Integer.parseInt(removetest.toString());
//        testcase.remove(removetest);
//        Log.d("After remove",testcase.toString());




    }

}