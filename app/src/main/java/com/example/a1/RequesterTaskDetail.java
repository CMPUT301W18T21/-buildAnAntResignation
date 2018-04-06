package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private Button DeleteButton;

    private ListView listView;
    private EditText viewtitle;
    private TextView viewstatus;
    private TextView viewlowsetbid;
    private  EditText viewdescription;

    private static Task task;
    private static String username;
    private static ArrayAdapter<Integer> adapter;
    private static ArrayList<Integer> bids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_task_detail);
        Intent intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        /* debugging */

        bids = new ArrayList<>(0);
        bids.add(5);
        bids.add(9);
        bids.add(8);
        task = new Task("hi","nihao","whatsup","location", Status.REQUESTED,bids);



        viewtitle =(EditText)findViewById(R.id.ViewTitle);
        viewstatus =(TextView)findViewById(R.id.ViewStatus);
        viewlowsetbid = (TextView)findViewById(R.id.ViewLowsetBid);
        viewdescription =(EditText) findViewById(R.id.Viewdescription) ;
        listView =(ListView)findViewById(R.id.bidlist);

        //put Bids in bidlist
        adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,bids);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        //set text for textview
        ((TextView)(findViewById(R.id.name))).setText(username);
        viewtitle.setText(task.getTitle());
        viewstatus.setText("Status: " + String.valueOf(task.getStatus()));
        viewlowsetbid.setText("Lowest bid: $" + String.valueOf(task.getLowestBid()));
        viewdescription.setText(task.getDescription());


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
    }

    /**
     * Sets the task who's info will be displayed
     * @param task Task to be displayed.
     */
    public static void setTask(Task task){
        RequesterTaskDetail.task = task;
    }


    /**
     * when Addphoto buton is clicked jump from Requester's  Task Detail Screen to AddPhoto screen.
     */
    private void setupAddPhotoButton(){
        AddPhoto = (Button) findViewById(R.id.AddPhoto_B);
        AddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterTaskDetail.this, com.example.a1.AddPhoto.class);
                startActivity(intent);
            }
        });
    }

    /**
     * When the delete button is clicked, the viewed task is deleted
     * @param view The caller view.
     */
    public void onDeleteClick(View view){

        Server.TaskController.delete(task);
        finish();
    }

    /**
     * When save button is clicked, save the changes to the task and goes back to Requester Main Screen.
     */
    public void onSaveClick(View view){

        String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        String description = ((EditText) findViewById(R.id.descriptionEditText)).getText().toString();

        Task newTask = task;
        newTask.setTitle(title);
        newTask.setDescription(description);

        Server.TaskController.edit(task,newTask);

        Intent intent = new Intent(RequesterTaskDetail.this,RequesterMain.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }

    /**
     * when AddLocation button is clicked jump from Requester's  Task Detail Screen to AddPhoto screen.
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