package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author Liu
 * this is dialog for RequesterAssignedTask
 */
public class DialogChangeStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_change_status);

        Button Done = (Button) findViewById(R.id.buttonDone);
        Button Assigned = (Button) findViewById(R.id.buttonAssigned);


        //when Done button is clicked , status of the task change to Done and jump back to RequesterAssignedTask
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AssignedTasks.get(position).setDone();
                Intent intent = new Intent(DialogChangeStatus.this,RequesterAssignedTasks.class);

                startActivity(intent);

            }
        });


        //when Assigned button is clicked , status of the task change to Assigned and jump back to RequesterAssignedTask
        Assigned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AssignedTasks.get(position).setAssigned();
                Intent intent = new Intent(DialogChangeStatus.this,RequesterAssignedTasks.class);

                startActivity(intent);
            }
        });
    }
}