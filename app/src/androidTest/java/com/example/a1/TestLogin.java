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
    private  String testUserName;
    private  String name;
    private  String gender;

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
    public void testSignUp(){
        Login activity = (Login)solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",Login.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.inputname),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnButton("SIGNUP");
        solo.assertCurrentActivity("wrongActivity",Register.class);

        testUserName = "Hao";
        name = "hao liu";
        gender = "male";
        email ="test@gmail.com";
        phoNum ="7806043976";

        solo.enterText((EditText)solo.getView(R.id.userName),testUserName);
        solo.enterText((EditText)solo.getView(R.id.name),name);
        solo.enterText((EditText)solo.getView(R.id.Email),email);
        solo.enterText((EditText)solo.getView(R.id.Phone),phoNum);
        solo.enterText((EditText)solo.getView(R.id.gender),gender);

        solo.clickOnButton("REGISTER");
        solo.assertCurrentActivity("WrongAcvity",Login.class);





    }








    /**
     * test edit Profile
     */

    public void testModifyProfile(){
        Login activity = (Login)solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity",Login.class);
        testName = "test";
        solo.enterText((EditText)solo.getView(R.id.inputname),testName);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("Wrong Activity",MainActivity.class);

        solo.clickOnButton("VIEW PROFILE");
        solo.assertCurrentActivity("WrongActivity",ContactInfo.class);

        email = "test@gmail.com";
        phoNum = "7806046783";

        solo.enterText((EditText)solo.getView(R.id.emailEditText),email);
        solo.enterText((EditText)solo.getView(R.id.phoneEditText),phoNum);

        solo.clickOnButton("Finish");
        solo.assertCurrentActivity("WrongActivity",MainActivity.class);
    }



}
