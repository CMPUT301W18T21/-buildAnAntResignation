package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ContactInfo extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        setTitle("Contact Info");
    }

    public void setUser(User user){
        this.user = user;
        ((TextView) findViewById(R.id.username)).setText(user.getUsername());
        ((EditText) findViewById(R.id.nameEditText)).setText(user.getName());
        ((EditText) findViewById(R.id.genderEditText)).setText(user.getGender());
        ((EditText) findViewById(R.id.phoneEditText)).setText(user.getPhone());
        ((EditText) findViewById(R.id.emailEditText)).setText(user.getEmail());
    }

    public void onButtonClick(View view){
        if ( user.getUsername() == MainActivity.getCurrentUser().getUsername()){
            user.setName(((TextView) findViewById(R.id.nameEditText)).getText().toString());
            user.setGender(((TextView) findViewById(R.id.genderEditText)).getText().toString());
            user.setPhone(((TextView) findViewById(R.id.phoneEditText)).getText().toString());
            user.setEmail(((TextView) findViewById(R.id.emailEditText)).getText().toString());
            MainActivity.getCurrentUser().setName(user.getName());
            MainActivity.getCurrentUser().setGender(user.getGender());
            MainActivity.getCurrentUser().setPhone(user.getPhone());
            MainActivity.getCurrentUser().setEmail(user.getEmail());
        }

        //Might a new intent here.
        finish();
    }
}
