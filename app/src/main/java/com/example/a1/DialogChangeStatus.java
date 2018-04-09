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
 * This dialog is for RequesterAssignedTasks
 * A user can change the status of a selcted task.
 */
public class DialogChangeStatus extends AppCompatActivity {

    private static Task task;

    @Override
    /**
     * Occurs everytime the form is created.
     * Here it sets the title of the form.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_change_status);
        setTitle("Assigned Task Options");
    }

    /**
     * Tells the dialogue which task it is changing the status off.
     * @param task The task whose status is being changed.
     */
    public static void setTask(Task task){
        DialogChangeStatus.task = task;
    }

    /**
     * Changes the status to REQUESTED
     * @param view The caller view
     */
    public void onClickRequested(View view){
        task.setRequested();
        Server.TaskController.edit(task,task);
        finish();

    }

    /**
     * Changes the status to BIDDED
     * @param view The caller view
     */
    public void onClickBidded(View view){
        task.setBidded();
        Server.TaskController.edit(task,task);
        finish();
    }

    /**
     * Changes the status to DONE
     * @param view The caller view
     */
    public void onClickDone(View view){
        task.setDone();
        Server.TaskController.edit(task,task);
        finish();
    }

    /**
     * Views the user profile of the provider.
     * @param view The caller view
     */
    public void onClickProviderProfile(View view){
        Intent intent = new Intent(DialogChangeStatus.this, ContactInfo.class);
        intent.putExtra("username",task.getProviderName());
        startActivity(intent);
    }
}