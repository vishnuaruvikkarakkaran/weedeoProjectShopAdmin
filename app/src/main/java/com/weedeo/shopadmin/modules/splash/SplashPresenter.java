package com.weedeo.shopadmin.modules.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.hypertrack.hyperlog.HyperLog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;

import java.io.File;
import java.util.List;

public class SplashPresenter implements iSplash.Presenter{

    private iSplash.ActivityView mvpView;
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private String TAG = "SplashPresenter";
    private Context context;
    private String token="";
    private String user_id="";


    public SplashPresenter(iSplash.ActivityView mvpView ,Context context ) {
        this.mvpView = mvpView;
        this.context = context;
    }


    @Override
    public void requestPermission(Context splashActivity) {

        Dexter.withActivity((Activity) splashActivity)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                        //    Toast.makeText(context, "All permissions are granted!", Toast.LENGTH_SHORT).show();
                            mvpView.prmissionGranted();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            mvpView.errorPermission();
                        }
                    }



                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(context, "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();


    }

    @Override
    public void onStartSplashTimer() {
        Log.e("splash timer","started.......");
        HyperLog.i(TAG,"onStartSplashTimer - Executed");
        /* New Handler to start the Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(() -> {
            HyperLog.i(TAG,"Splash screen timer completed");
            mvpView.onSplashTimerCompleted();
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void pushLogToServer(Context mContext) {

        HyperLog.i(TAG, "pushLogToServer - Executed");

        File file = HyperLog.getDeviceLogsInFile(mContext);

        if (Utils.LoadPreferences(context, Constants.KEY_SHOP_ID).length() > 0) {

            Log.e("hyperlog","hyper log in.......");

            try {

                if (file != null) {
                    HyperLog.i(TAG, "Log file present");
                    BasicAWSCredentials credentials = new BasicAWSCredentials(Constants.AWS_ACCESS_KEY, Constants.AWS_SECRET_KEY);
                    AmazonS3Client s3Client = new AmazonS3Client(credentials);

                    TransferUtility transferUtility =
                            TransferUtility.builder()
                                    .context(mContext)
                                    .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                                    .s3Client(s3Client)
                                    .build();

                    // "logs" will be the folder that contains the file
                    TransferObserver uploadObserver =
                            transferUtility.upload(Constants.AWS_BUCKET_NAME, Constants.LOG_PATH + Utils.LoadPreferences(context, Constants.KEY_SHOP_ID) + "/" + file.getName(), file);

                    uploadObserver.setTransferListener(new TransferListener() {

                        @Override
                        public void onStateChanged(int id, TransferState state) {
                            if (TransferState.COMPLETED == state) {
                                HyperLog.i(TAG, "Log file Upload Successfully");
                                //  Toast.makeText(mContext, "Upload Complete", Toast.LENGTH_SHORT).show();
                                HyperLog.deleteLogs();
                                // Handle a completed download.
                            }
                        }

                        @Override
                        public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                            float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                            int percentDone = (int) percentDonef;
                        }

                        @Override
                        public void onError(int id, Exception ex) {
                            // Handle errors
                            HyperLog.i(TAG, "Failed to upload log file : " + ex.getMessage());
                        }

                    });
                } else {
                    HyperLog.i(TAG, "No log file present");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public void checkAlredyLoged() {
        token = Utils.LoadPreferences(context, Constants.KEY_TOCKEN);
        user_id = Utils.LoadPreferences(context, Constants.KEY_USER_ID);

        if(user_id.isEmpty()|| token.isEmpty()){
            mvpView.otpSending();
        }else{
            mvpView.directToProfile();
        }
    }
}
