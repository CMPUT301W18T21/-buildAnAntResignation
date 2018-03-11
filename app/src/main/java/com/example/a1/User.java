package com.example.a1;

import android.media.Image;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Alex on 2018-02-26.
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


    public User(String name, String username, String gender, String phone, String email, String image){
        this.name = name;
        if (username.length() <= 8) this.username = username;
        else this.username = username.subSequence(0,8).toString();
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;

    }

    public static User getUser(String username){
        return users.get(username);
    }

    public String getName() {
        return this.name;

    }

    public String getUsername(){
        return username;
    }

    public String getGender() {
        return  gender;
    }

    public String getPhone() {
        return  phone;
    }

    public String getEmail(){
        return email;
    }

    public String getImage(){
        return image;
    }

    public Task getRequestedTask(int index){
        return requestedTasks.get(index);
    }

    public Task getAssignedTask(int index){
        return assignedTasks.get(index);
    }

    public ArrayList<Task> getRequestedTasks(){return  requestedTasks;}

    public ArrayList<Task> getAssignedTasks(){return assignedTasks;}

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void requestTask(Task task){
        requestedTasks.add(task);
    }
}