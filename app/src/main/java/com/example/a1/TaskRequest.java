package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TaskRequest extends AppCompatActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_request);
        setTitle("Request Task");
        ((TextView) findViewById(R.id.username)).setText(MainActivity.getCurrentUser().getUsername());
    }

    public void onRequestClick(View view){
        String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        String description = ((EditText) findViewById(R.id.descriptionEditText)).getText().toString();
        if (title.length() <= 30 && description.length() <= 300) {
            task = new Task(title, MainActivity.getCurrentUser().getUsername(), description);
            MainActivity.getCurrentUser().requestTask(task);
            finish();
        }
    }
}
