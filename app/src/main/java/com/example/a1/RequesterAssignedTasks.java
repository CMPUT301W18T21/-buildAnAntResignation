package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private Button saveButton;
    private Button backButton;

    private User user;
    /**Initialize the AssignedTasks array to store the tasks that the user has assigned
     *Initialize the TasksStatus array to store status of each task that the requster has assigned
     * Initialize the Accepted Array to store each task's accepted bid
     */

    ArrayList<String> AssignedTasks = new ArrayList<>(0);
    ArrayList<String> AssignedTasksStatus = new ArrayList<>(0);
    ArrayList<Integer>AcceptedBids = new ArrayList<>(0);
    ArrayList<String>UserName =new ArrayList<>(0);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_assigned_tasks);
        setupBackButton();
        setupSaveButton();
        getAssignedTasks();

        ((TextView) findViewById(R.id.name)).setText(MainActivity.getCurrentUser().getUsername());

        ListView listView = (ListView) findViewById(R.id.ListView_Task);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        //test: add data to Arraylist

        AssignedTasksStatus.add("assigned");
        AssignedTasksStatus.add("assigned");
        AssignedTasksStatus.add("assigned");
        AcceptedBids.add(111);
        AcceptedBids.add(222);
        AcceptedBids.add(333);
        AssignedTasks.add("task1");
        AssignedTasks.add("task2");
        AssignedTasks.add("task3");
        UserName.add("hao");
        UserName.add("liu");
        UserName.add("jie");


        getAssignedTasks();


        /**
         mTaskList = new ArrayList<>();
         Toast.makeText(getApplicationContext(),"111 =" ,Toast.LENGTH_SHORT).show();

         //Init adapter
         adapter = new RequesterAssignedListviewAdapter(getApplicationContext(),mTaskList);
         listView.setAdapter(adapter);


         Toast.makeText(getApplicationContext(),"222=" ,Toast.LENGTH_SHORT).show();
         */


        //when item was click jump out a dialog that selec Done or Assigned
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Toast.makeText(getApplicationContext(), "Cliacked task name =" + view.getTag(), Toast.LENGTH_SHORT).show();
                //Call dialog to display detail

                Intent intent = new Intent(getApplicationContext(),DialogChangeStatus.class);

                startActivity(intent);


            }
        });
    }


    /**
     * get tasks with status is Assigned , get status of the task, and the Bib made by specific provider, and provider username.
     */
    public void getAssignedTasks(){
        ArrayList<Task> AllTasks = MainActivity.getCurrentUser().getRequestedTasks();
        for(Integer j=0;j<AllTasks.size();j++){
            Task task = MainActivity.getCurrentUser().getRequestedTask(j);
            if(task.getStatus() == ASSIGNED){
                AssignedTasks.add(task.getTitle());
                String status = task.getStatus().toString();
                AssignedTasksStatus.add(status);
                AcceptedBids.add(task.getLowestBid());  //this should get one bid of provider
                UserName.add(task.getUsername()); //this should get providerusernam here.
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
            textView_task.setText(AssignedTasks.get(i));
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
                startActivity(intent);
            }
        });
    }

    /**
     * when back button is clicked jump from Requester's Assigned Task Screen to Requester Main Screen without saving any changes.
     */
    private void setupBackButton(){
        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequesterAssignedTasks.this,RequesterMain.class);
                startActivity(intent);
            }
        });
    }
}