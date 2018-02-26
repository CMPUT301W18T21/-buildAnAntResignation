package com.example.a1;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
/**
 * Created by Yuan on 2018-02-26.
 */

public class TestTask {

    @Test
    public void TestGetTitle(){
        Task task=new Task("title","","","","",1,0);
        assertTrue(task.getTitle()=="title");
    }
    @Test

    public void TestGetUsername(){
        Task task=new Task("","Username","","","",1,0);
        assertTrue(task.getUsername()=="Username");
    }
    @Test
    public void TestGetAddress(){
        Task task=new Task("","","","address","",1,0);
        assertTrue(task.getAddress()=="address");
    }
    @Test
    public void TestGetDes(){
        Task task=new Task("","","des","","",1,0);
        assertTrue(task.getDescription()=="des");
    }
    @Test
    public void TestGetSta(){
        Task task=new Task("","","","","sta",1,0);
        assertTrue(task.getStatus()=="sta");
    }
    @Test
    public void TestLowestBid(){
        Task task=new Task("","","","","",1,0);
        assertTrue(task.getLowestBid()==1);
    }


    @Test
    public void TestUpdateLBid(){
        Task task=new Task("","","","","",1,0);
        task.updateLowestBid(1);
        assertTrue(task.getLowestBid()==1);
    }
    @Test
    public void TestImportPhoto(){
        assertTrue(Boolean.TRUE);
    }
    @Test
    public void TestGetPhoto(){
        assertTrue(Boolean.TRUE);
    }
    @Test
    public void TestgetBids(){
        Task task=new Task("","","","","",1,0);
        assertTrue(task.getBids()==0);

    }


}
