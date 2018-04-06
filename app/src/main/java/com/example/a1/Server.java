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

import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Represents a controller for interacting with the Elasticsearch server.
 * It acts a kind of front end for the UserElasticSearchController, allowing
 * for easier use of its functions.
 *
 * The class allows getting, editing, and removing data from the Elasticsearch server.
 *
 * It contains two static nested classes, UserController and TaskController, controlling
 * the User and Task classes' interaction with the server.
 *
 * @see UserElasticSearchController
 */
public class Server {

    private static UserElasticSearchController.GetUserProfileTask           getUserTask;
    private static UserElasticSearchController.AddNewUserProfileTask        addUserTask;
    private static UserElasticSearchController.CheckUserProfileExistTask    checkUserTask;
    private static UserElasticSearchController.DeleteUserTask               deleteUserTask;

    /**
     * Represents the User controller.
     * It handles interaction of User instances
     * with the Elasticsearch server.
     * @see com.example.a1.User
     */
    public static class UserController{

        /**
        * Adds a user to the server.
        * If user already exists, does not add it.
        * @param user The user to be added to the server.
        */
        public static void add(User user){

            //checks if user already exists on server
            //if so, do not add the user.
            if(check(user.getUsername()))
                return;

            addUserTask = new UserElasticSearchController.AddNewUserProfileTask();
            addUserTask.execute(user);
        }

        /**
        * Deletes a user from the server.
        * @param user The user to be deleted.
        */
        public static void delete(User user){
            deleteUserTask = new UserElasticSearchController.DeleteUserTask();
            deleteUserTask.execute(user);
        }

        /**
         * Deletes a user from the server.
         * @param username The to-be-deleted user's username from the server.
         */
        public static void delete(String username){
            User user = get(username);
            delete(user);
        }

        /**
         * Edits an existing user on the server.
         * @param user The edited user instance to be synced to the server.
         */
        public static void edit(User user){
            //Remove the original instance of the user
            User oldInstance = get(user.getUsername());
            delete(oldInstance);
            //Add the new instance of the user
            add(user);
        }

        /**
         * Gets a user from the server.
         * @param username The username of the user to be retrieved from the server.
         * @return The user with the matching username. If user not found, return null.
         */
        public static User get(String username){

            //checks if user exists on server.
            if(!check(username)) return null;

            User user;
            getUserTask = new UserElasticSearchController.GetUserProfileTask();
            getUserTask.execute(username);
            try {
                user = getUserTask.get();
                Log.i("test",user.toString());
            } catch (Exception e) {
                user = null;
            }

            return user;

        }

        /**
         * Checks if a user exists on the server.
         * @param username The username to be searched for in the server.
         * @return True if user exists, false if user does not.
         */
        public static Boolean check(String username){
            checkUserTask = new UserElasticSearchController.CheckUserProfileExistTask();
            checkUserTask.execute(username);
            Boolean exists;
            try {
                exists = checkUserTask.get();
            } catch (Exception e){
                exists = false;
            }
            if(exists == null) exists = false;
            return exists;
        }

    }

    /**
     * Represents the Task controller.
     * It handles interaction of Task instances
     * with the Elasticsearch server.
     * @see com.example.a1.Task
     */
    public static class TaskController{

        /**
         * Edits an existing task on the server.
         * @param oldTask The original non-edited task.
         * @param newTask The new edited task to be uploaded to the server.
         */
        public static void edit(Task oldTask, Task newTask){
            User provider = UserController.get(oldTask.getProviderName());
            User requester = UserController.get(oldTask.getRequesterName());


            provider.deleteProvidedTask(oldTask);
            requester.deleteRequestedTask(oldTask);

            provider.provideTask(newTask);
            requester.requestTask(newTask);

            UserController.edit(provider);
            UserController.edit(requester);
        }

        /**
         * Returns a list of all requested tasks.
         */
        public static void getAll(){

        }


    }

}
