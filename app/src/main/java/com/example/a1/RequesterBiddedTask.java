package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.a1.Status.BIDDED;

/**
 * @author Yuan
 */

public class RequesterBiddedTask extends AppCompatActivity {

    Button BackButton;
    ArrayList<String> BiddedTasks = new ArrayList<>(0);
    private static User user;
    private String username;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_bidded_task);
        intent = new Intent();
        intent = getIntent();
        username = intent.getStringExtra("username");

        setTitle("Bidded Tasks");

        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();
        getUserProfileTask.execute(username);
        try{
            user = getUserProfileTask.get();
        }catch(Exception e){
            Log.i("user doesn't exist","user doesn't exist");
        }
        //the following six lines are added to test
        Task task1= new Task("title1","user1","des1");
        user.getRequestedTasks().add(task1);
        Task task2= new Task("title2","user1","des2");
        user.getRequestedTasks().add(task2);

        user.getRequestedTask(0).setBided();

        user.getRequestedTask(1).setBided();

//****
        getBiddedTasks();

        ListView listView=(ListView)findViewById(R.id.viewBiddedTask);
        TextView textView=(TextView)findViewById(R.id.username);
        textView.setText(username);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }


    public void getBiddedTasks(){

        ArrayList<Task> AllTasks= user.getRequestedTasks();
        for(Integer j=0;j<AllTasks.size();j++){
            Task task= user.getRequestedTask(j);
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
            textView_username.setText(username);
            textView_task.setText(BiddedTasks.get(i));

            return view;
        }

        @Override

        public int getCount(){
            return BiddedTasks.size();
        }

    }



}

