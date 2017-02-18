package com.example.HealthApplication.application.activity;

import com.example.HealthApplication.application.interfaces.presenterInterface.IDataSummaryPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.IDataSummaryView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/18.
 */

public class DataSummaryActivity extends MvpBaseActivity<IDataSummaryView,IDataSummaryPresenter> implements IDataSummaryView {
    @Override
    public IDataSummaryPresenter getPresenter() {
        return null;
    }
}
