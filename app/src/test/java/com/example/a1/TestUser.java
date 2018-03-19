package com.example.a1;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static com.example.a1.Status.BIDDED;
import java.util.ArrayList;


/**
 * Created by Alex on 2018-02-26.
 */

public class TestUser {

    @Test
    //
    public void getName(){

        User user = new User("laoxu","","","","","");
        assertTrue(user.getName() == "laoxu");
    }

    @Test
    public void setName(){

        User user = new User("laoxu","","","","","");
        user.setName("hahaha");
        assertTrue(user.getName() == "hahaha");
    }

    @Test
    public void getUser(){
        User user = new User("","Alex","","","","");
        assertTrue(user.getUsername() == "Alex");
    }

    @Test
    public void getGender() {

        User user = new User("", "", "male", "", "", "");
        assertTrue(user.getGender() == "male");
    }

    @Test

    public void getPhone() {

        User user = new User("", "", "", "7807801234", "", "");
        assertTrue(user.getPhone() == "7807801234");
    }
    @Test
    public void getEmail(){
        User user = new User("", "", "", "", "345678@aaa", "");
        assertTrue(user.getEmail()=="345678@aaa");
    }

    @Test
    public void testRequestTask(){
        /**
         *         Initialize an array so that we can initialize a task. The we let the RequestedTask() to do what it should do.
         *         (add a task named task1 into an array in User class)
         *         then we check if the array actually contains the task1.
         *
         */
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task1 = new Task("", "", "", "", BIDDED, bids);

        User user=new User("", "", "", "", "", "");
        user.requestTask(task1);
        Task task2=user.getRequestedTask(0);
        assertTrue(task2==task1);
    }





    @Test
    public void setGender(){

        User user = new User("","","male","","","");
        user.setGender("female");
        assertTrue(user.getGender() == "female");

    }

    @Test
    public void setPhone(){

        User user = new User("","","","7807109999","","");
        user.setPhone("7807108888");
        assertTrue(user.getPhone() == "7807108888");

    }

    /**
     *     In order to set image, we let user choose poicture from gallery then compress the picture to string format
     *  therefore our setImage() method takes image argument in string format
     */

    @Test
    public void testGetImage(){

        User user = new User("","","","","","img");

        assertTrue(user.getImage() == "img");

    }

    /**
     * the image attribute in User class before set is "img", the image is supposed to be a different string like "IMG" if user
     * change this image
     */
    @Test
    public void testSetImae(){
        User user = new User("","","","","","img");
        user.setImage("IMG");
        assertTrue(user.getImage()=="IMG");

    }







}
