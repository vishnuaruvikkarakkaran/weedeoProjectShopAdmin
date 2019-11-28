package com.weedeo.shopadmin.timing;

import com.weedeo.shopadmin.base.MvpBase;

public interface TimingContract {

    interface Mvpview extends MvpBase {
       void onApiCallSuccess(String response);
    }

    interface Presenter{

        void onCallApi();

    }

}
