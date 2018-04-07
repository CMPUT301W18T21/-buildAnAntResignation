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

    Button btnShowCoord;
    EditText edtAddress;
    TextView txtCoord;
    private static Task task;
    private static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        btnShowCoord = (Button)findViewById(R.id.btnShowCoordinates);
        edtAddress = (EditText)findViewById(R.id.edtAddress);
        txtCoord = (TextView)findViewById(R.id.txtCoordinates);

        btnShowCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCoordinates().execute(edtAddress.getText().toString().replace(" ","+"));
            }
        });

    }

    public static void setTask(Task task){
        AddLocation.task = task;
    }


    private class GetCoordinates extends AsyncTask<String,Void,String> {
        ProgressDialog dialog = new ProgressDialog(AddLocation.this);

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

                float latitude = Float.parseFloat(lat);
                float longitude = Float.parseFloat(lng);
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