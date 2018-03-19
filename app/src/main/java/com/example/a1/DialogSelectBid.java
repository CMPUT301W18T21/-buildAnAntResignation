package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class DialogSelectBid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_select_bid);

        Button accept = (Button)findViewById(R.id.buttonAccept);
        Button decline = (Button)findViewById(R.id.buttonDecline);


        //accept botton be clicked keep the bid and remove other bids of the Task and jump back to TaskDetailScreen
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialogSelectBid.this,RequesterTaskDetail.class);

                startActivity(intent);

            }
        });


        //decline botton be clicked remove the bid of the task and jump back to TaskDetailScreen
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DialogSelectBid.this,RequesterTaskDetail.class);

                startActivity(intent);

            }
        });
    }

}
