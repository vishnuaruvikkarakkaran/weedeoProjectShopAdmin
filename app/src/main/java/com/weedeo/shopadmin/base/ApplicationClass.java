package com.weedeo.shopadmin.base;

import android.app.Application;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;
import com.hypertrack.hyperlog.HyperLog;
import com.weedeo.shopadmin.CustomLogMessageFormat;
import com.weedeo.shopadmin.Utils.Constants;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        Stetho.initializeWithDefaults(this);
        HyperLog.initialize(this,new CustomLogMessageFormat(this));
        HyperLog.setLogLevel(Log.VERBOSE);
        HyperLog.setURL(Constants.LOGGING_URL);


    }
}
