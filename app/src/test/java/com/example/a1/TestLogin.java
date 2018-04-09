package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

/**
 * Created by Hao on 2018/5/9.
 */



public class TestLogin extends  ActivityInstrumentationTestCase2<Login>{

    private  Solo solo;
    private  String testName;
    private  String email;
    private  String phoNum;

    public TestLogin(){super(Login.class);}

    public void setUp()throws Exception{
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testStart()throws Exception{
        AppCompatActivity activity = getActivity();
    }

    /**
     * test login
     */

    public void testLogin(){
        Login activity = (Login) solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",Login.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.inputname),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        // go to see my request task
        solo.clickOnButton("Requeste A Task");
        solo.assertCurrentActivity("Wrong Activity",RequesterMain.class);

        // keep going
        // Create new task
        solo.clickOnButton("Request");
        solo.assertCurrentActivity("Wrong Activity",TaskRequest.class);

        solo.enterText((EditText)solo.getView(R.id.titleEditText),"testTask");
        assertTrue(solo.waitForText("testTask"));
        solo.enterText((EditText)solo.getView(R.id.descriptionEditText),"testTask");
        assertTrue(solo.waitForText("testTask"));
        solo.clickOnButton("Request");
        solo.assertCurrentActivity("Wrong Activity", RequesterMain.class);


        }


     /**
     * test signup
     */



}
