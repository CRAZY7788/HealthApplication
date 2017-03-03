package com.example.HealthApplication.application.interfaces.presenterInterface;

import com.example.HealthApplication.application.interfaces.viewInterface.IPersonalInfoView;
import com.example.HealthApplication.mvp.mvp_interface.IMvpBasePresenter;
import com.example.HealthApplication.mvp.mvpbaseClass.MvpBasePresenter;

/**
 * Created by liweihao on 17/2/15.
 */

public interface IPersonalInfoPresenter extends IMvpBasePresenter<IPersonalInfoView> {

    void initView();

    void setName(String name);


}
