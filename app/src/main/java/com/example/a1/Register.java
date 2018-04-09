/*
 * Register
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private User user;

    /**
     * a method that execute every time the activity is shown.
     * this method captures the information entered by the user and then uploads it to the server.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ((Button)findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)findViewById(R.id.userName)).getText().toString();
                String email = (((EditText)findViewById(R.id.Email))).getText().toString();
                String phone = ((EditText)findViewById(R.id.Phone)).getText().toString();
                String gender = ((EditText)findViewById(R.id.gender)).getText().toString();
                String name = ((EditText)findViewById(R.id.name)).getText().toString();


                if(!Server.UserController.check(username)){

                    if(username.equals("") || username.equals(null)) {
                        Toast.makeText(Register.this, "Please enter a valid username!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    user = new User();
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setGender(gender);
                    user.setPhone(phone);
                    if(name == null || name == "") name = username;
                    user.setName(name);

                    Server.UserController.add(user);
                    finish();
                }else{
                    Toast.makeText(Register.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
