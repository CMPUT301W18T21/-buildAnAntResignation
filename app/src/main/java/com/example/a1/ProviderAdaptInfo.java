package com.example.a1;



/**
 * Created by Alex on 2018-03-19.
 */

public class ProviderAdaptInfo  {
    private  String name;
    private  String task;
    private  String status;
    private  Integer lowestbid;

    public ProviderAdaptInfo(String name, String task, String status, Integer lowestbid) {
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

    public Integer getLowestbid() {
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

    public void setLowestbid(Integer lowestbid) {
        this.lowestbid = lowestbid;
    }
}

