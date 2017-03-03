package com.example.HealthApplication.application.interfaces.presenterInterface;

import com.example.HealthApplication.application.interfaces.viewInterface.ISelfTestRecordView;
import com.example.HealthApplication.mvp.mvp_interface.IMvpBasePresenter;

/**
 * Created by liweihao on 17/2/18.
 */

public interface ISelfTestRecordPresenter extends IMvpBasePresenter<ISelfTestRecordView> {
    void initAddFoamingView();
    void initAddProfessionalView();
    void initShowView();
}
