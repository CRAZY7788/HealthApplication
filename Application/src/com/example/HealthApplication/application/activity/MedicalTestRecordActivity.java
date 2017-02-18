package com.example.HealthApplication.application.activity;

import com.example.HealthApplication.application.interfaces.presenterInterface.IMedicalTestRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.IMedicalTestRecordView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/18.
 */

public class MedicalTestRecordActivity extends MvpBaseActivity<IMedicalTestRecordView,IMedicalTestRecordPresenter> implements IMedicalTestRecordView {
    @Override
    public IMedicalTestRecordPresenter getPresenter() {
        return null;
    }
}
