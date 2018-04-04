package com.example.a1;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.a1.Status.ASSIGNED;
import static com.example.a1.Status.BIDDED;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yuan on 2018-02-26.
 */

public class TestTask {

    @Test
    public void testGetTitle(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("title","","","",BIDDED,bids);
        assertTrue(task.getTitle()=="title");
    }

    @Test
    public void testGetRequesterName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task=new Task("","Username","","",BIDDED,bids);
        assertTrue(task.getRequesterName()=="Username");
    }

    @Test
    public void testGetProviderName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task=new Task("","","Username", "","",BIDDED,bids);
        assertTrue(task.getProviderName()=="Username");
    }

    @Test
    public void testSetRequesterName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task=new Task("","Username","","",BIDDED,bids);
        task.setRequesterName("Username1");
        assertTrue(task.getRequesterName()=="Username1");
    }

    @Test
    public void testSetProviderName(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task=new Task("","","Username", "","",BIDDED,bids);
        task.setProviderName("Username1");
        assertTrue(task.getProviderName()=="Username1");
    }

    @Test
    public void testGetDescription(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",BIDDED,bids);
        assertTrue(task.getDescription()=="description");
    }

    @Test
    public void testGetStatus(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",BIDDED,bids);
        assertTrue(task.getStatus()==BIDDED);
    }

    @Test
    public void testSetBidded(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",ASSIGNED,bids);
        task.setBidded();
        assertTrue(task.getStatus() == Status.BIDDED);
    }

    @Test
    public void testSetAssigned(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",BIDDED,bids);
        task.setAssigned();
        assertTrue(task.getStatus() == Status.ASSIGNED);
    }

    @Test
    public void testSetRequested(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",ASSIGNED,bids);
        task.setRequested();
        assertTrue(task.getStatus() == Status.REQUESTED);
    }

    @Test
    public void testSetDone(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",ASSIGNED,bids);
        task.setDone();
        assertTrue(task.getStatus() == Status.DONE);
    }


    /**
        NEED GET LOCATION TEST!
    */

    @Test
    public void testGetBids(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        bids.add(1);
        bids.add(2);
        bids.add(3);

        Task task=new Task("","","","",BIDDED,bids);
        assertTrue(task.getBids()==bids);

    }

    @Test
    public void testAddBids(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",BIDDED,bids);
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
        Task task=new Task("","","description","",BIDDED,bids);
        task.addBid(1);
        task.addBid(2);
        task.addBid(3);
        assertTrue(task.getLowestBid()==1);

        //We do the same test in a different array order
        bids=new ArrayList<>(0);
        task=new Task("","","description","",BIDDED,bids);
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
        ArrayList<String> test=new ArrayList<>(0);
        test.add("photoString");
        Task task=new Task("","","","",BIDDED,bids);
        task.importPhoto("photoString");
        assertTrue(task.getPhotos().equals(test));

    }

    /**
     *
     */
    @Test
    public void testGetPhoto(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        ArrayList<String> test=new ArrayList<>(0);

        test.add("photoString");
        Task task=new Task("","","","",BIDDED,bids);
        task.importPhoto("photoString");
        assertTrue(task.getPhotos().equals(test));
    }



}
