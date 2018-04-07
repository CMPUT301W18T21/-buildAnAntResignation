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

    private EditText input_name;
    private EditText input_email;
    private EditText input_phone;
    private Button save_bt;
    private String username;
    private String email;
    private String phone;
    private Boolean is_exist;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_name = (EditText)findViewById(R.id.userName);
        input_email= (EditText)findViewById(R.id.Email);
        input_phone = (EditText)findViewById(R.id.Phone);
        save_bt = (Button)findViewById(R.id.save);

        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = input_name.getText().toString();
                email = input_email.getText().toString();
                phone = input_phone.getText().toString();
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
