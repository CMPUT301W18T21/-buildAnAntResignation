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

import android.media.Image;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Represents a user.
 * A user has a unique username, a regular name, a gender, phone number, email, and a user image.
 * A collection of all users is kept here.
 */
public class User {

    private static Map<String, User> users;

    private String name;
    private String username;
    private String gender;
    private String phone;
    private String email;
    private String image;

    private ArrayList<Task> requestedTasks = new ArrayList<>(0);
    private ArrayList<Task> assignedTasks = new ArrayList<>(0);
    private ArrayList<Task> BiddedTasks = new ArrayList<>(0);
    private ArrayList<Integer> Bids = new ArrayList<>(0);


    /**
     * Constructs a user object.
     * @param name The user's regular name.
     * @param username The user's unique username.
     * @param gender The user's gender.
     * @param phone The user's phone number.
     * @param email The user's email address.
     * @param image The user's profile picture.
     */
    public User(String name, String username, String gender, String phone, String email, String image){
        this.name = name;
        if (username.length() <= 8) this.username = username;
        else this.username = username.subSequence(0,8).toString();
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    /**
     * Gets a user with the matching username.
     * @param username The username of the user to be returned.
     * @return The user object with the matching username.
     */
    public static User getUser(String username){
        return users.get(username);
    }

    /**
     * Gets the regular name of the user.
     * @return The user's regular name.
     */
    public String getName() {
        return this.name;

    }

    /**
     * Gets the username of the user.
     * @return The user's username.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets the gender of the user.
     * @return The user's gender.
     */
    public String getGender() {
        return  gender;
    }

    /**
     * Gets the phone number of the user.
     * @return The user's phone number.
     */
    public String getPhone() {
        return  phone;
    }

    /**
     * Gets the email address of the user.
     * @return The user's email.
     */
    public String getEmail(){
        return email;
    }

    /**
     * Gets the profile image address of the user.
     * @return The user's profile image's address.
     */
    public String getImage(){
        return image;
    }

    /**
     * Gets the specified requested task.
     * @param index The index of the task to be returned.
     * @return The requested task at the given index.
     */
    public Task getRequestedTask(int index){
        return requestedTasks.get(index);
    }

    /**
     * Gets the specified assigned task.
     * @param index The index of the task to be returned.
     * @return The assigned task at the given index.
     */
    public Task getAssignedTask(int index){
        return assignedTasks.get(index);
    }

    public Task getBiddedTask(int index) {return BiddedTasks.get(index);}
    public int getBids(int index){return Bids.get(index);}

    /**
     * Gets a list of all requested tasks the user has.
     * @return All requested tasks.
     */
    public ArrayList<Task> getRequestedTasks(){return  requestedTasks;}

    public ArrayList<Task> getBiddedTasks(){return BiddedTasks;}

    /**
     * Gets a list of all assigned tasks the user has.
     * @return All assigned tasks.
     */
    public ArrayList<Task> getAssignedTasks(){return assignedTasks;}

    /**
     * Sets the user's regular name.
     * @param name The name to be set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the user's gender.
     * @param gender The gender to be set.
     */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
     * Sets the user's phone number.
     * @param phone The phone number to be set.
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * Sets the user's email.
     * @param email The email to be set.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Sets the user's profile image.
     * @param image The address of the image to be set.
     */
    public void setImage(String image){
        this.image = image;
    }

    /**
     * Adds a task to the user's requested tasks.
     * @param task The task to be requested.
     */
    public void requestTask(Task task){
        requestedTasks.add(task);
    }

    public void assignTask (Task task){assignedTasks.add(task);}


    public void bidTask(Task task){BiddedTasks.add(task);}

    public void bids(Integer Value){Bids.add(Value);}


}