package com.example.HealthApplication.application.activity;

import com.example.HealthApplication.application.interfaces.presenterInterface.ISelfTestRecordPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.ISelfTestRecordView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/18.
 */

public class SelfTestRecordActivity extends MvpBaseActivity<ISelfTestRecordView,ISelfTestRecordPresenter> implements ISelfTestRecordView {
    @Override
    public ISelfTestRecordPresenter getPresenter() {
        return null;
    }
}
