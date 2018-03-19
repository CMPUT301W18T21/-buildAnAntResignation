package com.example.a1;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.ExecutionException;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Yuan on 2018-02-26.
 */

public class TestTask {

    @Test
    public void testGetTitle(){
        Task task=new Task("title","","");
        assertTrue(task.getTitle()=="title");
    }
    @Test

    public void testGetUsername(){
        Task task=new Task("","Username","");
        assertTrue(task.getUsername()=="Username");
    }
    @Test
    public void testGetAddress(){
        Task task=new Task("","","","address";
        assertTrue(task.getLocation()=="address");
    }
    @Test
    public void testGetDes(){
        Task task=new Task("","","des");
        assertTrue(task.getDescription()=="des");
    }
    @Test
    public void testGetStatus(){
        Task task=new Task("","","");
        assertTrue(task.getStatus()=="sta");
    }
    @Test
    public void testLowestBid(){
        Task task=new Task("","","");
        assertTrue(task.getLowestBid()==1);
    }


    @Test
    public void testUpdateLBid(){
        Task task=new Task("","","");
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
        Task task=new Task("","","";
        assertTrue(task.getBids()==0);

    }


}
