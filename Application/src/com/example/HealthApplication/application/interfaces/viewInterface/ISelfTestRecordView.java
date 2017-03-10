package com.example.HealthApplication.application.interfaces.viewInterface;

import com.example.HealthApplication.mvp.mvp_interface.IMvpView;

/**
 * Created by liweihao on 17/2/18.
 */

public interface ISelfTestRecordView extends IMvpView {
    void initViewForAddFoamingCard();
    void initViewForAddProfessionalCard();
    void initViewForResultCard();
    void updateDataBase();
}
