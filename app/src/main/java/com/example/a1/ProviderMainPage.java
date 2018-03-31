package com.example.a1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProviderMainPage extends AppCompatActivity {

    private ListView showContent;
    private SearchView keyWords;
    private Button myTask;
    private Button viewOnMap;




    String[] name_test = {"apple", "alpha", "bad", "battle", "cover"};
    String [] task_test = {"task1","task2","task3","task4","task5"};
    String [] status_test = {"On","off","on","on","off"};
    int [] lowest_test = {1,2,3,4,5};

    //ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main_page);
        showContent = (ListView) findViewById(R.id.listView);
        keyWords = (SearchView) findViewById(R.id.searchView);
        final CustomAdapter customAdapter = new CustomAdapter(this,boundinfo());
        showContent.setAdapter(customAdapter);


        keyWords.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                customAdapter.getFilter().filter(newText);


                return false;
            }
        });

/**
 * miss the itemonlclicked
 */

        myTask = (Button) findViewById(R.id.myTask);
        myTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProviderMainPage.this,ProviderBiddedTask.class);
                startActivity(intent);
                //show_content(showContent);
            }
        });
        viewOnMap = (Button) findViewById(R.id.viewOnMap);
        viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map_handler();


            }
        });


    }

    private ArrayList<ProviderAdaptInfo> boundinfo()
    {
        ArrayList<ProviderAdaptInfo> providerAdaptInfos = new ArrayList<ProviderAdaptInfo>();
        ProviderAdaptInfo p;
        for (int i = 0; i< name_test.length;i++){
            p = new ProviderAdaptInfo(name_test[i],task_test[i],status_test[i],lowest_test[i]);
            providerAdaptInfos.add(p);
        }
        return providerAdaptInfos;
    }



    public void map_handler() {

        /* this part should handle google map api connection */

    }
    class CustomAdapter extends BaseAdapter implements Filterable {

        Context c;
        ArrayList<ProviderAdaptInfo> providerinfos;
        LayoutInflater inflater;
        CustomFilter filter;
        ArrayList<ProviderAdaptInfo> filterList;

        //custom adapter constructor

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
                                                Intent intent = new Intent(ProviderMainPage.this, Provider_bid_task.class);
                                                startActivity(intent);
                                                Toast.makeText(c, providerinfos.get(pos).getTask(), Toast.LENGTH_SHORT).show();



                                            }

                                        }

            );

            return convertView;
        }
        @Override
        public Filter getFilter() {

            if (filter == null)
            {
                filter = new CustomFilter();
            }

            return filter;
        }

        class CustomFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    constraint = constraint.toString().toLowerCase();

                    ArrayList<ProviderAdaptInfo> filters = new ArrayList<>();

                    for (int i = 0; i < filterList.size(); i++) {
                        if (filterList.get(i).getTask().toLowerCase().contains(constraint)) {
                            ProviderAdaptInfo tempinfo = new ProviderAdaptInfo(filterList.get(i).getName(),
                                    filterList.get(i).getTask(),
                                    filterList.get(i).getStatus(),
                                    filterList.get(i).getLowestbid());
                            filters.add(tempinfo);
                        }
                    }

                    results.count = filters.size();
                    results.values = filters;

                } else {

                    results.count = filterList.size();
                    results.values = filterList;
                }


                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                providerinfos = (ArrayList<ProviderAdaptInfo>) results.values;
                notifyDataSetChanged();

            }
        }


    }



}






