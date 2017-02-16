package com.example.HealthApplication.mvp.mvpbaseClass;

import android.os.Bundle;

import com.example.HealthApplication.application.activity.HealthBaseActivity;
import com.example.HealthApplication.mvp.mvp_interface.IMvpBasePresenter;
import com.example.HealthApplication.mvp.mvp_interface.IMvpView;

/**
 * Created by liweihao on 17/2/5.
 */

public abstract class MvpBaseActivity<V extends IMvpView, P extends IMvpBasePresenter<V>> extends HealthBaseActivity {

    abstract public P getPresenter();


    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    public void onCreateActivity(final Bundle savedInstanceState) {
        super.onCreateActivity(savedInstanceState);
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }


    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    protected void onPostCreateActivity(Bundle savedInstanceState) {
        super.onPostCreateActivity(savedInstanceState);
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    protected void onStartActivity() {
        super.onStartActivity();
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    protected void onResumeActivity() {
        super.onResumeActivity();
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    protected void onPauseActivity() {
        super.onPauseActivity();
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    protected void onStopActivity() {
        super.onStopActivity();
        P presenter = getPresenter();
        if (presenter != null) {
            getPresenter().detachView();
        }
    }

    @Override
    @SuppressWarnings({"ConstantConditions", "unchecked"})  //Null checks needed for leaks
    protected void onDestroy() {
        super.onDestroy();
        P presenter = getPresenter();
        if (presenter != null) {
            getPresenter().detachView();
        }
    }

}
