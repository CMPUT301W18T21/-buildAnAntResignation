package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class RequesterBiddedTask extends AppCompatActivity {
    Button BackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_bidded_task);
        setupBackButton();
    }

    private void setupBackButton() {
        /** when add button is clicked jump to addsubscription View
         */

        BackButton = (Button) findViewById(R.id.back);
        BackButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(RequesterBiddedTask.this, RequesterMain.class);
                startActivityForResult(intent, 1);


            }
        });

    }

}

