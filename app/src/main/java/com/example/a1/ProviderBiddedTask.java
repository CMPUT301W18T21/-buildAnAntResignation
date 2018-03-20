package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProviderBiddedTask extends AppCompatActivity {

    private User user;
    Button BackButton;
    /**
     * @author Yuan
     */

    /**
     * Initialize the ProviderBiddedTasks array to store the tasks that the user has bidded
     * Initialize the ProviderBiddedtasksStatus array to store status of each task that the provider has bidded
     * Initialize the LowestBids Array to store each task's lowest bid
     *
     */

    ArrayList<String> ProviderBiddedTasks = new ArrayList<>(0);
    ArrayList<String> ProviderBiddedTasksStatus = new ArrayList<>(0);
    ArrayList<Integer>LowestBids=new ArrayList<>(0);
    ArrayList<String>UserName=new ArrayList<>(0);








    /**
     * @author: Yuan
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_bidded_task);
        ListView listView=(ListView)findViewById(R.id.Listview_BidList);

        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
        setupBackButton();
        getTasksAttri();



    }



    private void setupBackButton() {
        /** when back button is clicked jump back to provider main page
         */

        BackButton = (Button) findViewById(R.id.back);
        BackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //need change , the second activity is not the right destination
                Intent intent = new Intent(ProviderBiddedTask.this, RequesterMain.class);
                startActivityForResult(intent, 1);


            }
        });

    }
    /**
     * this method will create an array of staus of tasks that provider has bidded
     * and an array of lowest bids of each task
     */


    private void getTasksAttri(){

        ArrayList<Task> AllTasks= MainActivity.getCurrentUser().getProvidedTasks();
        for(Integer j=0;j<AllTasks.size();j++){
            Task task= MainActivity.getCurrentUser().getRequestedTask(j);
            /**
             * the status here should be String since it will be set as the content of textView
             */
            String status=task.getStatus().toString();
            ProviderBiddedTasksStatus.add(status);
            LowestBids.add(task.getLowestBid());
            UserName.add(task.getUsername());

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
