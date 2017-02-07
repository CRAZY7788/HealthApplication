package com.example.HealthApplication.mvp.mvpbaseClass;

import com.example.HealthApplication.application.fragment.BaseFragment;
import com.example.HealthApplication.mvp.mvp_interface.IMvpBasePresenter;
import com.example.HealthApplication.mvp.mvp_interface.IMvpView;

/**
 * Created by liweihao on 17/2/5.
 */

public abstract class MvpBaseFragment<V extends IMvpView, P extends IMvpBasePresenter<V>> extends BaseFragment{
}
