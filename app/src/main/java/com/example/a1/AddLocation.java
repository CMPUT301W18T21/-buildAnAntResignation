/*
 * AddLocation
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** this is to add location of a task **/

/**
 * Created by k-h on 2018-04-05.
 */


public class AddLocation extends AppCompatActivity{

    private TextView txtCoord;


    private static Task task;
    private static String username;
    //private String address;

    /**
     * under onCreate, there is a onclick method which is invoked when the user press the button. This button captures the input address string
     * and store it.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        Intent intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        Button btnShowCoord = (Button)findViewById(R.id.btnShowCoordinates);
        txtCoord = (TextView)findViewById(R.id.txtCoordinates);

        btnShowCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtAddress = (EditText)findViewById(R.id.edtAddress);
                new GetCoordinates().execute(edtAddress.getText().toString().replace(" ","+"));
            }
        });

    }

    /**
     * setTask allows this class to capture the exact task this class is looking for.
     * @param task
     */
    public static void setTask(Task task){
        AddLocation.task = task;
    }


    /**
     * once the input address string is captured, it is then converted into lat and long, and then these two double will be stored in
     * the task.
     */
    private class GetCoordinates extends AsyncTask<String,Void,String> {
        private ProgressDialog dialog = new ProgressDialog(AddLocation.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait....");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String response;
            try{
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                response = http.getHTTPData(url);
                return response;
            }
            catch (Exception e){
                Log.d("ONDoInBackGround","there is some error occurred in doInBackGround");
            }
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);

                String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lat").toString();
                String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location").get("lng").toString();

                txtCoord.setText(String.format("Coordinates : %s / %s ",lat,lng));
                /***********************************/
                Task newTask;
                try{
                    newTask = (Task) task.clone();
                }catch (CloneNotSupportedException  e){
                    Toast.makeText(AddLocation.this, "User not found!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double latitude = Double.parseDouble(lat);
                double longitude = Double.parseDouble(lng);
                //newTask.setLocation(address);
                newTask.setLatitude(latitude);
                newTask.setLongitude(longitude);
                Server.TaskController.edit(task,newTask);
                /**********************************/


                if(dialog.isShowing())
                    dialog.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}