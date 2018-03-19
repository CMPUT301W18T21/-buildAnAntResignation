package com.example.a1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Alex on 2018-03-19.
 * This class is for building a custom adapter which is to adapt several item into single listView.
 *
 */

public class pmpCustomAdapter extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<ProviderAdaptInfo> providerinfos;
    LayoutInflater inflater;
    CustomFilter filter;
    ArrayList<ProviderAdaptInfo> filterList;

    //custom adapter constructor

    public pmpCustomAdapter(Context ctx, ArrayList<ProviderAdaptInfo> providerinfos){
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

        if (inflater==null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null)
        {
            convertView=inflater.inflate(R.layout.custom_pp,null);

        }
        pmpViewHolder holder = new pmpViewHolder(convertView);


        holder.textView_task.setText(providerinfos.get(pos).getTask());
        holder.textView_username.setText(providerinfos.get(pos).getName());
        holder.textView_status.setText(providerinfos.get(pos).getStatus());
        holder.textView_lowestBid.setText(providerinfos.get(pos).getLowestbid().toString());

        holder.setItemClickListener(new pmpItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Toast.makeText(c,providerinfos.get(pos).getTask(),Toast.LENGTH_SHORT).show();
                MainActivity.getSelectedTask.setTitle(providerinfos.get(pos).getTask());
                MainActivity.getSelectedTask.setUsername(providerinfos.get(pos).getName());
                //MainActivity.getSelectedTask.setDescription(providerinfos.get(pos).get);
                Intent myIntent = new Intent(ProviderMainPage.this,Provider_bid_task.class);
                //star.(myIntent);


            }
        });
/*
MainActivity.getSelectedTask.setTitle();
MainActivity.getSelectedTask.setUsername():
MainActivity.getSelectedTask.setDescription();
 */
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

    class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0)
            {
                constraint = constraint.toString().toLowerCase();

                ArrayList<ProviderAdaptInfo> filters = new ArrayList<>();

                for (int i  = 0 ; i < filterList.size();i++)
                {
                    if (filterList.get(i).getTask().toLowerCase().contains(constraint))
                    {
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
