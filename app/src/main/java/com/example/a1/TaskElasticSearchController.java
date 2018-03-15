package com.example.a1;

/**
 * Created by k-h on 2018-03-11.
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

public class TaskElasticSearchController {
    private static JestDroidClient client;

    private static final String SEARCH_INDEX = "cmput301w18t21";
    private static final String SEARCH_TYPE = "taskname";

    /** Add new task into the database
     * @param TaskName:
     */
    public static class AddTask extends AsyncTask<TaskName, Void, Void> {

        @Override
        protected Void doInBackground(TaskName... taskName) {
            verifySettings();

            TaskName taskName = taskName[0];

            // Set the unique ID for the document of this task to be the uniquetaskname of this task
            String doc_ID = taskName.getTitle();

            // Add this new task to online database
            Index index = new Index.Builder(taskName)
                    .index(SEARCH_INDEX)
                    .type(SEARCH_TYPE)
                    .id(doc_ID)
                    .build();

            try {
                DocumentResult execute = client.execute(index);

                if(execute.isSucceeded()){
                    Log.d("addTask", "Add addTask Success");
                }
            }
            catch (Exception e) {
                Log.i("Error", "The application failed to build");
            }

            return null;
        }
    }

    public static class CheckTaskExist extends AsyncTask<String, Void, Boolean> {

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



    public static class GetTask extends AsyncTask<String, Void, TaskName> {

        @Override
        protected TaskName doInBackground(String... strings) {
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
                    TaskName taskName = result.getSourceAsObject(TaskName.class);

                    return taskName;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }




    public static class UpdateTask extends AsyncTask<TaskName, Void, Void> {

        @Override
        protected Void doInBackground(TaskName... taskName) {
            verifySettings();

            TaskName taskName = taskName[0];

            // Update this userProfile to online database
            Index index = new Index.Builder(taskName)
                    .index(SEARCH_INDEX)
                    .type(SEARCH_TYPE)
                    .id(taskName.getTitle())
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



    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


    public static void syncOnlineWithOffline(TaskName offlineUserProfile) {
        // Sync the User Profile from the offline file
        TaskElasticSearchController.UpdateTask updateTask = new UpdateTask();
        updateTask.execute(offlineUserProfile);
    }


}