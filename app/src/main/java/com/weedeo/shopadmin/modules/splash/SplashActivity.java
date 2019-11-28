package com.weedeo.shopadmin.modules.splash;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.hypertrack.hyperlog.HyperLog;

import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.base.BaseActivity;
import com.weedeo.shopadmin.modules.phonesigninpage.PhoneSignInActivity;
import com.weedeo.shopadmin.modules.profile.ProfileActivity;

public class SplashActivity extends BaseActivity implements iSplash.ActivityView {

    private SplashPresenter splashPresenter;
    private String TAG="SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Device Density : ", Utils.getDeviceDensity(this));


        HyperLog.i(TAG,"Activity Started");
      //  AWSMobileClient.getInstance().initialize(this).execute();
        splashPresenter = new SplashPresenter(this, this);
        splashPresenter.requestPermission(this);


    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void onSplashTimerCompleted() {
        HyperLog.i(TAG,"onSplashTimerCompleted - Executed");
        splashPresenter.checkAlredyLoged();
        splashPresenter.pushLogToServer(this);

    }

    @Override
    public void errorPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void prmissionGranted() {
        splashPresenter.onStartSplashTimer();

    }

    @Override
    public void otpSending() {
        Log.e("new member","new member");
        Intent loginIntent = new Intent(SplashActivity.this, PhoneSignInActivity.class);
        startActivity(loginIntent);
        this.finish();
    }

    @Override
    public void directToProfile() {
        Log.e("alreedy a member","already a member");
            Intent intent=new Intent(this, ProfileActivity.class);
            startActivity(intent);
            this.finish();
    }


    private void openSettings() {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        }

}
