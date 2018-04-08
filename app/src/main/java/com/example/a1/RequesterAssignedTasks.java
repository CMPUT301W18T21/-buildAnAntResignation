package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RequesterAssignedTasks extends AppCompatActivity {

    private static String username;
    private static User user;

    private static ArrayList<Task> assignedTasks = new ArrayList<>(0);
    private static ArrayList<String> assignedTasksTitle = new ArrayList<>(0);
    private static ArrayList<String> assignedTasksStatus = new ArrayList<>(0);
    private static ArrayList<String> acceptedBids = new ArrayList<>(0);
    private static ArrayList<String> usernames =new ArrayList<>(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_assigned_tasks);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        user = Server.UserController.get(username);
        if (user == null){
            Toast.makeText(RequesterAssignedTasks.this, "User not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        getAssignedTasks();

        ((TextView) findViewById(R.id.name)).setText(username);

        ListView listView = (ListView) findViewById(R.id.ListView_Task);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


        //when item was click jump out a dialog that select Done or Assigned
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long lid) {

                //Call dialog to display detail
                DialogChangeStatus.setTask(assignedTasks.get((int)lid));
                Intent intent = new Intent(getApplicationContext(), DialogChangeStatus.class);
                startActivity(intent);

            }
        });
    }


    /**
     * get tasks with status is Assigned , get status of the task, and the Bib made by specific provider, and provider username.
     */
    public void getAssignedTasks(){
        ArrayList<Task> allTasks = user.getRequestedTasks();



        assignedTasks.clear();
        assignedTasksTitle.clear();
        assignedTasksStatus.clear();
        acceptedBids.clear();
        usernames.clear();
        for (Task task : allTasks){
            if(task.getStatus() == Status.ASSIGNED){
                assignedTasks.add(task);
                assignedTasksTitle.add("Title: "+task.getTitle());
                assignedTasksStatus.add("Status: "+task.getStatus().toString());
                acceptedBids.add("Accepted bid: $"+task.getLowestBid());
                usernames.add("Username: "+ task.getProviderName());
            }
        }

        ListView listView = (ListView) findViewById(R.id.ListView_Task);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    public void onRefreshClick(View view){
        getAssignedTasks();
    }


    class CustomAdapter extends BaseAdapter{
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.requester_assigned_task_ifo,null);
            //set text to textview
            ((TextView)view.findViewById(R.id.View_Task)).setText(assignedTasksTitle.get(i));
            ((TextView)view.findViewById(R.id.View_User_Name)).setText(usernames.get(i));
            ((TextView)view.findViewById(R.id.View_Status)).setText(assignedTasksStatus.get(i));
            ((TextView)view.findViewById(R.id.View_Bid)).setText(acceptedBids.get(i).toString());

            return view;
        }

        @Override

        public int getCount(){
            return assignedTasks.size();
        }

    }


}