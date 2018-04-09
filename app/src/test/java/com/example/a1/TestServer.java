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

/**
 * Created by Nik on 4/4/2018.
 */

import org.junit.Test;
import static org.junit.Assert.assertTrue;


/**
 * Tests the Server class.
 * These tests assume that the User and Task classes
 * work correctly and the tests for them pass.
 * @see Server
 * @see Server.UserController
 * @see Server.TaskController
 * @see User
 * @see Task
 * @see TestUser
 * @see TestTask
 */
public class TestServer {

    @Test
    /**
     * Tests the Server.UserController.add method
     * and the Server.UserController.get method
     *
     * There is just no way to test the separately,
     * and besides, they are useless without one another...
     */
    public void testUserControllerAddAndGet(){
        User user1 = new User("","test","","","","");
        Server.UserController.add(user1);
        User user2 = Server.UserController.get(user1.getUsername());
        assertTrue(user1.equals(user2));
    }

    @Test
    public void testUserControllerDelete(){


    }

    @Test
    public void testUserControllerEdit(){

    }

    @Test
    public void testUserControllerCheck(){
        assertTrue(Server.UserController.check("1"));
    }

}
