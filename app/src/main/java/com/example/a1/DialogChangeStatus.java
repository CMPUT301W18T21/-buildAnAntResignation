package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Liu
 * this is dialog for RequesterAssignedTask
 */
public class DialogChangeStatus extends AppCompatActivity {

    public static Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_change_status);
    }

    public static void setTask(Task task){
        DialogChangeStatus.task = task;
    }

    public static Task getTask(){
        return task;
    }

    public void onClickRequested(View view){

    }

    public void onClickBidded(View view){

    }

    public void onClickDone(View view){

    }
}