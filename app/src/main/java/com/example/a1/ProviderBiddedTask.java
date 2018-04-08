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

import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProviderBiddedTask extends AppCompatActivity {

    private User user;
    private Button viewAssigned;
    private String username;
    /**
     * @author Yuan
     */

    /**
     * Initialize the ProviderBiddedTasks array to store the tasks that the user has bidded
     * Initialize the ProviderBiddedtasksStatus array to store status of each task that the provider has bidded
     * Initialize the LowestBids Array to store each task's lowest bid
     *
     */

    ArrayList<String> ProviderBiddedTasks = new ArrayList<String>(0);
    ArrayList<String> ProviderBiddedTasksStatus = new ArrayList<String>(0);
    ArrayList<Integer>LowestBids=new ArrayList<Integer>(0);
    ArrayList<String>UserName=new ArrayList<String>(0);





    /**
     * @author: Yuan
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        UserName.add("alex");
//        ProviderBiddedTasks.add("want");
//        ProviderBiddedTasksStatus.add("bidded");
//        LowestBids.add(1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_bidded_task);
        Intent intent = getIntent();
        setTitle("My Bidded Task");
        username = intent.getStringExtra("username");
        Log.i("want see the username",username);
        ListView listView=(ListView)findViewById(R.id.Listview_BidList);
        getTasksAttri(username);
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
        setupBackButton();



    }



    private void setupBackButton() {
        /** when back button is clicked jump back to provider main page
         */

        viewAssigned = (Button) findViewById(R.id.back);
        viewAssigned.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //need change , the second activity is not the right destination
                Intent intent = new Intent(ProviderBiddedTask.this, ProviderAssignedTasks.class);
                intent.putExtra("username",username);
                startActivityForResult(intent, 1);


            }
        });

    }
    /**
     * this method will create an array of staus of tasks that provider has bidded
     * and an array of lowest bids of each task
     */


    private void getTasksAttri(String username){

        user = Server.UserController.get(username);

        ArrayList<Task> AllTasks = new ArrayList<Task>();

        Log.i("PRINT USERJJAHDKJAS",user.getUsername().toString());


        if (user.getBiddedTasks() == null) {
            Toast.makeText(this,"No bidded task exist",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            AllTasks = user.getBiddedTasks();
            for (Task singleTask : AllTasks) {
                /**
                 * the status here should be String since it will be set as the content of textView
                 */
                ProviderBiddedTasks.add(singleTask.getTitle().toString());
                UserName.add(singleTask.getRequesterName().toString());
                ProviderBiddedTasksStatus.add(singleTask.getStatus().toString());
                LowestBids.add(singleTask.getLowestBid());

            }
        }
    }





    class CustomAdapter extends BaseAdapter {

        @Override

        public int getCount(){
            return ProviderBiddedTasks.size();
        }
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
            view = getLayoutInflater().inflate(R.layout.customlayout2,null);
            TextView textView_task=(TextView)view.findViewById(R.id.textView_task);
            TextView textView_username=(TextView)view.findViewById(R.id.textView_username);
            TextView textView_lowestBid=(TextView)view.findViewById(R.id.textView_lowestBid);
            TextView textView_status=(TextView)view.findViewById(R.id.textView_status);

            //textView_username.setText(user.getName());
            textView_username.setText(UserName.get(i));
            textView_status.setText(ProviderBiddedTasksStatus.get(i));
            textView_lowestBid.setText(LowestBids.get(i).toString());
            textView_task.setText(ProviderBiddedTasks.get(i));



            return view;
        }



    }


}
