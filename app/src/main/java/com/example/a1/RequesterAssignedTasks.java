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

import java.util.ArrayList;

import static com.example.a1.Status.ASSIGNED;

public class RequesterAssignedTasks extends AppCompatActivity {
    private Button saveButton;
    private Button backButton;
    Intent intent;
    private static String username;
    private static User user;
    private static Task oldTask;
    private static Task newTask;
    private static int id;


    /**Initialize the AssignedTasks array to store the tasks that the user has assigned
     *Initialize the TasksStatus array to store status of each task that the requster has assigned
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
        intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();
        getUserProfileTask.execute(username);
        try{
            user = getUserProfileTask.get();
        }catch(Exception e){
            Log.i("user doesn't exist","user doesn't exist");
        }
        //the following five  lines are added to test
        Task task= new Task("title1","user1","des1");
        user.getRequestedTasks().add(task);

        user.getRequestedTask(0).setAssigned();
        user.getRequestedTask(0).addBid(1);

        setupSaveButton();
        getAssignedTasks();

        ((TextView) findViewById(R.id.name)).setText(username);

        ListView listView = (ListView) findViewById(R.id.ListView_Task);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);




        //getAssignedTasks();


        //when item was click jump out a dialog that selec Done or Assigned
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long lid) {
                id = (int)lid;
                //old backup of the task
                oldTask = AssignedTasks.get( id);

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
        //updates the tasks to both task providers and requester

        //update for requester

        //get user form server
        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();

        getUserProfileTask.execute(username);
        try {
            user = getUserProfileTask.get();
            user.deleteRequestedTask(oldTask);
            user.requestTask(newTask);

        } catch (Exception e) {
            Log.i("user doesn't exist", "User does not exist!");
            // Add a notification
        }


        //update for provider

        //find provider form task, remove this task from his provided tasks.
        String providerName = newTask.getProviderName();
        User provider;
        if (newTask.getStatus() == Status.REQUESTED){

            //get provider from server
            getUserProfileTask.execute(providerName);
            try {
                provider = getUserProfileTask.get();
                provider.deleteProvidedTask(oldTask);

            } catch (Exception e) {
                Log.i("user doesn't exist", "User does not exist!");
                // Add a notification
            }

            //set provider to null
        }else{
            //get provider from server
            getUserProfileTask.execute(providerName);
            try {
                provider = getUserProfileTask.get();
                provider.deleteProvidedTask(oldTask);
                provider.provideTask(newTask);

            } catch (Exception e) {
                Log.i("user doesn't exist", "User does not exist!");
                // Add a notification
            }
        }

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
        for(Integer j = 0;j < allTasks.size(); j++){
            Task task = user.getRequestedTask(j);
            if(task.getStatus() == ASSIGNED){
                AssignedTasks.add(task);
                AssignedTasksTitle.add("Title:"+task.getTitle());
                Status status = task.getStatus();
                AssignedTasksStatus.add("Status:"+status.toString());
                AcceptedBids.add("Accepted bid:"+task.getLowestBid());  //this should get one bid of provider
                UserName.add("User name:"+username); //this should get providerusernam here.
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
            TextView textView_task=(TextView)view.findViewById(R.id.View_Task);
            TextView textView_username=(TextView)view.findViewById(R.id.View_User_Name);
            TextView textView_status=(TextView)view.findViewById(R.id.View_Status) ;
            TextView textView_acceptBid=(TextView)view.findViewById(R.id.View_Bid) ;


            //set text to textview
            textView_task.setText(AssignedTasksTitle.get(i));
            textView_username.setText(UserName.get(i));
            textView_status.setText(AssignedTasksStatus.get(i));
            textView_acceptBid.setText(AcceptedBids.get(i).toString());


            return view;
        }

        @Override

        public int getCount(){
            return AssignedTasks.size();
        }

    }

    /**
     * when save button is clicked jump from Requester's Assigned Task Screen to Requester Main Screen and saving changes.
     */
    private void setupSaveButton(){
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterAssignedTasks.this,RequesterMain.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }



}