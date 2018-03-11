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
        //setUser(new User("Name01", "username01","Male","(555)-555-5555","123 Street Smalltown", "img"));
    }

    public void setUser(User user){
        this.user = user;
        ((TextView) findViewById(R.id.username)).setText(user.getUsername());
        ((EditText) findViewById(R.id.nameEditText)).setText(user.getName());
        ((EditText) findViewById(R.id.genderEditText)).setText(user.getGender());
        ((EditText) findViewById(R.id.phoneEditText)).setText(user.getPhone());
        ((EditText) findViewById(R.id.addressEditText)).setText(user.getAddress());
    }

    public void onEditClick(View view){
        if ( true /*user.getUsername() == Mainscreen.user.getUsername()*/){
            user.setName(((TextView) findViewById(R.id.nameEditText)).getText().toString());
            user.setGender(((TextView) findViewById(R.id.genderEditText)).getText().toString());
            user.setPhone(((TextView) findViewById(R.id.phoneEditText)).getText().toString());
            user.setAddress(((TextView) findViewById(R.id.addressEditText)).getText().toString());
            /*Mainscreen.user = user;*/
        }

        //Might a new intent here.
        finish();

    }
}
