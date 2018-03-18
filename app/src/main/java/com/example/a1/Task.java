/*
 * ContactInfo
 *
 * CMPUT301W18T21
 *
 * March 10, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A representation of a task.
 * A tasks consists of a title, requester's username, description, status, and bids.
 * A tasks can also have photographs and a location added to it.
 *
 * @see User
 * @see Status
 */
public class Task{
    private String title;
    private String username;
    private String description;
    private Status status = Status.REQUESTED;;
    private String location;
    private ArrayList<Integer> bids = new ArrayList<>(0);
    private ArrayList<String> photos = new ArrayList<>(0);

    /**
     * Constructs a Task object with status REQUESTED, no bids, and no photographs.
     *
     * @param title The task's title.
     * @param username The task-requester's username.
     * @param description The task's description.
     */
    public Task(String title, String username, String description){
        this.title = title;
        this.username = username;
        this.description = description;
    }

    /**
     * Constructs a Task object.
     *
     * @param title The task's title.
     * @param username The task-requester's username.
     * @param description The task's description.
     * @param location The task's geolocation.
     * @param status The task's status.
     * @param bids The taks's bids.
     */
    public  Task(String title, String username, String description, String location, Status status, ArrayList<Integer> bids){
        this.title = title;
        this.username = username;
        this.description = description;
        this.status = status;
        this.location = location;
        bids = new ArrayList<Integer>();
        photos = new ArrayList<String>();
        this.bids = bids;
    }

    /**
     * Gets the task-requester's username.
     * @return The username of the task-requester.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the task's description.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the task's status.
     * @return The status of the task.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the geolocation of the task.
     * @return The geolocation fo the task.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the task's title.
     * @return The title of the task.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Gets the task's lowest bid.
     *
     * Source from user Marimuthu Madasamy: https://stackoverflow.com/questions/15995458/how-to-find-the-minimum-value-in-an-arraylist-along-with-the-index-number-jav#15995501
     * March 11, 2018
     *
     * @return The lowest bid of the task.
     */
    public int getLowestBid(){
        return bids.get(Collections.min(bids));
    }

    /**
     * Adds a photo to the task.
     * @param photo The address of the photo.
     */
    public void importPhoto(String photo){
        photos.add(photo);
    }

    /**
     * Gets all photos attached to the task.
     * @return A list of the task's photo's addresses.
     */
    public ArrayList<String> getPhotos() {
        return photos;
    }

    /**
     * Gets all of the task's bids.
     * @return A list of the task's bids.
     */
    public ArrayList<Integer> getBids(){
        return bids;
    }
}