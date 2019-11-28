package com.weedeo.shopadmin.timing;

import android.content.Context;
import android.os.Bundle;

import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.base.BaseActivity;

public class TimingActivity extends BaseActivity implements TimingContract.Mvpview {

    private TimingPresenter timingPresenter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        timingPresenter = new TimingPresenter(this, mContext);
        timingPresenter.onCallApi();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_timing;
    }

    @Override
    public void onShowProgress() {

    }

    @Override
    public void onHideProgress() {

    }

    @Override
    public void onShowToast(String message) {

    }

    @Override
    public void onShowSnackBar(String message) {
    }

    @Override
    public void onShowAlertDialog(String message) {

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onApiCallSuccess(String response) {

    }
}
