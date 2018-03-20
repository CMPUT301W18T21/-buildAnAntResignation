package com.example.a1;

import org.junit.Test;

import java.util.ArrayList;

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

    public void testGetUsername(){
        ArrayList<Integer> bids=new ArrayList<>(0);

        Task task=new Task("","Username","","",BIDDED,bids);
        assertTrue(task.getUsername()=="Username");
    }

    @Test
    public void testGetDes(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",BIDDED,bids);
        assertTrue(task.getDescription()=="description");
    }
    @Test
    public void testGetSta(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        Task task=new Task("","","description","",BIDDED,bids);
        assertTrue(task.getStatus()==BIDDED);
    }

    /**
     * we create a bids array which contains 3 bids [1,2,3] , the method to be tested is supposed to return us
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
        assertTrue(task.getPhotos()==test);

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
        assertTrue(task.getPhotos()==test);
    }
    @Test
    public void testgetBids(){
        ArrayList<Integer> bids=new ArrayList<>(0);
        bids.add(1);
        bids.add(2);
        bids.add(3);

        Task task=new Task("","","","",BIDDED,bids);
        assertTrue(task.getBids()==bids);

    }


}
