package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.a1.Status.BIDDED;

/**
 * @author Yuan
 */

public class RequesterBiddedTask extends AppCompatActivity {
    private User user;
    Button BackButton;
    ArrayList<String> BiddedTasks = new ArrayList<>(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_bidded_task);
        setupBackButton();
        getBiddedTasks();


        ListView listView=(ListView)findViewById(R.id.viewBiddedTask);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }


    private void setupBackButton() {
        /** when add button is clicked jump to addsubscription View
         */

        BackButton = (Button) findViewById(R.id.back);
        BackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterBiddedTask.this, RequesterMain.class);
                startActivityForResult(intent, 1);


            }
        });

    }

    public void getBiddedTasks(){

        ArrayList<Task> AllTasks= MainActivity.getCurrentUser().getRequestedTasks();
        for(Integer j=0;j<AllTasks.size();j++){
            Task task=MainActivity.getCurrentUser().getRequestedTask(j);
            if (task.getStatus()==BIDDED){
                BiddedTasks.add(task.getTitle());
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
            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            TextView textView_task=(TextView)view.findViewById(R.id.textView_task);
            TextView textView_username=(TextView)view.findViewById(R.id.textView_username);
            //textView_username.setText(user.getName());
            textView_task.setText(BiddedTasks.get(i));

            return view;
        }

        @Override

        public int getCount(){
            return BiddedTasks.size();
        }

    }



}

