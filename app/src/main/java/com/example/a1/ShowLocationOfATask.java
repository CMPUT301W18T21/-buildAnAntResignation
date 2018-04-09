/*
 * ShowLocationOfATask
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
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by k-h on 2018-04-07.
 */

public class ShowLocationOfATask extends AppCompatActivity implements OnMapReadyCallback{

    private static Task task;
    private double lat;
    private double lng;
    private String requesterName;
    private String taskName;

    /**
     * this method is to show the location of teh selected task on a map.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlocationofatask);

        Intent intent = getIntent();
        taskName = intent.getStringExtra("taskname");
        requesterName = intent.getStringExtra("requesterName");

        User requester = Server.UserController.get(requesterName);
        ArrayList<Task> taskList = requester.getRequestedTasks();

        for (Task eachTask: taskList){
            if (eachTask.getTitle().equals(taskName)){
                task = eachTask;
                Log.i("show me the name:",taskName);

            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * setting up the map
     * it gets the latitude and longitude of the task and then add a marker on the map.
     * then the camera will be moved to that location.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        lat = task.getLatitude();
        lng = task.getLongitude();
        LatLng location = new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(location).title("Location of the selected task"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));


    }
}
