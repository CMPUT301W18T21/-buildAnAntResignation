/*
 * pmpViewHolder
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Alex on 2018-03-19.
 */

/**
 * this class set the buttons.
 * then this class gets the information when the user clicks on one of the tasks in the listview after searching.
 * for more information, please go to ProviderMainPage
 */
public class pmpViewHolder implements View.OnClickListener {

    public TextView textView_task;
    public TextView textView_username;
    public TextView textView_lowestBid;
    public TextView textView_status;

    public pmpItemClickListener itemClickListener;


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

