package com.example.a1;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by k-h on 2018-03-13.
 */

public class TaskName {

    private static Map<String, User> users;

    private String title;
    private String description;
/** missing location **/
    private String image;

    private ArrayList<Integer> bidslist = new ArrayList<>(0);


    public TaskName(String name, String username, String gender, String phone, String email, String image){
        //.......
        this.title = title;
        if (username.length() <= 8) this.title = title;
        else this.title = title.subSequence(0,8).toString();

        this.description = description;
        this.image = image;
    }


    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String name){
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){return description;}

    public void addbidslist(Bid bid){
        bidslist.add(bid);
    }

    public int getLowestBid(){
        int smallest = bidslist.get(0);
        for (int i = 0; i< bidslist.size(); i++ ){
            if (bidslist.get(i) < smallest){
                smallest = bidslist.get(i);
            }
        }
        return smallest;
    }


}
