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
import android.widget.Toast;

import java.util.ArrayList;

public class RequesterTaskDetail extends AppCompatActivity {

    private Task task;
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
        final Integer taskIndex = intent.getIntExtra("taskIndex",999);

        Log.d("Give me taskIndex",taskIndex.toString());

        User user = Server.UserController.get(username);
        task = user.getRequestedTask(taskIndex);




        EditText viewtitle =(EditText)findViewById(R.id.titleEditText);
        TextView viewstatus =(TextView)findViewById(R.id.ViewStatus);
        TextView viewlowsetbid = (TextView)findViewById(R.id.ViewLowsetBid);
        EditText viewdescription =(EditText) findViewById(R.id.descriptionEditText) ;
        ListView listView =(ListView)findViewById(R.id.bidlist);

        //put Bids in bidlist
        adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, task.getBids());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        //set text for textview
        ((TextView)(findViewById(R.id.name))).setText(username);
        viewtitle.setText(task.getTitle());
        viewstatus.setText("Status: " + String.valueOf(task.getStatus()));
        if(task.getLowestBid() == -1)
            viewlowsetbid.setText("Lowest bid: None");
        else
            viewlowsetbid.setText("Lowest bid: $" + String.valueOf(task.getLowestBid()));
        viewdescription.setText(task.getDescription());


        //when item was click jump out a dialog that accept and decline

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Bid bid = (Bid)adapter.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),DialogSelectBid.class);
           //     DialogSelectBid.setTask2(task);
                //position of bidder
                intent.putExtra("position",position);
                intent.putExtra("username",username);
                intent.putExtra("taskIndex",taskIndex);
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
//    public static void setTask(Task task){
//        RequesterTaskDetail.task = task;
//    }



    /**
     * when Addphoto buton is clicked jump from Requester's  Task Detail Screen to AddPhoto screen.
     */
    private void setupAddPhotoButton(){
        Button addPhoto = (Button) findViewById(R.id.AddPhoto_B);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterTaskDetail.this, AddPhoto.class);
                AddPhoto.setTask(task);
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
     * @param view The caller view.
     */
    public void onSaveClick(View view){

        String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        String description = ((EditText) findViewById(R.id.descriptionEditText)).getText().toString();
        Task newTask;
        try{
            newTask = (Task) task.clone();
        }catch (CloneNotSupportedException  e){
            Toast.makeText(RequesterTaskDetail.this, "User not found!", Toast.LENGTH_SHORT).show();
            return;
        }

        newTask.setTitle(title);
        newTask.setDescription(description);
        Server.TaskController.edit(task,newTask);

        finish();
    }

    /**
     * when AddLocation button is clicked jump from Requester's  Task Detail Screen to AddPhoto screen.
     */
    private void setupAddLocationButton(){
        ((Button) findViewById(R.id.AddLocation_B)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task.getLongitude()!= null){
                    Toast.makeText(RequesterTaskDetail.this, "The location of the task is already added.", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent intent = new Intent(RequesterTaskDetail.this,AddLocation.class);

                    AddLocation.setTask(task);
                    intent.putExtra("username",username);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }





}