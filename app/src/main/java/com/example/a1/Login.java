package com.example.a1;

/*
 * Login
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private String username;

    /**
     * this method asks the user to input the required information of sign up
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button)findViewById(R.id.login);
        Button signup = (Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = ((EditText)findViewById(R.id.inputname)).getText().toString();

                User user = Server.UserController.get(username);

                if(username.equals("") || username.equals(null)) {
                    Toast.makeText(Login.this, "Please enter a valid username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (user == null){
                    Toast.makeText(Login.this, "User not found!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("username", username);
                User.setCurrentUser(username);
                startActivity(intent);

            }
        });

        /**
         * this method saves the information the user input to the server
         */
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });
    }
}
