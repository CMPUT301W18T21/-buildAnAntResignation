/*
 * DialogSelectBid
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
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This is a dialog for RequesterTaskDetail.
 * It allows the user to accept or decline a selected bid.
 */
public class DialogSelectBid extends AppCompatActivity {

    private Task task;
    private String username;
    private Integer BidderPosition;
    private Integer taskIndex;

    @Override
    /**
     * Occurs every time the dialog is shown.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_select_bid);

        Intent intent = getIntent();

//        testcase.add("a");
//        testcase.add("b");
//        testcase.add("c");
//        testcase.add("d");
//        testcase.add("e");


        BidderPosition = intent.getIntExtra("position",999);
        username = intent.getStringExtra("username");
        taskIndex = intent.getIntExtra("taskIndex",999);

        setTitle("Accept Bid?");

        Log.d("test bidderPos",BidderPosition.toString());
        Log.d("test Username",username);
        Log.d("test taskIndex",taskIndex.toString());


        final Button accept = (Button)findViewById(R.id.buttonAccept);
        Button decline = (Button)findViewById(R.id.buttonDecline);


        //accept botton be clicked keep the bid and remove other bids of the Task and jump back to TaskDetailScreen
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcceptHandler();
                finish();

            }
        });


        //decline botton be clicked remove the bid of the task and jump back to TaskDetailScreen
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeclineHandler();
                finish();

            }
        });
    }

    /**
     * Handles bid acceptance.
     */
    private void AcceptHandler(){

        User loginUser = Server.UserController.get(username);
        Task acceptedTask = loginUser.getRequestedTask(taskIndex);
        String taskTitle = acceptedTask.getTitle();
        ArrayList<String> lostBidders = acceptedTask.getBidders();
        Log.d("show me len",String.valueOf(lostBidders.size()));
        String winner = lostBidders.get(BidderPosition);
        int winnerPrice = acceptedTask.getBids().get(BidderPosition);
        int ix = Integer.parseInt(BidderPosition.toString());
        lostBidders.remove(ix);

        //Then remove Bidded task from each lost Bidder
        for (String loser:lostBidders){
            User lostUser = Server.UserController.get(loser);
            ArrayList<Task> loserBiddedTasks  = lostUser.getBiddedTasks();
            int index = 0;
            int marker = 0;
            for (Task eachBiddedTask : loserBiddedTasks){
                if (eachBiddedTask.getTitle().equals(taskTitle) && eachBiddedTask.getRequesterName().equals(username)){
                    //we found the task which should be removed
                    marker = index;
                }
                index++;
            }
            //delete lost bidded task
            lostUser.getBiddedTasks().remove(marker);
            Server.UserController.edit(lostUser);

        }

        //then we update the winner who win the bid
        User winnerUer = Server.UserController.get(winner);
        ArrayList<Task> winnerBiddedTasks = winnerUer.getBiddedTasks();

        int index = 0;
        int marker = 0;
        Task winnerTask = null;
        // find the accepted task
        int winBid = 0;
        for (Task eachT : winnerBiddedTasks){

            if (eachT.getTitle().equals(taskTitle) && eachT.getRequesterName().equals(username)){
                winnerTask = eachT;
                marker = index;
            }
            index++;
        }
        winnerTask.getBids().clear();
        winnerTask.getBidders().clear();
        winnerTask.addBid(winnerPrice); //make sure user still can tarce his accepted price
        winnerTask.setAssigned();
        winnerUer.getBiddedTasks().remove(marker);
        winnerUer.provideTask(winnerTask);
        //now move task to providedTask
//        if (winnerUer.getProvidedTasks() != null) {
//            ArrayList<Task> tempL = winnerUer.getProvidedTasks();
//            tempL.add(winnerTask);
//            winnerUer.provideTask(tempL);
//        } else {
//            ArrayList<Task> newL = new ArrayList<Task>();
//            newL.add(winnerTask);
//            winnerUer.setProvidedTasks(newL);
//        }
//        //now update winner
//        Server.UserController.edit(winnerUer);

        //finally we change status of requester and clear bidder and bids
        loginUser.getRequestedTask(taskIndex).getBidders().clear();
        loginUser.getRequestedTask(taskIndex).getBids().clear();
        loginUser.getRequestedTask(taskIndex).addBid(winnerPrice);
        loginUser.getRequestedTask(taskIndex).setProviderName(winner);
        loginUser.getRequestedTask(taskIndex).setAssigned();
        Server.UserController.edit(loginUser);



    }

    /**
     * Handles bid declining.
     */
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