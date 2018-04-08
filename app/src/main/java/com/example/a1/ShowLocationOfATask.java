package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by k-h on 2018-04-07.
 */

public class ShowLocationOfATask extends AppCompatActivity implements OnMapReadyCallback{

    private String username;
    private String taskname;
    public Bundle getBundle = null;
    private static Task task;
    private double lat;
    private double lng;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showlocationofatask);

        getBundle = this.getIntent().getExtras();
        taskname = getBundle.getString("taskName");
        username = getBundle.getString("userName");
        Intent intent = getIntent();

        task = Server.TaskController.getProvided(taskname,username);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        lat = task.getLatitude();
        lng = task.getLongitude();
        LatLng location = new LatLng(lat,lng);
        googleMap.addMarker(new MarkerOptions().position(location).title("Location of the selected task"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));


    }
}
