/*
 * UserElasticSearchController
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
 * Created by Yuan on 2018-03-16.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class UserElasticSearchController {

    private static JestDroidClient client;

    private static final String SEARCH_INDEX = "cmput301w18t21";
    private static final String SEARCH_TYPE = "user";

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    /**
     * this method is to add a new user profile to the server.
     */
    public static class AddNewUserProfileTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for(User user:users){

            // Set the unique ID for the document of this userProfile to be the uniqueUsername of this user
                String doc_ID = user.getUsername();

            // Add this new user to online database
                Index index = new Index.Builder(user)
                    .index(SEARCH_INDEX)
                    .type(SEARCH_TYPE)
                    .id(user.getUsername())
                    .build();

                try {
                    DocumentResult execute = client.execute(index);

                    if(execute.isSucceeded()){
                        Log.d("addUser", "Add userProfile Success");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the tweets");
                }
            }
            return null;
        }
    }

    /**
     * this method is to check fi the selected user is exist in the server.
     */
    public static class CheckUserProfileExistTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            verifySettings();

            // input UserName
            User user;

            // Check if UserProfile with id equals userName_IN exists
            Get get = new Get.Builder(SEARCH_INDEX, params[0])
                    .type(SEARCH_TYPE)
                    .build();

            try {
                JestResult result = client.execute(get);
                user = result.getSourceAsObject(User.class);
                Log.i("check if exist","check user profile exist");
                if (user != null) return true;
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("exception","nooooooo exception");
            }
            return false;
        }
    }

    /**
     * this method is to get all the information of this user.
     */
    public static class GetUserProfileTask extends AsyncTask<String, Void, User> {

        @Override
        protected User doInBackground(String... strings) {
            verifySettings();
            // input UserName
            String userName_IN = strings[0];

            // Get the Document with ID equals the input username
            Get get = new Get.Builder(SEARCH_INDEX, userName_IN)
                    .type(SEARCH_TYPE)
                    .build();

            try {
                JestResult result = client.execute(get);

                if (result.isSucceeded()) {
                    User user = result.getSourceAsObject(User.class);

                    return user;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * this method is to delete the selectd user profile.
     */

    public static class DeleteUserTask extends AsyncTask<User,Void,Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            for(User user: users){
                Delete delete =new Delete.Builder(user.getUsername())
                        .index(SEARCH_INDEX)
                        .type(SEARCH_TYPE)
                        .build();


                try {
                    DocumentResult execute = client.execute(delete);

                    if (execute.isSucceeded()) {
                        Log.d("Delete successfully", "Deleted you selected user");
                    } else {
                        Log.d("Error", "Delete error");
                    }

                } catch (IOException e) {
                    Log.i("Error","Failed to build");
                }
            }
            return null;
        }

    }

    public static class QueryTask extends AsyncTask<String,Void,ArrayList<User>> {

        @Override
        protected ArrayList<User> doInBackground(String... search_parameter) {
            verifySettings();
            ArrayList<User> selecteduser = new ArrayList<User>();

            String query = "";
            Search search = new Search.Builder(search_parameter[0])
                    .addIndex(SEARCH_INDEX)
                    .addType(SEARCH_TYPE)
                    .build();
            try{
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<User> foundUser =  result.getSourceAsObjectList(User.class);
                    selecteduser.addAll(foundUser);
                } else {
                    Log.i("Error","The search query failed to find any Tasks that matched");
                }
            } catch (Exception e) {
                Log.i("Error","Something wrong");
            }


            return selecteduser;
        }
    }

}