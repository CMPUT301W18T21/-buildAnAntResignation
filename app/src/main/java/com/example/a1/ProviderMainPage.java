/*
 * ProviderMainPage
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;


/**
 * This activity branches out into all other Provider use cases.
 * From here one can search and bid on tasks, as well as view their assigned tasks.
 * The map view is also accessible from here.
 */
public class ProviderMainPage extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private EditText searchBox;
    private String keyword;
    private String currentUser;
    private ArrayList<User> users;

    private ArrayList<String> name_test = new ArrayList<>();
    private ArrayList<String> task_test = new ArrayList<>();
    private ArrayList<String> status_test = new ArrayList<>();
    private ArrayList<String> lowest_test = new ArrayList<>();
    private ArrayList<User> matchUsers = new ArrayList<>();


    /**
     * a method that execute every time the activity is shown.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main_page);
        Intent intent = getIntent();
        currentUser = intent.getStringExtra("username");
        final String username = intent.getStringExtra("username");

        ListView showContent = (ListView) findViewById(R.id.p_assigned_list);


        final CustomAdapter customAdapter = new CustomAdapter(this,boundinfo());
        showContent.setAdapter(customAdapter);

        /**
         */
        /**
         * once the button is clicked, it goes to ProviderBiddedTask to view all the tasks bidded by this provider
         */
        Button myTask = (Button) findViewById(R.id.myTask);
        myTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProviderMainPage.this,ProviderBiddedTask.class);
                intent.putExtra("username",username);
                startActivity(intent);
                //show_content(showContent);
            }
        });

        //example of query the taskname  on ES server

        //{
//        "query":{
//        "match":{
//        "requestedTasks.title":{
//        "query":keyword that u want to search,
//        "opeartor":"and"
//        }
//        }
//        }
//        }
        /**
         * this is to handle searching. Once the user click the search button, result will be displayed on the screen.
         */
        Button searchButton = (Button) findViewById(R.id.SearchButton);
        searchBox = (EditText) findViewById(R.id.SearchBox);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                keyword = searchBox.getText().toString();
                name_test.clear();
                task_test.clear();
                lowest_test.clear();
                status_test.clear();
                matchUsers.clear();
                String search_query = "{\"query\":{\"match\":{\"requestedTasks.title\":{\"query\":\""+keyword+"\",\"operator\":\""+"and"+"\"}}}}";

                UserElasticSearchController.QueryTask queryTaskName = new UserElasticSearchController.QueryTask();
                queryTaskName.execute(search_query);

                try {
                    users = queryTaskName.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.i("print query", search_query);
                Log.i("printUser",users.toString());


                for (User taskuser : users){

                    //we dont want our own tasks to appear here.
                    if(taskuser.getUsername().equals(User.getCurrentUser()))
                        continue;

                    ArrayList<Task> eachRequestedTask = taskuser.getRequestedTasks();

                    for (Task eachTask: eachRequestedTask){
                        String requestedTaskTitle = eachTask.getTitle();
                        if (conditionCheck(keyword,requestedTaskTitle,eachTask.getStatus().toString())) {
                            name_test.add(taskuser.getUsername());
                            task_test.add(requestedTaskTitle);
                            status_test.add(eachTask.getStatus().toString());
                            if(eachTask.getLowestBid() == -1){
                                lowest_test.add("None");
                            }else{
                                lowest_test.add(Integer.toString(eachTask.getLowestBid()));
                            }
                            matchUsers.add(taskuser);

                        }

                    }


                }

                Log.i("query",search_query.toString());
                Log.i("name",name_test.toString());
                Log.i("title",task_test.toString());
                Log.i("status",status_test.toString());
                Log.i("lowestbid",lowest_test.toString());

                ArrayList<ProviderAdaptInfo> tmplist = boundinfo();
                customAdapter.updateListView(tmplist);





            }
        });



        /******************************************* added by JiaHong *************************************************************/
        if(isServicesOK()){
            init();
        }
    }


    /**
     * once the button is clicked, it goes to viewOnMap which shows all the tasks that are within 5km of the current location
     */
    private void init(){
        Button btnMap = (Button) findViewById(R.id.viewOnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProviderMainPage.this, ShowWithin5kmMapActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * this method is to check whether google services is ok, in other words, this method checks status of the google services.
     * @return
     */
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ProviderMainPage.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(ProviderMainPage.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    /**
     * this method is to store the information of each task.
     * @return An array of ProviderAdaptInfo, which is used to dispaly the tasks in the list view.
     */
    private ArrayList<ProviderAdaptInfo> boundinfo()
    {
        ArrayList<ProviderAdaptInfo> providerAdaptInfos = new ArrayList<ProviderAdaptInfo>();
        ProviderAdaptInfo p;
        if (name_test.size()>0){
            for (int i = 0; i< name_test.size();i++){
                p = new ProviderAdaptInfo(name_test.get(i), task_test.get(i), status_test.get(i), lowest_test.get(i));
                providerAdaptInfos.add(p);
            }
            return providerAdaptInfos;
        } else {

            return providerAdaptInfos;
        }

    }

    /**
     * checking whether the condition is met
     * @param keyword
     * @param title
     * @param status
     * @return
     */
    private boolean conditionCheck(String keyword,String title,String status){
        Boolean find = true;
        if (keyword == null){
            return false;
        } else {
            String[] splited = keyword.split("\\s+");
            for(int i = 0;i<splited.length;i++){
                Boolean singleFind = title.contains(splited[i]);
                find = find & singleFind;
            }
        }
        Boolean statusBool = status.contains("REQUESTED") || status.contains("BIDDED");

        find = find & statusBool;

        return find;
    }


    /**
     * A custom adapter for viewing Task information.
     */
    class CustomAdapter extends BaseAdapter   { //implements Filterable

        Context c;
        ArrayList<ProviderAdaptInfo> providerinfos;
        LayoutInflater inflater;
       // CustomFilter filter;
        ArrayList<ProviderAdaptInfo> filterList;

        //custom adapter constructor

        /**
         * this is the custom listview adapter
         * @param ctx
         * @param providerinfos
         */

        public CustomAdapter(Context ctx, ArrayList<ProviderAdaptInfo> providerinfos) {
            this.c = ctx;
            this.providerinfos = providerinfos;
            this.filterList = providerinfos;
        }

        @Override
        public int getCount() {
            return providerinfos.size();
        }

        @Override
        public Object getItem(int pos) {
            return providerinfos.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(final int pos, View convertView, ViewGroup parent) {

            if (inflater == null) {
                inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.custom_pp, null);

            }



            pmpViewHolder holder = new pmpViewHolder(convertView);


            holder.textView_task.setText(providerinfos.get(pos).getTask());
            holder.textView_username.setText(providerinfos.get(pos).getName());
            holder.textView_status.setText(providerinfos.get(pos).getStatus());
            holder.textView_lowestBid.setText(providerinfos.get(pos).getLowestbid().toString());


            holder.setItemClickListener(new pmpItemClickListener() {
                                            @Override
                                            public void onItemClick(View view) {
                                                Intent intent = new Intent(ProviderMainPage.this, ProviderBidTask.class);
                                                intent.putExtra("taskname",providerinfos.get(pos).getTask());
                                                intent.putExtra("username",matchUsers.get(pos).getUsername());
                                                intent.putExtra("currentuser",currentUser);
                                                startActivity(intent);

                                            }

                                        }

            );

            return convertView;
        }
        public void updateListView(ArrayList<ProviderAdaptInfo> newlist){
            providerinfos.clear();
            providerinfos.addAll(newlist);
            this.notifyDataSetChanged();
        }
    }
}






