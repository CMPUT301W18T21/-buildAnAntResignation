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
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;

public class UserElasticSearchController {

    private static JestDroidClient client;

    private static final String SEARCH_INDEX = "cmput301w18t21";
    private static final String SEARCH_TYPE = "userprofile";

    /**
     *  Add new user profile to online database
     *
     *  @params UserProfile: The UserProfile object of this user
     *  @progress Void
     *  @return Void
     */
    public static class AddNewUserProfileTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            User userProfile = users[0];

            // Set the unique ID for the document of this userProfile to be the uniqueUsername of this user
            String doc_ID = userProfile.getUsername();

            // Add this new user to online database
            Index index = new Index.Builder(userProfile)
                    .index(SEARCH_INDEX)
                    .type(SEARCH_TYPE)
                    .id(doc_ID)
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

            return null;
        }
    }
    /**
     * Check if the username has already been registered
     *
     * @params String: The username of this user, id of the UserProfile doc in elasticsearch
     * @progress Void
     * @return Boolean: true: The input username has already exist in online database
     *                  false: The input username does not exist
     */
    public static class CheckUserProfileExistTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            verifySettings();

            // input UserName
            String userName_IN = strings[0];

            // Check if UserProfile with id equals userName_IN exists
            Get get = new Get.Builder(SEARCH_INDEX, userName_IN)
                    .type(SEARCH_TYPE)
                    .build();

            try {
                JestResult result = client.execute(get);

                if (result.isSucceeded()) {
                    Log.d("checkUser", "Check userProfile UserName Unique Success");
                    return true;
                } else {
                    Log.d("checkUser", "Check userProfile UserName Unique Fail");
                    return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    /**
     * Get UserProfile of the given user
     *
     * @params String: The username of this user, id of the UserProfile doc in elasticsearch
     * @progress Void
     * @return UserProfile: The UserProfile with the input username
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
     * Update the UserProfile
     *
     * @params UserProfile: The UserProfile object of this user
     * @progress Void
     * @return Void
     */

    public static class UpdateUserProfileTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            User userProfile = users[0];

            // Update this userProfile to online database
            Index index = new Index.Builder(userProfile)
                    .index(SEARCH_INDEX)
                    .type(SEARCH_TYPE)
                    .id(userProfile.getUsername())
                    .build();

            try {
                DocumentResult execute = client.execute(index);

                if (execute.isSucceeded()) {
                    Log.d("update", "Update User Habits Success");
                } else {
                    Log.d("update", "Update User Habits Fail");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    /**
     * this method is from lonelytwitter class
     * Reference: git hub
     */

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


    public static void syncOnlineWithOffline(User offlineUserProfile) {
        // Sync the User Profile from the offline file
        UserElasticSearchController.UpdateUserProfileTask updateUserProfileTask = new UpdateUserProfileTask();
        //......offlineUserProfile
        updateUserProfileTask.execute(offlineUserProfile);
    }


}