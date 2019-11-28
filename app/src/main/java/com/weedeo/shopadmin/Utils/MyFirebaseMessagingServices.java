package com.weedeo.shopadmin.Utils;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.weedeo.shopadmin.modules.call.CallAnsweringActivity;

public class MyFirebaseMessagingServices extends FirebaseMessagingService {

    LocalBroadcastManager localBroadcastManager;
    PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            String user_id = Utils.LoadPreferences(getApplicationContext(), Constants.KEY_USER_ID);
            boolean isCallActive = Boolean.parseBoolean(remoteMessage.getData().get("is_call_active"));

            if (isCallActive) {

                // CHECK WHETHER USER IS LOGINED OR NOT (ONLY LOGINED USER CAN RECIEVE VIDEO CALLS)
                if (!user_id.isEmpty()) {

                    if (Constants.IN_A_CALL == 0) {
                        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
                        if (!powerManager.isScreenOn()) {

                            wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,
                                    "MyApp::MyWeedeolockTag");
                            wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
                        }

                        Intent intent = new Intent(getApplicationContext(), CallAnsweringActivity.class);
                        intent.putExtra("token", remoteMessage.getData().get("token"));
                        intent.putExtra("channelName", remoteMessage.getData().get("channel"));
                        intent.putExtra("userName", remoteMessage.getData().get("user_name"));
                        intent.putExtra("userImage", remoteMessage.getData().get("user_Image"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        startActivity(intent);
                    }
                }
            } else {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        if (wakeLock != null) {
                            wakeLock.release();
                        }

                        // CHECK WHETHER USER IS LOGINED OR NOT (ONLY LOGINED USER CAN RECIEVE VIDEO CALLS)
                        if (!user_id.isEmpty()) {

                            Intent intent = new Intent("finishActivity");
                            intent.putExtra("status", remoteMessage.getData().get("is_call_active"));
                            localBroadcastManager.sendBroadcast(intent);
                        }
                    }
                });
            }
        }
    }
}
