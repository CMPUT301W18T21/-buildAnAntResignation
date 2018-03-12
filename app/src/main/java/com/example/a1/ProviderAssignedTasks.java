package com.example.a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProviderAssignedTasks extends AppCompatActivity {
    Button BackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_assigned_tasks);
        setTitle("Assigned Tasks");
        ((TextView) findViewById(R.id.username)).setText(MainActivity.getCurrentUser().getUsername());
        setupBackButton();

    }
    private void setupBackButton() {
        /** when add button is clicked jump back to requester's main page
         */

        BackButton = (Button) findViewById(R.id.viewBack);
        BackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ProviderAssignedTasks.this, RequesterMain.class);
                startActivityForResult(intent, 1);

            }
        });

    }
}
