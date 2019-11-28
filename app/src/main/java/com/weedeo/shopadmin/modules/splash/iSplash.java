package com.weedeo.shopadmin.modules.splash;

import android.content.Context;

public interface iSplash  {

    interface ActivityView  {

        void onSplashTimerCompleted();

        void errorPermission();

        void prmissionGranted();

        void otpSending();

        void directToProfile();
    }

    interface Presenter{
        void requestPermission(Context splashActivity);

        void onStartSplashTimer();

        void pushLogToServer(Context mContext);

        void checkAlredyLoged();
    }
}
