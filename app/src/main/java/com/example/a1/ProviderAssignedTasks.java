package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProviderAssignedTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_assigned_tasks);
        setTitle("Assigned Tasks");
        ((TextView) findViewById(R.id.username)).setText(MainActivity.getCurrentUser().getUsername());

    }
}
