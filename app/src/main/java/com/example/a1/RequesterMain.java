package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RequesterMain extends AppCompatActivity {

    private static ArrayList<String> tasksInfo = new ArrayList<>(0);
    private static ArrayAdapter<String> adapter;


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
}
