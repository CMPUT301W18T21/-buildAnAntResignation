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

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }

    public String getLowestbid() {
        return lowestbid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLowestbid(String lowestbid) {
        this.lowestbid = lowestbid;
    }
}

