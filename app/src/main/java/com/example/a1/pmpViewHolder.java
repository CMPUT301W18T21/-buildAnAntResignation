package com.example.a1;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Alex on 2018-03-19.
 */

public class pmpViewHolder implements View.OnClickListener {

    TextView textView_task;
    TextView textView_username;
    TextView textView_lowestBid;
    TextView textView_status;

    pmpItemClickListener itemClickListener;


    public pmpViewHolder(View v) {
        textView_task = (TextView) v.findViewById(R.id.pp_task);
        textView_username = (TextView) v.findViewById(R.id.pp_name);
        textView_lowestBid = (TextView) v.findViewById(R.id.pp_lowestbid);
        textView_status = (TextView) v.findViewById(R.id.pp_status);

        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v);

    }
    public void setItemClickListener(pmpItemClickListener ic)
    {
        this.itemClickListener = ic;

    }
}

