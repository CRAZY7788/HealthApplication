package com.example.HealthApplication.application;

import android.app.Application;

/**
 * Created by liweihao on 17/2/15.
 */

public class HealthApplication extends Application {
    private static HealthApplication sApplication;

    public HealthApplication(){
        sApplication = this;
    }

    public static HealthApplication getApplication() {
        return sApplication;
    }
}
