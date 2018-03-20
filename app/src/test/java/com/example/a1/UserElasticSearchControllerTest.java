package com.example.a1;

/**
 * Created by k-h on 2018-03-19.
 */

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class UserElasticSearchControllerTest extends ActivityInstrumentationTestCase2{

    private final UserElasticSearchController userelasticsearchcontroller = new UserElasticSearchController();


    //  Task task1 = new Task("TaskRequester1", "Title1", "Description1");
    // Task task2 = new Task("TaskRequester2", "Title2", "Description3");
    //Task task3 = new Task("TaskRequester3", "Title3", "Description3");

    // User user1 = new User("name1", "uname1", "mixed gender","123456","123456@23.com","None");
    //  User user2 = new User("name2", "uname2", "mixed gender","123454","123457@23.com","None");
    // User user3 = new User("name3", "uname3", "mixed gender","123452","123458@23.com","None");



    public UserElasticSearchControllerTest(){
        super(UserElasticSearchController.class);
    }

    @Test
    public void testAddNewUserProfileTask(){
        User user1 = new User("name1", "uname1", "mixed gender","123456","123456@23.com","None");

        UserElasticSearchController.AddNewUserProfileTask addnewuserprofiletask = new UserElasticSearchController.AddNewUserProfileTask();
        addnewuserprofiletask.execute(user1);
        UserElasticSearchController.CheckUserProfileExistTask checkUserProfileExistTask = new UserElasticSearchController.CheckUserProfileExistTask();
        checkUserProfileExistTask.execute("nam1");
        try {
            Boolean isExist = checkUserProfileExistTask.get();

            assertTrue(isExist);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCheckUserProfileExistTask(){
        UserElasticSearchController.CheckUserProfileExistTask checkuserprofileexiisttask = new UserElasticSearchController.CheckUserProfileExistTask();
        checkuserprofileexiisttask.execute("no such file exist");

        try {
            Boolean isExist = checkuserprofileexiisttask.get();

            assertFalse(isExist);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        UserElasticSearchController.CheckUserProfileExistTask checkuserprofileexiisttask1 = new UserElasticSearchController.CheckUserProfileExistTask();
        checkuserprofileexiisttask1.execute("uname1");

        try {
            Boolean isExist = checkuserprofileexiisttask1.get();

            assertTrue(isExist);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testGetUserProfileTask(){
        User user2 = new User("name2", "uname2", "mixed gender","123454","123457@23.com","None");

        UserElasticSearchController.AddNewUserProfileTask addnewuserprofiletask = new UserElasticSearchController.AddNewUserProfileTask();
        addnewuserprofiletask.execute(user2);

        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();
        getUserProfileTask.execute("uname2");

        try {
            User getUserProfile = getUserProfileTask.get();

            assertTrue(user2.getUsername().equals(getUserProfile.getUsername()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void testUpdateUserProfileTask(){
        User user3 = new User("name3", "uname3", "mixed gender","123452","123458@23.com","None");

        UserElasticSearchController.AddNewUserProfileTask addnewuserprofiletask = new UserElasticSearchController.AddNewUserProfileTask();
        addnewuserprofiletask.execute(user3);
        User user4 = user3;
        user4.setPhone("1111111111111");
        UserElasticSearchController.AddNewUserProfileTask addnewuserprofiletask1 = new UserElasticSearchController.AddNewUserProfileTask();
        addnewuserprofiletask1.execute(user4);
        try {

            assertEquals(user3.getPhone(), user4.getPhone());
        }
        //force fail
        catch (Exception e) {
            assertEquals(0, 1);
        }

    }


}