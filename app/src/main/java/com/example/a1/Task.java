/*
 * Task
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A representation of a task.
 * A tasks consists of a title, requester's username, description, status, and bid.
 * A tasks can also have photographs and a location added to it.
 *
 * @see User
 * @see Status
 */
public class Task implements Cloneable{

    private String title;
    private String requesterName;
    private String providerName;
    private String description;
    private Status status = Status.REQUESTED;
    private Double latitude;
    private Double longitude;
    private String location;
    private String photo;
    private ArrayList<Integer> bids = new ArrayList<>(0);
    private ArrayList<String> bidders = new ArrayList<>(0);


    /**
     * Constructs a Task object with status REQUESTED, no bid, and no photographs.
     *
     * @param title The task's title.
     * @param requesterName The task-requester's username.
     * @param description The task's description.
     */
    public Task(String title, String requesterName, String description){
        this.title = title;
        this.requesterName = requesterName;
        this.description = description;
    }


    /**
     * Constructs a Task object.
     *
     * @param title The task's title.
     * @param requesterName The task-requester's username.
     * @param description The task's description.
     * @param location The task's geolocation.
     * @param status The task's status.
     * @param bids The task's bid.
     */
    public  Task(String title, String requesterName, String description, String location, Status status, String photo, ArrayList<Integer> bids){
        this.title = title;
        this.requesterName = requesterName;
        this.description = description;
        this.status = status;
        this.location = location;
        this.bids = bids;
        this.photo = photo;
    }


    /**
     * Constructs a Task object.
     *
     * @param title The task's title.
     * @param requesterName The task-requester's username.
     * @param providerName THe task-provider's username.
     * @param description The task's description.
     * @param location The task's geolocation.
     * @param status The task's status.
     * @param bids The task's bid.
     *
     */
    public  Task(String title, String requesterName, String providerName, String description, String location, Status status, String photo, ArrayList<Integer> bids){
        this.title = title;
        this.requesterName = requesterName;
        this.providerName = providerName;
        this.description = description;
        this.status = status;
        this.location = location;
        this.bids = bids;
        this.photo = photo;
    }

    /**
     * Gets the task-requester's username.
     * @return The username of the task-requester.
     */
    public String getRequesterName() {
        return requesterName;
    }
    /**
     * Gets the task-provider's username.
     * @return The username of the task-provider.
     */
    public String getProviderName(){return providerName; }


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
     * @return The lowest bid of the task. Returns -1 if task has no bids.
     */
    public int getLowestBid(){
        if(bids.size() == 0) return -1;
        return Collections.min(bids);
    }


    /**
     * Gets all photos attached to the task.
     * @return A list of the task's photo's addresses.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Gets all of the task's bid.
     * @return A list of the task's bid.
     */
    public ArrayList<Integer> getBids(){
        return bids;
    }

    public String getBidder(Integer index) {return bidders.get(index);}

    public ArrayList<String> getBidders() {return bidders;}

    /**
     * Add a bid to bid arrayList
     */
    public void addBid(Integer bid){
        bids.add(bid);
    }
    /**
     * add the bidder to bidders list.
     */

    public void addBidder(String username){
        bidders.add(username);
    }

    /**
     * delete a certain bid in the list.
     * @param index
     */

    public void deleteBid(int index) {bids.remove(index);}

    /**
     * delete a certain bidder in the list.
     * @param index
     */

    public void deleteBidder(int index) {bidders.remove(index);}

    /**
     * Sets teh task's status to BIDDED.
     */
    public void setBidded(){
        this.status = Status.BIDDED;
    }

    /**
     * Sets teh task's status to ASSIGNED.
     */
    public void setAssigned(){
        this.status = Status.ASSIGNED;
    }

    /**
     * Sets teh task's status to REQUESTED.
     */
    public void setRequested(){this.status = Status.REQUESTED;}

    /**
     * Sets teh task's status to DONE.
     */
    public void setDone(){
        this.status =  Status.DONE;
    }

    /**
     * setting the title of the task.
     * @param title
     */
    public void setTitle(String title){this.title = title;}

    /**
     * setting the requestername of the task.
     * @param username
     */
    public void setRequesterName(String username){this.requesterName = username;}

    /**
     * setting the providername of the task.
     * @param username
     */
    public void setProviderName(String username){this.providerName = username;}

    /**
     * add the description of the task.
     * @param description
     */
    public void setDescription(String description){this.description = description;}

    /**
     * add the photo to the task.
     * @param photo
     */
    public void setPhoto(String photo){this.photo = photo;}

    /**
     * add the location of the task.
     * @param location
     */

    public void setLocation(String location){this.location = location;}

    /**
     * get the longitude of the task.
     * @return
     */

    public Double getLongitude(){
        return longitude;
    }
    /**
     *set the longitude of the task.
     */

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    /**
     * get the latitude of the task.
     * @return
     */

    public Double getLatitude(){
        return latitude;
    }

    /**
     * set the latitude of the task.
     * @param latitude
     */

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    /**
     *
     * Source: User Bhasker Tiwari from https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java#869078
     *          April 6th, 2018
     * @return
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}