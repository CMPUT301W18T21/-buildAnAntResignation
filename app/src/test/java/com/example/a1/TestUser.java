package com.example.a1;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 2018-02-26.
 */

public class TestUser {

    @Test
    public void getName(){

        User user = new User("laoxu","","","","","");
        assertTrue(user.getName() == "laoxu");
    }

    @Test
    public void setName(){

        User user = new User("laoxu","","","","","");
        user.setName("hahaha");
        assertTrue(user.getName() == "hahaha");
    }

    @Test
    public void getUser(){
        User user = new User("","Alex","","","","");
        assertTrue(user.getUsername() == "Alex");
    }

    @Test
    public void getGender() {

        User user = new User("", "", "male", "", "", "");
        assertTrue(user.getGender() == "male");
    }

    @Test

    public void getPhone() {

        User user = new User("", "", "", "7807801234", "", "");
        assertTrue(user.getPhone() == "7807801234");
    }

    @Test
    public void getAddress() {

        User user = new User("", "", "", "", "10712-45ave NW", "");
        assertTrue(user.getAddress() == "10712-45ave NW");
    }


    @Test
    public void setGender(){

        User user = new User("","","male","","","");
        user.setGender("female");
        assertTrue(user.getGender() == "female");

    }

    @Test
    public void setPhone(){

        User user = new User("","","","7807109999","","");
        user.setPhone("7807108888");
        assertTrue(user.getPhone() == "7807108888");

    }

    @Test
    public void setAddress(){

        User user = new User("","","","","10712-45ave NW","");
        user.setAddress("10000-45ave NW");
        assertTrue(user.getAddress() == "10000-45ave NW");

    }
    @Test
    public void setImage(){

        User user = new User("","","","","","img");
        assertTrue(user.getImage() == "img");

    }




}
