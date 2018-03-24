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
    private String userName;
    private Boolean isExist;


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
                userName = input_name.getText().toString();

                UserElasticSearchController.CheckUserProfileExistTask checkUserProfileExistTask = new UserElasticSearchController.CheckUserProfileExistTask();
                checkUserProfileExistTask.execute(userName);
                try {
                    isExist = checkUserProfileExistTask.get();
                    Log.i("existed user","the user already exist");
                    Log.d("print isExist",isExist.toString());
                }catch (Exception e){
                    Log.i("print something","hhhhhhhh");
                }
                if (isExist){
                    Intent intent = new Intent(com.example.a1.Login.this, MainActivity.class);
                    intent.putExtra("username",userName);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"login....",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(getApplicationContext(),"unidentified user name",Toast.LENGTH_SHORT);
                }

            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.a1.Login.this,Register.class);
                startActivity(intent);

            }
        });
    }
}
