package com.example.HealthApplication.mvp.mvp_interface;

/**
 * Created by liweihao on 17/2/5.
 */

public interface IMvpBasePresenter<V extends IMvpView> extends Cloneable {

    void attachView(V view);

    //invoked when view is destroyed
    void detachView();

}
