package com.dabinu.apps.chesstimer.models;

import java.io.Serializable;

/**
 * Created by Taslim Oseni on 1/22/19.
 */
public class GameMode implements Serializable{

    public String name, delay, duration;


    public GameMode(){

    }


    public GameMode(String name, String delay, String duration){
        this.name = name;
        this.delay = delay;
        this.duration = duration;
    }


    public String getName(){
        return name;
    }

    public String getDelay(){
        return delay;
    }

    public String getDuration(){
        return duration;
    }


}