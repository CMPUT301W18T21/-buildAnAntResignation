package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText input_name;
    private Button Login;
    private Button Signup;
    private String username;
    private Boolean isExist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_name = (EditText)findViewById(R.id.inputname);
        Login = (Button)findViewById(R.id.login);
        Signup = (Button)findViewById(R.id.signup);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = input_name.getText().toString();

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
                startActivity(intent);

            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);

            }
        });
    }
}
