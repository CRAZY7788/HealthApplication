package com.example.HealthApplication.application.utils;

/**
 * Created by liweihao on 17/3/9.
 */

public enum ClockEnum {
    CONSTANT_NIGHT("night"),
    CONSTANT_8AM("8am"),
    CONSTANT_10AM("10am"),
    CONSTANT_12PM("12pm"),
    CONSTANT_14PM("14pm"),
    CONSTANT_16PM("16pm"),
    CONSTANT_18PM("18pm"),
    CONSTANT_20PM("20pm"),
    CONSTANT_22PM("22pm");

    private String clockValue;

    ClockEnum(String clock){
        this.clockValue = clock;
    }

    public String getClock(){
        return clockValue;
    }
}
