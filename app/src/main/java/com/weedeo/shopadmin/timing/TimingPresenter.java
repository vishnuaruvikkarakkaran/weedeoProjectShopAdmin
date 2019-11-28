package com.weedeo.shopadmin.timing;

import android.content.Context;

public class TimingPresenter implements TimingContract.Presenter {

    private TimingContract.Mvpview mvpview;
    private Context mContext;

    public TimingPresenter(TimingContract.Mvpview mvpview, Context mContext) {
        this.mvpview = mvpview;
        this.mContext = mContext;
    }

    @Override
    public void onCallApi() {
        mvpview.onApiCallSuccess("fwefwefwf");
    }
}
