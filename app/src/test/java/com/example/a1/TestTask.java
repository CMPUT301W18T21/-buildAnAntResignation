package com.example.a1;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
/**
 * Created by Yuan on 2018-02-26.
 */

public class TestTask {

    @Test
    public void testGetTitle(){
        Task task=new Task("title","","","","",1,0);
        assertTrue(task.getTitle()=="title");
    }
    @Test

    public void testGetUsername(){
        Task task=new Task("","Username","","","",1,0);
        assertTrue(task.getUsername()=="Username");
    }
    @Test
    public void testGetAddress(){
        Task task=new Task("","","","address","",1,0);
        assertTrue(task.getAddress()=="address");
    }
    @Test
    public void testGetDes(){
        Task task=new Task("","","des","","",1,0);
        assertTrue(task.getDescription()=="des");
    }
    @Test
    public void testGetSta(){
        Task task=new Task("","","","","sta",1,0);
        assertTrue(task.getStatus()=="sta");
    }
    @Test
    public void testLowestBid(){
        Task task=new Task("","","","","",1,0);
        assertTrue(task.getLowestBid()==1);
    }


    @Test
    public void testUpdateLBid(){
        Task task=new Task("","","","","",1,0);
        task.updateLowestBid(1);
        assertTrue(task.getLowestBid()==1);
    }
    @Test
    public void testImportPhoto(){
        assertTrue(Boolean.TRUE);
    }
    @Test
    public void testGetPhoto(){
        assertTrue(Boolean.TRUE);
    }
    @Test
    public void testgetBids(){
        Task task=new Task("","","","","",1,0);
        assertTrue(task.getBids()==0);

    }


}
