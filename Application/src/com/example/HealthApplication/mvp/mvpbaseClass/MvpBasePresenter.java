package com.example.HealthApplication.mvp.mvpbaseClass;

import android.app.Fragment;
import android.content.Context;

import com.example.HealthApplication.mvp.mvp_interface.IMvpBasePresenter;
import com.example.HealthApplication.mvp.mvp_interface.IMvpView;

import static com.example.HealthApplication.application.HealthApplication.getApplication;

/**
 * Created by liweihao on 17/2/5.
 */

public abstract class MvpBasePresenter<V extends IMvpView> implements IMvpBasePresenter<V> {

    private V mView;

    @Override
    public void attachView(V view) {

    }

    @Override
    public void detachView() {

    }

    protected final V getView() {
        return mView;
    }

    @Override
    public Context getApplicationContext() {
        return getApplication();
    }
}
