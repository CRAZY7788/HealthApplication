package com.example.HealthApplication.application.activity;

import com.example.HealthApplication.application.interfaces.presenterInterface.IFitnessRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.IFitnessRecordView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/18.
 */

public class FitnessRecordActivity extends MvpBaseActivity<IFitnessRecordView,IFitnessRecordPresenter> implements IFitnessRecordView {

    @Override
    public IFitnessRecordPresenter getPresenter() {
        return null;
    }
}
