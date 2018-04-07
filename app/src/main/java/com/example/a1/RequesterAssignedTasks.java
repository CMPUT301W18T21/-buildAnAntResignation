package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.a1.Status.ASSIGNED;

public class RequesterAssignedTasks extends AppCompatActivity {

    private static String username;
    private static User user;
    private static Task oldTask;
    private static Task newTask;
    private static int id;


    /**Initialize the AssignedTasks array to store the tasks that the user has assigned
     *Initialize the TasksStatus array to store status of each task that the requester has assigned
     * Initialize the Accepted Array to store each task's accepted bid
     */

    private static ArrayList<Task> AssignedTasks = new ArrayList<>(0);
    private static ArrayList<String> AssignedTasksTitle = new ArrayList<>(0);
    private static ArrayList<String> AssignedTasksStatus = new ArrayList<>(0);
    private static ArrayList<String>AcceptedBids = new ArrayList<>(0);
    private static ArrayList<String>UserName =new ArrayList<>(0);



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
                id = (int)lid;
                //old backup of the task
                oldTask = AssignedTasks.get(id);

                //Call dialog to display detail
                DialogChangeStatus.setTask(AssignedTasks.get(id));
                Intent intent = new Intent(getApplicationContext(), DialogChangeStatus.class);
                startActivity(intent);

            }
        });
    }

    public static void UpdateTask(){
        AssignedTasks.set(id, DialogChangeStatus.getTask());
        newTask = AssignedTasks.get(id);
        getAssignedTasks();
    }


    /**
     * get tasks with status is Assigned , get status of the task, and the Bib made by specific provider, and provider username.
     */
    public static void getAssignedTasks(){
        ArrayList<Task> allTasks = user.getRequestedTasks();
        AssignedTasks.clear();
        AssignedTasksTitle.clear();
        AssignedTasksStatus.clear();
        AcceptedBids.clear();
        UserName.clear();
        for (Task task : allTasks){
            if(task.getStatus() == Status.ASSIGNED){
                AssignedTasks.add(task);
                AssignedTasksTitle.add("Title: "+task.getTitle());
                Status status = task.getStatus();
                AssignedTasksStatus.add("Status: "+status.toString());
                AcceptedBids.add("Accepted bid: "+task.getLowestBid());  //this should get one bid of provider
                UserName.add("User name: "+ task.getProviderName()); //this should get providerusernam here.
            }
        }
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
            ((TextView)view.findViewById(R.id.View_Task)).setText(AssignedTasksTitle.get(i));
            ((TextView)view.findViewById(R.id.View_User_Name)).setText(UserName.get(i));
            ((TextView)view.findViewById(R.id.View_Status)).setText(AssignedTasksStatus.get(i));
            ((TextView)view.findViewById(R.id.View_Bid)).setText(AcceptedBids.get(i).toString());

            return view;
        }

        @Override

        public int getCount(){
            return AssignedTasks.size();
        }

    }

    /**
     * when save button is clicked jump from Requester's Assigned Task Screen to Requester Main Screen and saving changes.
     * @param view The caller view.
     */
    public void onSaveClick(View view){
        Intent intent = new Intent(RequesterAssignedTasks.this,RequesterMain.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }



}