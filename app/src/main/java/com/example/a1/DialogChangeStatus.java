/*
 * DialogChangeStatus
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
import android.view.View;

/**
 * @author Liu
 * this is dialog for RequesterAssignedTask
 */
public class DialogChangeStatus extends AppCompatActivity {

    private static Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_change_status);
        setTitle("Assigned Task Options");
        setResult(RESULT_OK);
    }

    public static void setTask(Task task){
        DialogChangeStatus.task = task;
    }

    public void onClickRequested(View view){
        task.setRequested();
        Server.TaskController.edit(task,task);
        finish();

    }

    public void onClickBidded(View view){
        task.setBidded();
        Server.TaskController.edit(task,task);
        finish();
    }

    public void onClickDone(View view){
        task.setDone();
        Server.TaskController.edit(task,task);
        finish();
    }

    public void onClickProviderProfile(View view){
        Intent intent = new Intent(DialogChangeStatus.this, ContactInfo.class);
        intent.putExtra("username",task.getProviderName());
        startActivity(intent);
    }
}