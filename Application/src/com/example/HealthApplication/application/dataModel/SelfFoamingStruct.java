package com.example.HealthApplication.application.dataModel;

/**
 * Created by liweihao on 17/3/8.
 */

public class SelfFoamingStruct {
    public int id;
    public String date;
    public int value;

    public SelfFoamingStruct(){

    }

    public SelfFoamingStruct(int id,String date, int value){
        this.id = id;
        this.date = date;
        this.value = value;
    }

}
