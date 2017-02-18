package com.example.HealthApplication.application.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;

import com.example.HealthApplication.R;
import com.example.HealthApplication.application.interfaces.presenterInterface.IPersonalInfoPresenter;
import com.example.HealthApplication.application.interfaces.viewInterface.IPersonalInfoView;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBaseActivity;

/**
 * Created by liweihao on 17/2/15.
 */

public class PatientInfoActivity extends MvpBaseActivity<IPersonalInfoView,IPersonalInfoPresenter> implements IPersonalInfoView {


    @Override
    public void onCreateActivity(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.base_activity_layout);
    }

    @Override
    public IPersonalInfoPresenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {

    }
}
