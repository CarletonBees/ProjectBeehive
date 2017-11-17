package com.example.benjamin.beehiveapp;

/**
 * Created by Benjamin on 2017-10-31.
 */

public class BeePacket {
    private float humidity;
    private float temperature;
    private int hiveNumber;

    public BeePacket(float h, float t){
        temperature = t;
        humidity = h;
        hiveNumber = 1;
    }
    public BeePacket(float h, float t, int n){
        temperature = t;
        humidity = h;
        hiveNumber = n;
    }

    public void setTemperature(float t){
        temperature = t;
    }
    public void setHumidity(float h){
        humidity = h;
    }
    public void setHiveNumber(int n){
        hiveNumber = n;
    }

    public float getTemperature(){
        return temperature;
    }
    public float getHumidity(){
        return humidity;
    }
    public int getHiveNumber(){
        return hiveNumber;
    }
}
