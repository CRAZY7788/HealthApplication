package com.example.HealthApplication.mvp.mvp_interface;

import android.content.Context;

/**
 * Created by liweihao on 17/2/5.
 */

public interface IMvpBasePresenter<V extends IMvpView>{

    void attachView(V view);

    //invoked when view is destroyed
    void detachView();

    Context getApplicationContext();

}
