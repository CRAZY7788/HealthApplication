package com.example.HealthApplication.application.presenter;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

import com.example.HealthApplication.application.dataModel.SelfRecordDataModel;
import com.example.HealthApplication.application.interfaces.presenterInterface.ISelfTestRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.ISelfTestRecordView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBasePresenter;

import java.sql.Date;

/**
 * Created by liweihao on 17/2/18.
 */

public class SelfTestRecordPresenter extends MvpBasePresenter<ISelfTestRecordView> implements ISelfTestRecordPresenter {
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    public void initAddFoamingView() {

    }

    @Override
    public void initAddProfessionalView() {

    }

    @Override
    public void initShowView() {

    }

    @Override
    public void saveData(String date, int foamingValue) {
        SelfRecordDataModel dma = new SelfRecordDataModel(getApplicationContext());
//        dma.setData();
    }
}
