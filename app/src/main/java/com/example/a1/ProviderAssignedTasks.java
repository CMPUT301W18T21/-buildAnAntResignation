/*
 * ContactInfo
 *
 * CMPUT301W18T21
 *
 * March 10, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */

package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Represents a view-provider's-assigned-task activity.
 * Here a user can see a list of their assigned tasks, as well as
 * the tasks' details.
 *
 * @see Task
 */
public class ProviderAssignedTasks extends AppCompatActivity {

    /**
     * A method that executes every time the activity is shown on screen.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_assigned_tasks);
        setTitle("Assigned Tasks");
        ((TextView) findViewById(R.id.name)).setText(MainActivity.getCurrentUser().getUsername());

    }
}
