package com.example.a1;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.a1.Status.BIDDED;
import static org.junit.Assert.assertTrue;


/**
 * Created by Alex on 2018-02-26.
 */

public class TestUser {

    @Test
    public void testGetName(){

        User user = new User("laoxu","","","","","");
        assertTrue(user.getName() == "laoxu");
    }

    @Test
    public void testSetUsername(){

        User user = new User("","username","","","","");
        user.setUsername("hahaha");
        assertTrue(user.getUsername().equals("hahaha"));
    }

    @Test
    public void testGetUsername(){

        User user = new User("","Username","","","","");
        assertTrue(user.getUsername() == "Username");
    }

    @Test
    public void testSetName(){

        User user = new User("Name","","","","","");
        user.setName("test");
        assertTrue(user.getName().equals("test"));
    }

    @Test
    public void testGetGender() {

        User user = new User("", "", "male", "", "", "");
        assertTrue(user.getGender() == "male");
    }

    @Test
    public void testSetGender(){

        User user = new User("","","male","","","");
        user.setGender("female");
        assertTrue(user.getGender() == "female");

    }

    @Test
    public void testGetPhone() {

        User user = new User("", "", "", "7807801234", "", "");
        assertTrue(user.getPhone() == "7807801234");
    }

    @Test
    public void testSetPhone(){

        User user = new User("","","","7807109999","","");
        user.setPhone("7807108888");
        assertTrue(user.getPhone() == "7807108888");

    }

    @Test
    public void testGetEmail(){
        User user = new User("", "", "", "", "345678@aaa", "");
        assertTrue(user.getEmail()=="345678@aaa");
    }

    @Test
    public void testSetEmail(){
        User user = new User("", "", "", "", "345678@aaa", "");
        user.setEmail("newEmail");
        assertTrue(user.getEmail()=="newEmail");
    }

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
    public void testSetImage(){
        User user = new User("","","","","","img");
        user.setImage("IMG");
        assertTrue(user.getImage()=="IMG");

    }





    @Test
    public void testGetRequestedTasks(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        ArrayList<Task> tasks = new ArrayList<>(0);
        tasks.add( new Task("", "", "", "", BIDDED, bids));

        User user=new User("", "", "", "", "", "",tasks,tasks);
        ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(tasks.equals(tasks2));
    }

    @Test
    public void testSetRequestedTasks(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        ArrayList<Task> tasks = new ArrayList<>(0);
        tasks.add( new Task("", "", "", "", BIDDED, bids));

        User user=new User("", "", "", "", "", "");
        user.setRequestedTasks(tasks);
        ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(tasks.equals(tasks2));
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
        Task task2 = user.getRequestedTasks().get(0);
        assertTrue(task2 == task1);
    }

    @Test
    public void testGetRequestedTask(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task1 = new Task("", "", "", "", BIDDED, bids);

        User user = new User("", "", "", "", "", "");
        user.requestTask(task1);
        Task task2 = user.getRequestedTask(0);
        assertTrue(task1.equals(task2));
    }

    @Test
    public void testDeleteRequestedTask(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("", "", "", "", BIDDED, bids);

        User user=new User("", "", "", "", "", "");
        user.requestTask(task);
        user.deleteRequestedTask(task);
        ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(tasks2.equals(new ArrayList<Task>(0)));
    }




    @Test
    public void testGetProvidedTasks(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        ArrayList<Task> tasks = new ArrayList<>(0);
        tasks.add( new Task("", "", "", "", BIDDED, bids));

        User user=new User("", "", "", "", "", "",tasks,tasks);
        ArrayList<Task> tasks2 = user.getProvidedTasks();
        assertTrue(tasks.equals(tasks2));
    }

    @Test
    public void testSetProvidedTasks(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        ArrayList<Task> tasks = new ArrayList<>(0);
        tasks.add( new Task("", "", "", "", BIDDED, bids));

        User user=new User("", "", "", "", "", "");
        user.setProvidedTasks(tasks);
        ArrayList<Task> tasks2 = user.getProvidedTasks();
        assertTrue(tasks.equals(tasks2));
    }

    @Test
    public void testProvideTask(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task1 = new Task("", "", "", "", BIDDED, bids);

        User user=new User("", "", "", "", "", "");
        user.provideTask(task1);
        Task task2 = user.getProvidedTasks().get(0);
        assertTrue(task2 == task1);
    }

    @Test
    public void testGetProvidedTask(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task1 = new Task("", "", "", "", BIDDED, bids);

        User user=new User("", "", "", "", "", "");
        user.provideTask(task1);
        Task task2 = user.getProvidedTask(0);
        assertTrue(task2 == task1);
    }


    @Test
    public void testDeleteProvidedTask(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("", "", "", "", BIDDED, bids);

        User user=new User("", "", "", "", "", "");
        user.provideTask(task);
        user.deleteProvidedTask(task);
        ArrayList<Task> tasks2 = user.getProvidedTasks();
        assertTrue(tasks2.equals(new ArrayList<Task>(0)));
    }


    @Test
    public void testGetBiddedTasks(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("", "", "", "", BIDDED, bids);

        ArrayList<Task> biddedTasks = new ArrayList<>(0);
        biddedTasks.add(task);

        User user=new User("", "", "", "", "", "",biddedTasks,biddedTasks,biddedTasks);
        ArrayList<Task> tasks2 = user.getBiddedTasks();
        assertTrue(tasks2.equals(biddedTasks));
    }

    @Test
    public void testBidTask(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("", "", "", "", BIDDED, bids);
        User user=new User("", "", "", "", "", "");
        user.bidTask(task);
        Task task2 = user.getBiddedTasks().get(0);
        assertTrue(task2.equals(task));
    }

}
