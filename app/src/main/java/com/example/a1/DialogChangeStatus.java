package com.example.a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogChangeStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_change_status);

        Button Done = (Button) findViewById(R.id.buttonDone);
        Button Assigned = (Button) findViewById(R.id.buttonAssigned);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AssignedTasks.get(position).setDone();
                Intent intent = new Intent(DialogChangeStatus.this,RequesterAssignedTasks.class);

                startActivity(intent);

            }
        });

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
