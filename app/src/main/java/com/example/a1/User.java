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

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a user.
 * A user has a unique username, a regular name, a gender, phone number, email, and a user image.
 * A collection of all users is kept here.
 */
public class User {

    private static String currentUser;

    private String name;
    private String username;
    private String gender;
    private String phone;
    private String email;
    private Uri image;


    /**
     * the array of tasks that provider has requested
     */
    private ArrayList<Task> requestedTasks = new ArrayList<>(0);
    /**
     * the array of tasks that are assigned
     */
    private ArrayList<Task> assignedTasks = new ArrayList<>(0);
    /**
     * the array of tasks that provider has bidded
     */
    private ArrayList<Task> providedTasks;

    private ArrayList<Task> biddedTasks = new ArrayList<>(0);

    /**
     * Constructs a user object.
     */
    public User(){}

    /**
     * Constructs a user object.
     * @param name The user's regular name.
     * @param username The user's unique username.
     * @param gender The user's gender.
     * @param phone The user's phone number.
     * @param email The user's email address.
     * @param image The user's profile picture.
     */
    public User(String name, String username, String gender, String phone, String email, Uri image){
        this.name = name;
        if (username.length() <= 8) this.username = username;
        else this.username = username.subSequence(0,8).toString();
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    /**
     * Constructs a user object.
     * @param name The user's regular name.
     * @param username The user's unique username.
     * @param gender The user's gender.
     * @param phone The user's phone number.
     * @param email The user's email address.
     * @param image The user's profile picture.
     * @param requestedTasks The user's requested tasks.
     * @param providedTasks The user's provided tasks.
     */
    public User(String name, String username, String gender, String phone, String email, Uri image,ArrayList<Task> requestedTasks,ArrayList<Task> providedTasks){
        this.name = name;
        if (username.length() <= 8) this.username = username;
        else this.username = username.subSequence(0,8).toString();
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.requestedTasks = requestedTasks;
        this.providedTasks = providedTasks;
    }

    /**
     * Constructs a user object.
     * @param name The user's regular name.
     * @param username The user's unique username.
     * @param gender The user's gender.
     * @param phone The user's phone number.
     * @param email The user's email address.
     * @param image The user's profile picture.
     * @param requestedTasks The user's requested tasks.
     * @param providedTasks The user's provided tasks.
     * @param biddedTasks The user's tasks on which he bid.
     */
    public User(String name, String username, String gender, String phone, String email, Uri image,ArrayList<Task> requestedTasks,ArrayList<Task> providedTasks, ArrayList<Task> biddedTasks){
        this.name = name;
        if (username.length() <= 8) this.username = username;
        else this.username = username.subSequence(0,8).toString();
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.requestedTasks = requestedTasks;
        this.providedTasks = providedTasks;
        this.biddedTasks = biddedTasks;
    }

    /**
     * Keeps track of the logged in user's username. This is used to distinguish
     * the logged in user from other users in the contact info screen.
     * @param username The logged in user's username
     * @see ContactInfo
     */
    public static void setCurrentUser(String username){
        currentUser = username;
    }

    /**
     * Keeps track of the logged in user's username. This is used to distinguish
     * the logged in user from other users in the contact info screen.
     * @return The username of the logged in user.
     * @see ContactInfo
     */
    public static String getCurrentUser(){
        return currentUser;
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
    public Uri getImage(){
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
     * Gets a list of all requested tasks the user has.
     * @return All requested tasks.
     */
    public ArrayList<Task> getRequestedTasks(){return  this.requestedTasks;}


    /**
     * Gets the specified index of provider's provided tasks
     * @param index the index of the task
     * @return  the provider's task
     */
    public Task getProvidedTask(int index){return providedTasks.get(index);}

    /**
     * Gets a list of all provided tasks the user has provided.
     * @return All provided tasks.
     */
    public ArrayList<Task> getProvidedTasks(){return  this.providedTasks;}

    /**
     * Gets the specified assigned task.
     * @param index The index of the task to be returned.
     * @return The assigned task at the given index.
     */
    public Task getAssignedTask(int index){
        return assignedTasks.get(index);
    }

    /**
     * Gets a list of all assigned tasks the user has.
     * @return All assigned tasks.
     */
    public ArrayList<Task> getAssignedTasks(){return assignedTasks;}

    /**
     * Sets the user's username.
     * @param username The name to be set.
     */
    public void setUsername(String username){
        this.username = username;
    }

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
    public void setImage(Uri image){
        this.image = image;
    }

    /**
     * Adds a task to the user's requested tasks.
     * @param task The task to be requested.
     */
    public void requestTask(Task task){
        requestedTasks.add(task);
    }

    /**
     * Adds a task to the user's provided tasks.
     * @param task The task to be provided.
     */
    public void provideTask(Task task){
        Task newTask;
        try {
            newTask = (Task) task.clone();
            newTask.setAssigned();
            newTask.setProviderName(username);
            providedTasks.add(task);
            Server.UserController.edit(this);
            Server.TaskController.edit(task,newTask);
        } catch (CloneNotSupportedException e){
            return;
        }
    }

    /**
     * Checks if a user has a matching name in his requested tasks.
     * @param title
     * @return True if match found, false if not.
     */
    public Boolean checkRequestedTitle(String title){
        for (Task task: requestedTasks) {
            if( task.getTitle().equals(title))
                return true;
        }
        return false;
    }

    /**
     * Checks if a user has a matching name in his provided tasks.
     * @param title
     * @return True if match found, false if not.
     */
    public Boolean checkProvidedTitle(String title){
        for (Task task: providedTasks) {
            if( task.getTitle().equals(title))
                return true;
        }
        return false;
    }


    /**
     * Removes a task from the user's requested tasks.
     * @param task The task to be deleted.
     */
    public void deleteRequestedTask(Task task){
        for (int i = 0; i < requestedTasks.size(); i++) {
            if (requestedTasks.get(i).getTitle().equals(task.getTitle())) {
                requestedTasks.remove(i);
                break;
            }
        }

    }

    /**
     * Removes a task from the user's provided tasks.
     * @param task The task to be deleted.
     */
    public void deleteProvidedTask(Task task){
        for (int i = 0; i < providedTasks.size(); i++) {
            if( providedTasks.get(i).getTitle().equals(task.getTitle())) {
                providedTasks.remove(i);
                break;
            }
        }
    }

    /**
     * Replaces an existing requested task, with a new task.
     * @param task The task to be edited.
     * @param title The new title of the task.
     * @param description The new description of the task.
     */
    public void editRequestedTask(Task task, String title, String description){
        int index = requestedTasks.indexOf(task);
        requestedTasks.get(index).setTitle(title);
        requestedTasks.get(index).setDescription(description);
    }

    /**
     * Sets an ArrayList of Tasks as the users requested tasks.
     * @param requestedTasks The array list of tasks to be set.
     */
    public void setRequestedTasks(ArrayList<Task> requestedTasks){this.requestedTasks = requestedTasks;}

    /**
     * Sets an ArrayList of Tasks as the users provided tasks.
     * @param providedTasks The array list of tasks to be set.
     */
    public void setProvidedTasks(ArrayList<Task> providedTasks){this.providedTasks = providedTasks;}

    /**
     * Adds a task to the user's bidded tasks.
     * @param task The task to to bid on.
     */
    public void bidTask(Task task){
        biddedTasks.add(task);
    }

    /**
     * Returns a list of all tasks that the user has bidded on.
     */
    public ArrayList<Task> getBiddedTasks(){
        return biddedTasks;
    }
}