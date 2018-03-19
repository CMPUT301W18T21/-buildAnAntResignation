package com.example.a1;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RequesterTaskDetail extends AppCompatActivity {
    private Button saveButton;
    private Button backButton;
    private Button AddPhoto;
    private Button AddLocation;

    private ListView listView;
    private TextView viewtitle;
    private TextView viewstatus;
    private TextView viewlowsetbid;
    private  TextView viewdescription;

    private static Task task;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_task_detail);
        /* debugging */

        bids = new ArrayList<>(0);
        bids.add(5);
        bids.add(9);
        bids.add(8);
        task = new Task("hi","nihao","whatsup","location",Status.REQUESTED,bids);

        viewtitle =(TextView)findViewById(R.id.ViewTitle);
        viewstatus =(TextView)findViewById(R.id.ViewStatus);
        viewlowsetbid = (TextView)findViewById(R.id.ViewLowsetBid);
        viewdescription =(TextView) findViewById(R.id.Viewdescription) ;
        listView =(ListView)findViewById(R.id.bidlist);

        //put Bids in bidlist
        adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,bids);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        //set text for textview
        viewtitle.setText(task.getTitle());
        viewstatus.setText(String.valueOf(task.getStatus()));
        //viewlowsetbid.setText(task.getLowestBid().toString());/**lowestbid did not work*/
        viewdescription.setText(task.getDescription());







        //        Elastic.GetallTaskByUsername alltask= new Elastic.GetallTaskByUsername();
        //        alltask.excute(key);
        //        try{
        //            taskArrayList=alltask.get();
        //            Log.e("Task","get");}
        //        catch (Exception e){
        //            Log.e("Task","not get");
        //
        //
        //        }
        //        ArrayAdapter<Task> adapter=new ArrayAdapter<Task>(RequesterTaskDetail.this,R.layout.support_simple_spinner_dropdown_item,taskArrayList);
        //        listView.setAdapter(adapter);
        //        datachanged:
        //        adapter.notifyDataSetChanged();
        //        listView.setAdapter(adapter);


        //when item was click jump out a dialog that accept and decline

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Bid bid = (Bid)adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),DialogSelectBid.class);

                startActivity(intent);
            }
        });
        setupAddLocationButton();
        setupAddPhotoButton();
        setupBackButton();
        setupSaveButton();
    }

    /**
     * when save button is clicked jump from Requester's  Task Detail Screen to Requester Main Screen and saving  changes.
     */
    private void setupSaveButton(){
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterTaskDetail.this,RequesterMain.class);
                startActivity(intent);
            }
        });
    }

    /**
     * when back button is clicked jump from Requester's  Task Detail Screen to Requester Main Screen without saving any changes.
     */
    private void setupBackButton(){
        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterTaskDetail.this,RequesterMain.class);
                startActivity(intent);
            }
        });
    }

    /**
     * when Addphoto buton is clicked jump from Requester's  Task Detail Screen to AddPhoto screen.
     */
    private void setupAddPhotoButton(){
        AddPhoto = (Button) findViewById(R.id.AddPhoto_B);
        AddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterTaskDetail.this,AddPhoto.class);
                startActivity(intent);
            }
        });
    }

    /**
     * when AddLocation buton is clicked jump from Requester's  Task Detail Screen to AddPhoto screen.
     */
    private void setupAddLocationButton(){
        AddLocation = (Button) findViewById(R.id.AddLocation_B);
        AddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RequesterTaskDetail.this,AddLocation.class);
                //startActivity(intent);
            }
        });
    }





}
