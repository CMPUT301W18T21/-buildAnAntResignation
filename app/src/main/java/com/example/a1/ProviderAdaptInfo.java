/*
 * ProviderAdaptInfo
 *
 * CMPUT301W18T21
 *
 * April 9, 2018
 *
 * Copyright (c) CMPUT301W18T21
 *
 */
package com.example.a1;

/**
 * Created by Alex on 2018-03-19.
 */

/**
 * this class holds the information of every tasks in the listview after searching by a provider.
 * for more information, please go to ProviderMainPage
 */
public class ProviderAdaptInfo  {
    private  String name;
    private  String task;
    private  String status;
    private  String lowestbid;

    public ProviderAdaptInfo(String name, String task, String status, String lowestbid) {
        this.name = name;
        this.task = task;
        this.status = status;
        this.lowestbid = lowestbid;
    }

    /**
     * Gets the name from the provider adapter info.
     * @return The provider adapter info's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the task from the provider adapter info.
     * @return The provider adapter info's task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Gets the status from the provider adapter info.
     * @return The provider adapter info's status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the lowest bid from the provider adapter info.
     * @return The provider adapter info's lowest bid.
     */
    public String getLowestbid() {
        return lowestbid;
    }

    /**
     * Sets the name for the provider adapter info.
     * @param name  The provider adapter info's name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the task for the provider adapter info.
     * @param task  The provider adapter info's task to be set.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Sets the status for the provider adapter info.
     * @param status  The provider adapter info's status to be set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the lowest bid for the provider adapter info.
     * @param lowestbid  The provider adapter info's name lowest bid to be set.
     */
    public void setLowestbid(String lowestbid) {
        this.lowestbid = lowestbid;
    }
}

