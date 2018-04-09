package com.example.a1;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.a1.Status.ASSIGNED;
import static com.example.a1.Status.BIDDED;
import static com.example.a1.Status.REQUESTED;
import static org.junit.Assert.assertTrue;


public class TestTask {


    @Test
    public void testGetTitle(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","description1","location1",REQUESTED,"photo1",bids);
        assertTrue(task.getTitle()=="title1");
    }

    @Test
    public void testGetRequesterName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","description1","location1",REQUESTED,"photo1",bids);
        assertTrue(task.getRequesterName()=="Rname");
    }

    @Test
    public void testGetProviderName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        assertTrue(task.getProviderName()=="Pname");
    }

    @Test
    public void testSetRequesterName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setRequesterName("Username1");
        assertTrue(task.getRequesterName()=="Username1");
    }

    @Test
    public void testSetProviderName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setProviderName("Username1");
        assertTrue(task.getProviderName()=="Username1");
    }

    @Test
    public void testGetDescription(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        assertTrue(task.getDescription()=="description1");
    }

    @Test
    public void testGetStatus(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        assertTrue(task.getStatus()==REQUESTED);
    }

    @Test
    public void testSetBidded(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setBidded();
        assertTrue(task.getStatus() == Status.BIDDED);
    }

    @Test
    public void testSetAssigned(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setAssigned();
        assertTrue(task.getStatus() == Status.ASSIGNED);
    }

    @Test
    public void testSetRequested(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setRequested();
        assertTrue(task.getStatus() == REQUESTED);
    }

    @Test
    public void testSetDone(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setDone();
        assertTrue(task.getStatus() == Status.DONE);
    }

    @Test
    public void testSetLocation(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setLocation("location2");
        assertTrue(task.getLocation() == "location2");
    }

    @Test
    public void testgetLocation(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);

        assertTrue(task.getLocation() == "location1");
    }

    @Test
    public void testgetLatitude(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setLatitude(56.44343434);
        assertTrue(task.getLatitude() == 56.44343434);
    }

    @Test
    public void testgetLongitude(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setLongitude(12345.45);
        assertTrue(task.getLongitude() == 12345.45);
    }


    @Test
    public void testGetBids(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        bids.add(1);
        bids.add(2);
        bids.add(3);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        assertTrue(task.getBids()==bids);

    }

    @Test
    public void testAddBids(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.addBid(1);
        task.addBid(2);
        task.addBid(3);
        bids.add(1);
        bids.add(2);
        bids.add(3);
        assertTrue(task.getBids()==bids);
    }

    /**
     * we create a bid array which contains 3 bid [1,2,3] , the method to be tested is supposed to return us
     * a smallest bid
     */
    @Test
    public void testLowestBid(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.addBid(1);
        task.addBid(2);
        task.addBid(3);
        assertTrue(task.getLowestBid()==1);

        //We do the same test in a different array order
        bids = new ArrayList<>(0);
        task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.addBid(3);
        task.addBid(1);
        task.addBid(2);
        assertTrue(task.getLowestBid()==1);
    }


    /**
     * the photo before importing to server will be compressed to string format , therefore we just need to
     * add string to an array to store photos
     */
    @Test
    public void testImportPhoto(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        //ArrayList<String> test=new ArrayList<>(0);
        //test.add("photoString");
        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setPhoto("photoString");
        assertTrue(task.getPhoto() == "photoString");

    }

    @Test
    public void testGetPhoto(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task = new Task("title1","Rname","Pname","description1","location1",REQUESTED,"photo1",bids);
        task.setPhoto("photoString");
        assertTrue(task.getPhoto().equals("photoString"));
    }



}
