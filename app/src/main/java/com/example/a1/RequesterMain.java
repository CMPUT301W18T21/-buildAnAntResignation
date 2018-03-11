package com.example.a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RequesterMain extends AppCompatActivity {

    private static ArrayList<String> tasksInfo = new ArrayList<>(0);
    private static ArrayAdapter<String> adapter;
    Button BiddedButton;
    ListView taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_main);
        setTitle("Requester Main Page");

        ((TextView) findViewById(R.id.username)).setText(MainActivity.getCurrentUser().getUsername());

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tasksInfo);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        /* Remove Later*/
        Task task = new Task("Task1", MainActivity.getCurrentUser().getUsername(),"Description of task");
        MainActivity.getCurrentUser().requestTask(task);

        displayTasks();
        setupListView();
        setupBiddedButton();

    }

    private void displayTasks(){
        ArrayList<Task> tasks = MainActivity.getCurrentUser().getRequestedTasks();
        for (Task task: tasks) {
            tasksInfo.add(task.getTitle() + "\n" + task.getStatus());
        }
    }

    public void onViewAssignedClick(View view){

    }
    public void onViewBiddedClick(View view){

    }
    public void onRequestClick(View view){

    }
    public void onTaskClick(View view){

    }
    private void setupBiddedButton() {
        /** when add button is clicked jump to addsubscription View
         */

        BiddedButton = (Button) findViewById(R.id.viewBiddedButton);
        BiddedButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterMain.this, RequesterBiddedTask.class);
                startActivityForResult(intent, 1);


            }
        });

    }
    private void setupListView() {
        /** when add button is clicked jump to addsubscription View
         */

        taskList = (ListView) findViewById(R.id.listView);
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(),TaskDetails.class);

                startActivityForResult(intent, 1);


            }
        });

    }

}
