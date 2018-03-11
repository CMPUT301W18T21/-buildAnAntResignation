package com.example.a1;

import android.media.Image;
import android.os.Bundle;

/**
 * Created by Alex on 2018-02-26.
 */

public class User {

    private String name;
    private String userName;
    private String gender;
    private String phone;
    private String address;
    private String image;

    public User(String name, String userName, String gender, String phone, String address, String image){
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.image = image;

    }

    public String getName() {
        return this.name;

    }

    public String getUsername(){
        return userName;
    }

    public String getGender() {
        return  gender;
    }

    public String getPhone() {
        return  phone;
    }

    public String getAddress(){
        return  address;
    }

    public String getImage(){
        return image;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setImage(String image){
        this.image = image;
    }


}