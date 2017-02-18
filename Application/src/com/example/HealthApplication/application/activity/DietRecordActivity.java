package com.example.HealthApplication.application.activity;

import com.example.HealthApplication.application.interfaces.presenterInterface.IDietRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.IDietRecordView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/18.
 */

public class DietRecordActivity extends MvpBaseActivity<IDietRecordView,IDietRecordPresenter> implements IDietRecordView {
    @Override
    public IDietRecordPresenter getPresenter() {
        return null;
    }
}
