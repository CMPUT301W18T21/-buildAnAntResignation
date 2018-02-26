package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class Task{
    private String title;
    private String username;
    private String description;
    private String status;
    private String address;
    private ArrayList<Integer> bids=new ArrayList<Integer>();
    private Integer lowestBid;
    private ArrayList<String> photos;
    private Integer Bids;


    public  Task(String title,String username,String description,String address,String status,Integer lowestBid,Integer Bids){
        this.title=title;
        this.username=username;
        this.description=description;
        this.status=status;
        this.address=address;
        bids= new ArrayList<Integer>();
        this.lowestBid=lowestBid;
        photos=new ArrayList<String>();
        this.Bids=Bids;


    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    //Set Lowest Bid
    public void updateLowestBid(Integer lowestBid) {
        this.lowestBid = lowestBid;
    }

    public String getTitle(){
        return title;

    }
    public Integer getLowestBid(){
        return lowestBid;

    }

    public void importPhoto(){
        //
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }


    //getBids(int index)    need rewriting
    public Integer getBids(){
        return Bids;

    }
}
