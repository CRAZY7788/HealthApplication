package com.example.HealthApplication.application.activity;

import android.os.Bundle;

import com.example.HealthApplication.R;
import com.example.HealthApplication.application.interfaces.presenterInterface.ISelfTestRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.ISelfTestRecordView;
import com.example.HealthApplication.application.presenter.PatientInfoPresenter;
import com.example.HealthApplication.application.presenter.SelfTestRecordPresenter;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/18.
 */

public class SelfTestRecordActivity extends MvpBaseActivity<ISelfTestRecordView,ISelfTestRecordPresenter> implements ISelfTestRecordView {

    SelfTestRecordPresenter mPresenter;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.ll_self_record_activity_layout);
        mPresenter = new SelfTestRecordPresenter();

        getPresenter().initAddFoamingView();
    }

    @Override
    public void onResume(){

    }

    @Override
    public ISelfTestRecordPresenter getPresenter() {
        return null;
    }

    private void initResource(){
        
    }

    @Override
    public void initViewForAddFoamingCard() {

    }

    @Override
    public void initViewForAddProfessionalCard() {

    }

    @Override
    public void initViewForResultCard() {

    }

    @Override
    public void updateDataBase() {

    }
}
