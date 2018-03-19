package com.example.a1;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class RequesterAssignedListviewAdapter extends BaseAdapter{

    private Context mContext;
    private List<Task> mTaskList;

    //Constructor


    public RequesterAssignedListviewAdapter(Context mContext, List<Task> mTaskList) {
        this.mContext = mContext;
        this.mTaskList = mTaskList;
    }



    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.requester_assigned_task_ifo, null);
        TextView View_Task =(TextView)v.findViewById(R.id.View_Task);
        TextView View_User_Name =(TextView)v.findViewById(R.id.View_User_Name);
        TextView View_Status = (TextView)v.findViewById(R.id.View_Status);
        TextView View_Bid = (TextView)v.findViewById(R.id.View_Bid);

        //Set text for TextView
        //View_Task.setText(mTaskList.get(position).getTitle());
        //View_User_Name.setText(mTaskList.get(position).getUsername());
        //View_Status.setText(String.valueOf(mTaskList.get(position).getStatus()));
        //View_Bid.setText(String.valueOf(mTaskList.get(position).getBids()) + "$");
        View_Task.setText("1");
        View_User_Name.setText("1");
        View_Status.setText("1");
        View_Bid.setText("$");


        //save task name to tag
        v.setTag(mTaskList.get(position).getTitle());


        return v;
    }
}


