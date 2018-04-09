package com.example.a1;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.a1.Status.BIDDED;
import static com.example.a1.Status.REQUESTED;
import static org.junit.Assert.assertTrue;


/**
 * Created by Alex on 2018-02-26.
 */

public class TestUser {

    @Test
    public void testGetName(){
        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);

        assertTrue(user.getName() == "name1");
    }

    @Test
    public void testSetUsername(){

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        user.setUsername("hahaha");
        assertTrue(user.getUsername().equals("hahaha"));
    }

    @Test
    public void testGetUsername(){

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        assertTrue(user.getUsername() == "uname1");
    }

    @Test
    public void testSetName(){

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        user.setName("test");
        assertTrue(user.getName().equals("test"));
    }

    @Test
    public void testGetGender() {

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","male","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        assertTrue(user.getGender() == "male");
    }

    @Test
    public void testSetGender(){

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        user.setGender("female");
        assertTrue(user.getGender() == "female");

    }

    @Test
    public void testGetPhone() {

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","7807801234","email1","image1",requestedtasks,providedtasks,biddedtasks);
        assertTrue(user.getPhone() == "7807801234");
    }

    @Test
    public void testSetPhone(){

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        user.setPhone("7807108888");
        assertTrue(user.getPhone() == "7807108888");

    }

    @Test
    public void testGetEmail(){
        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","345678@aaa","image1",requestedtasks,providedtasks,biddedtasks);
        assertTrue(user.getEmail()=="345678@aaa");
    }

    @Test
    public void testSetEmail(){
        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        user.setEmail("newEmail");
        assertTrue(user.getEmail()=="newEmail");
    }

    @Test
    public void testGetImage(){

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","img",requestedtasks,providedtasks,biddedtasks);

        assertTrue(user.getImage() == "img");

    }

    /**
     * the image attribute in User class before set is "img", the image is supposed to be a different string like "IMG" if user
     * change this image
     */
    @Test
    public void testSetImage(){
        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        user.setImage("IMG");

        assertTrue(user.getImage()=="IMG");

    }





    @Test
    public void testGetRequestedTasks(){
        ArrayList<Integer> bids = new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        requestedtasks.add(task);

        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(user.getRequestedTasks().equals(requestedtasks));
    }

    @Test
    public void testSetRequestedTasks(){
        ArrayList<Integer> bids = new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        requestedtasks.add(task);

        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(user.getRequestedTasks().equals(requestedtasks));
    }

    @Test
    public void testRequestTask(){
        /**
         *         Initialize an array so that we can initialize a task. The we let the RequestedTask() to do what it should do.
         *         (add a task named task1 into an array in User class)
         *         then we check if the array actually contains the task1.
         *
         */
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        requestedtasks.add(task);

        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(user.getRequestedTask(0).equals(task));
    }

    @Test
    public void testGetRequestedTask(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        requestedtasks.add(task);

        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        assertTrue(user.getRequestedTask(0).equals(task));
    }

    @Test
    public void testDeleteRequestedTask(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task1 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        Task task2 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        requestedtasks.add(task1);
        requestedtasks.add(task2);


        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        user.deleteRequestedTask(task1);
        assertTrue(user.getRequestedTask(0).equals(task2));
    }




    @Test
    public void testGetProvidedTasks(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task1 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        Task task2 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        providedtasks.add(task1);
        providedtasks.add(task2);


        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        ArrayList<Task> testTask = new ArrayList<>(0);
        testTask.add(task1);
        testTask.add(task2);
        assertTrue(user.getProvidedTasks().equals(testTask));
    }

    @Test
    public void testSetProvidedTasks(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task1 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        Task task2 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        providedtasks.add(task1);
        providedtasks.add(task2);


        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        user.setProvidedTasks(providedtasks);
//        ArrayList<Task> testTask = new ArrayList<>(0);
//        testTask.add(task1);
//        testTask.add(task2);
        assertTrue(user.getProvidedTasks().equals(providedtasks));
    }

//    @Test
//    public void testProvideTask(){
//        ArrayList<Integer> bids=new ArrayList<>(0);
//        Task task1 = new Task("", "", "", "", BIDDED, bids);
//
//        User user=new User("", "", "", "", "", "");
//        user.provideTask(task1);
//        Task task2 = user.getProvidedTasks().get(0);
//        assertTrue(task2 == task1);
//    }

    @Test
    public void testGetProvidedTask(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task1 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        Task task2 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        providedtasks.add(task1);
        providedtasks.add(task2);


        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        user.setProvidedTasks(providedtasks);
//        ArrayList<Task> testTask = new ArrayList<>(0);
//        testTask.add(task1);
//        testTask.add(task2);
        assertTrue(user.getProvidedTask(0).equals(task1));
    }


    @Test
    public void testDeleteProvidedTask(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task1 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        Task task2 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        providedtasks.add(task1);
        providedtasks.add(task2);


        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();
        user.deleteProvidedTask(task1);
//        ArrayList<Task> testTask = new ArrayList<>(0);
//        testTask.add(task1);
//        testTask.add(task2);
        assertTrue(user.getProvidedTask(0).equals(task2));
    }


    @Test
    public void testGetBiddedTasks(){
        ArrayList<Integer> bids = new ArrayList<>(0);
        Task task1 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        Task task2 = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        ArrayList<Task> requestedtasks = new ArrayList<>(0);
        ArrayList<Task> providedtasks = new ArrayList<>(0);
        ArrayList<Task> biddedtasks = new ArrayList<>(0);
        biddedtasks.add(task1);
        biddedtasks.add(task2);


        User user = new User("name1","uname1","gender1","phone1","email1","image1",requestedtasks,providedtasks,biddedtasks);
        //ArrayList<Task> tasks2 = user.getRequestedTasks();

//        ArrayList<Task> testTask = new ArrayList<>(0);
//        testTask.add(task1);
//        testTask.add(task2);
        assertTrue(user.getBiddedTasks().equals(biddedtasks));
    }

//    @Test
//    public void testBidTask(){
//        ArrayList<Integer> bids=new ArrayList<>(0);
//        Task task = new Task("", "", "", "", BIDDED, bids);
//        User user=new User("", "", "", "", "", "");
//        user.bidTask(task);
//        Task task2 = user.getBiddedTasks().get(0);
//        assertTrue(task2.equals(task));
//    }

}
