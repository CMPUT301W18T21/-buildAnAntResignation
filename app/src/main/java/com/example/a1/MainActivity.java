package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    private static User user = new User("name","Us3rn4m3", "male","7","@","img");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public static User getCurrentUser(){
        return user;
    }

}
