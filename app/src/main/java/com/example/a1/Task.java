package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class Task{
    private String title;
    private String username;
    private String description;
    private Status status = Status.REQUESTED;;
    private String location;
    private ArrayList<Integer> bids = new ArrayList<>(0);
    private int lowestBid;
    private ArrayList<String> photos = new ArrayList<>(0);

    public Task(String title, String username, String description){
        this.title = title;
        this.username = username;
        this.description = description;
    }

    public  Task(String title, String username, String description, String location, Status status, int lowestBid, ArrayList<Integer> bids){
        this.title = title;
        this.username = username;
        this.description = description;
        this.status = status;
        this.location = location;
        bids = new ArrayList<Integer>();
        this.lowestBid = lowestBid;
        photos = new ArrayList<String>();
        this.bids = bids;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public void updateLowestBid(Integer lowestBid) {
        this.lowestBid = lowestBid;
    }

    public String getTitle(){
        return title;
    }

    public Integer getLowestBid(){
        return lowestBid;
    }

    public void importPhoto(String photo){
        photos.add(photo);
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public ArrayList<Integer> getBids(){
        return bids;
    }
}
