package com.example.HealthApplication.application.presenter;

import android.content.Context;

import com.example.HealthApplication.application.interfaces.presenterInterface.IPersonalInfoPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.IPersonalInfoView;
import com.example.HealthApplication.mvp.mvp_interface.IMvpBasePresenter;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBasePresenter;

/**
 * Created by liweihao on 17/2/18.
 */

public class PatientInfoPresenter extends MvpBasePresenter<IPersonalInfoView> implements IPersonalInfoPresenter {


    @Override
    public void initView() {
        getView().initView();
    }

    @Override
    public void setName(String name) {

    }
}
