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
    private String useremail;
    private String userphone;
    private Boolean is_exist;
    private User user;
    private ArrayList<Task> requested_tasks;
    private ArrayList<Task> provided_tasks;

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
                useremail = input_email.getText().toString();
                userphone = input_phone.getText().toString();

                UserElasticSearchController.CheckUserProfileExistTask checkUserProfileExistTask = new UserElasticSearchController.CheckUserProfileExistTask();
                checkUserProfileExistTask.execute(username);
                try {
                    is_exist = checkUserProfileExistTask.get();
                    Log.i("existed user","the user already exist");
                }catch (Exception e){
                    Log.i("print something","hhhhhhhh");
                }
                if (is_exist){
                    Toast.makeText(Register.this, "user already exist", Toast.LENGTH_SHORT).show();
                }else{
                    user = new User();
                    user.setName(username);
                    user.setEmail(useremail);
                    user.setPhone(userphone);
                    requested_tasks = new ArrayList<Task>(0);
                    provided_tasks = new ArrayList<Task>(0);
                    user.setProvidedTasks(provided_tasks);
                    user.setRequestedTasks(requested_tasks);


                    UserElasticSearchController.AddNewUserProfileTask addNewUserProfileTask = new UserElasticSearchController.AddNewUserProfileTask();
                    addNewUserProfileTask.execute(user);

//                    Intent intent = new Intent(Register.this,Login.class);
//                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
