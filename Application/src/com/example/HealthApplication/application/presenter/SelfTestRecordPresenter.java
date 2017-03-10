package com.example.HealthApplication.application.presenter;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

import com.example.HealthApplication.application.dataModel.SelfRecordDataModel;
import com.example.HealthApplication.application.interfaces.presenterInterface.ISelfTestRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.ISelfTestRecordView;
import com.example.HealthApplication.application.utils.ClockEnum;
import com.example.HealthApplication.application.utils.KeyEnum;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBasePresenter;

import java.security.Key;
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

    /**
     * clock: night,8am,10am,12pm,14pm,16pm,18pm,20pm,22pm
     * @param date
     * @param clock
     * @param value
     */
    @Override
    public void setSingleDayValue(String date, ClockEnum clock, int value) {
        SelfRecordDataModel dma = new SelfRecordDataModel(getApplicationContext());
        dma.setPreferenceValue(clock.getClock()+KeyEnum.KEY_VALUE,value);
        if(dma.getPreferenceValue(KeyEnum.KEY_DATE) < 1){
            dma.setPreferenceValue(KeyEnum.KEY_DATE,1);
        }
        //last clock to calculate total and save in database
        if(clock.getClock().contains("22")){
            dma.setPreferenceValue(KeyEnum.KEY_DATE,dma.getPreferenceValue(KeyEnum.KEY_DATE)+1);

            int totalForTheDay = 0;
            totalForTheDay = dma.getPreferenceValue(ClockEnum.CONSTANT_NIGHT+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_8AM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_10AM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_12PM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_14PM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_16PM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_18PM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_20PM+KeyEnum.KEY_VALUE) +
                    dma.getPreferenceValue(ClockEnum.CONSTANT_22PM+KeyEnum.KEY_VALUE);


            dma.setData(dma.getPreferenceValue(KeyEnum.KEY_DATE),value,date);

        }
    }


}
