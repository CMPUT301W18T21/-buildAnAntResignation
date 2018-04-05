package com.example.a1;

/**
 * Created by k-h on 2018-03-19.
 */


import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

public class UserElasticSearchControllerTest{

    private final UserElasticSearchController userElasticSearchController = new UserElasticSearchController();


    @Test
    public void testAddNewUserProfileTask(){
        User user1 = new User("name1", "uname1", "mixed gender","123456","123456@23.com","None");
        UserElasticSearchController.AddNewUserProfileTask addNewUserProfileTask = new UserElasticSearchController.AddNewUserProfileTask();
        addNewUserProfileTask.execute(user1);
        UserElasticSearchController.CheckUserProfileExistTask checkUserProfileExistTask = new UserElasticSearchController.CheckUserProfileExistTask();
        checkUserProfileExistTask.execute("uname1");
        Boolean isExist = false;
        try {
            isExist = checkUserProfileExistTask.get();
        } catch (Exception e) {
            //do nothing
        }
        if(isExist == null) isExist = false;
        assertTrue(isExist);
    }

    @Test
    public void testCheckUserProfileExistTask(){
        UserElasticSearchController.CheckUserProfileExistTask checkUserProfileExistTask = new UserElasticSearchController.CheckUserProfileExistTask();
        checkUserProfileExistTask.execute("no such file exist");

        try {
            Boolean isExist = checkUserProfileExistTask.get();

            assertTrue(!isExist);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        UserElasticSearchController.CheckUserProfileExistTask checkUserProfileExistTask1 = new UserElasticSearchController.CheckUserProfileExistTask();
        checkUserProfileExistTask1.execute("uname1");

        try {
            Boolean isExist = checkUserProfileExistTask1.get();

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

        UserElasticSearchController.AddNewUserProfileTask addNewUserProfileTask = new UserElasticSearchController.AddNewUserProfileTask();
        addNewUserProfileTask.execute(user2);

        UserElasticSearchController.GetUserProfileTask getUserProfileTask = new UserElasticSearchController.GetUserProfileTask();
        getUserProfileTask.execute("uname2");

        User getUserProfile = null;

        try {
            getUserProfile = getUserProfileTask.get();



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        assertTrue(user2.equals(getUserProfile));
    }


    @Test
    public void testDeleteUserTask(){

    }

}