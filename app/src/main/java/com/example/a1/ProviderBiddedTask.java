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

public class ProviderBiddedTask extends AppCompatActivity {

    private User user;
    private String username;

    private ArrayList<String> providerBiddedTasks = new ArrayList<String>(0);
    private ArrayList<String> providerBiddedTasksStatus = new ArrayList<String>(0);
    private ArrayList<Integer> lowestBids =new ArrayList<Integer>(0);
    private ArrayList<String> usernames =new ArrayList<String>(0);


    /**
     * @author: Yuan
     *
     */

    /**
     * this method set the contentview to a screen which shows all the bidded tasks bidded by the current provider
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        usernames.add("alex");
//        providerBiddedTasks.add("want");
//        providerBiddedTasksStatus.add("bidded");
//        lowestBids.add(1);

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

        Button viewAssigned = (Button) findViewById(R.id.back);
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
                providerBiddedTasks.add(singleTask.getTitle().toString());
                usernames.add(singleTask.getRequesterName().toString());
                providerBiddedTasksStatus.add(singleTask.getStatus().toString());
                lowestBids.add(singleTask.getLowestBid());

            }
        }
    }


    class CustomAdapter extends BaseAdapter {

        @Override

        public int getCount(){
            return providerBiddedTasks.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        /**
         * this method is for custom listview. in this listview, username,lowestbid,status,and task title will be shown for each task.
         * @param i
         * @param view
         * @param viewGroup
         * @return
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout2,null);
            TextView textView_task=(TextView)view.findViewById(R.id.textView_task);
            TextView textView_username=(TextView)view.findViewById(R.id.textView_username);
            TextView textView_lowestBid=(TextView)view.findViewById(R.id.textView_lowestBid);
            TextView textView_status=(TextView)view.findViewById(R.id.textView_status);

            //textView_username.setText(user.getName());
            textView_username.setText(usernames.get(i));
            textView_status.setText(providerBiddedTasksStatus.get(i));
            textView_lowestBid.setText(lowestBids.get(i).toString());
            textView_task.setText(providerBiddedTasks.get(i));



            return view;
        }



    }


}
