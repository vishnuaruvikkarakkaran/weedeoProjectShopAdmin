package com.weedeo.shopadmin.modules.call;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.NetworkUtils;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.base.BaseActivity;
import com.weedeo.shopadmin.modules.profile.ProfileActivity;

import butterknife.BindView;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public class VideoChatViewActivity extends BaseActivity {

    private SurfaceView mLocalView;
    private SurfaceView mRemoteView;
    private RtcEngine mRtcEngine;
    private String TAG = "VideoCallActivity";

    private Context mContext;
    private Dialog progressDialog;
    private String agoraToken, agoraChannel;

    @BindView(R.id.remote_video_view_container)
    FrameLayout mRemoteContainer;

    @BindView(R.id.local_video_view_container)
    RelativeLayout mLocalContainer;

    private String token;
    private String channelName;
    private String userName;
    private String userImage;
    private boolean mMuted;
    private ImageView mMuteBtn;
    private ImageView mSwitchCameraBtn;
    @BindView(R.id.ivTakePhoto)
    ImageView ivTakePhoto;
    @BindView(R.id.btn_scan)
    ImageView btn_scan;
    @BindView(R.id.rlUserMuteVideo)
    RelativeLayout rlUserMuteVideo;

    @BindView(R.id.tvRemoteUserName)
    TextView tvRemoteUserName;

    @BindView(R.id.ivRemoteUserImage)
    ImageView ivRemoteUserImage;

    boolean userLeft = false;

    private static final int PERMISSION_REQ_ID = 23;

    // Permission WRITE_EXTERNAL_STORAGE is not mandatory
    // for Agora RTC SDK, just in case if you wanna save
    // logs to external sdcard.
    private static final String[] REQUESTED_PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        // Listen for the onJoinChannelSuccess callback.
        // This callback occurs when the local user successfully joins the channel.
        public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("agora", "Join channel success, uid: " + (uid & 0xFFFFFFFFL));
                    setupRemoteVideo(uid);
                }
            });
        }

        @Override
        public void onUserEnableLocalVideo(int uid, boolean enabled) {
            super.onUserEnableLocalVideo(uid, enabled);
            Log.e("onUserEnableLocalVideo",""+enabled);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (enabled) {
                        rlUserMuteVideo.setVisibility(View.GONE);
                        mRemoteContainer.setVisibility(View.VISIBLE);
                        mRemoteView.setVisibility(View.VISIBLE);

                    } else {
                        rlUserMuteVideo.setVisibility(View.VISIBLE);
                        mRemoteContainer.setVisibility(View.GONE);
                        mRemoteView.setVisibility(View.GONE);
                    }
                }
            });
        }

        @Override
        // Listen for the onFirstRemoteVideoDecoded callback.
        // This callback occurs when the first video frame of a remote user is received and decoded after the remote user successfully joins the channel.
        // You can call the setupRemoteVideo method in this callback to set up the remote video view.
        public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("agora", "First remote video decoded, uid: " + uid);
                    setupRemoteVideo(uid);
                }
            });
        }

        @Override
        // Listen for the onUserOffline callback.
        // This callback occurs when the remote user leaves the channel or drops offline.
        public void onUserOffline(final int uid, int reason) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    onRemoteUserLeft();
                }
            });
        }

        @Override
        public void onRemoteVideoTransportStats(int uid, int delay, int lost, int rxKBitRate) {
            super.onRemoteVideoTransportStats(uid, delay, lost, rxKBitRate);
        }

        @Override
        public void onRemoteVideoStat(int uid, int delay, int receivedBitrate, int receivedFrameRate) {
            super.onRemoteVideoStat(uid, delay, receivedBitrate, receivedFrameRate);
            Log.e("agoraRemoteVideo","onRemoteVideoStat   " + delay);
        }

        @Override
        public void onRemoteVideoStats(RemoteVideoStats stats) {
            super.onRemoteVideoStats(stats);
            Log.e("agoraRemoteVideo","onRemoteVideoStats   " + stats.delay);
        }

        @Override
        public void onRemoteVideoStateChanged(int uid, int state, int reason, int elapsed) {
            super.onRemoteVideoStateChanged(uid, state, reason, elapsed);
            Log.e("agoraRemoteVideo","onRemoteVideoStateChanged   " + state);
            if (state == 0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (userName == null){
                            userName = getString(R.string.str_user_dummy);
                        }
                        Toast.makeText(getApplicationContext(), "Connection Lost from " + userName, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        @Override
        public void onRejoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onRejoinChannelSuccess(channel, uid, elapsed);
            Log.e("agoraRemoteVideo","onRejoinChannelSuccess   " + channel);
        }

        @Override
        public void onRemoteAudioStateChanged(int uid, int state, int reason, int elapsed) {
            super.onRemoteAudioStateChanged(uid, state, reason, elapsed);
        }

        @Override
        public void onRemoteAudioStats(RemoteAudioStats stats) {
            super.onRemoteAudioStats(stats);
        }

        @Override
        public void onRemoteSubscribeFallbackToAudioOnly(int uid, boolean isFallbackOrRecover) {
            super.onRemoteSubscribeFallbackToAudioOnly(uid, isFallbackOrRecover);
        }

        @Override
        public void onRequestToken() {
            super.onRequestToken();
        }

        @Override
        public void onRemoteAudioTransportStats(int uid, int delay, int lost, int rxKBitRate) {
            super.onRemoteAudioTransportStats(uid, delay, lost, rxKBitRate);
        }

        @Override
        public void onFirstRemoteVideoFrame(int uid, int width, int height, int elapsed) {
            super.onFirstRemoteVideoFrame(uid, width, height, elapsed);
        }

        @Override
        public void onFirstRemoteAudioDecoded(int uid, int elapsed) {
            super.onFirstRemoteAudioDecoded(uid, elapsed);
        }

        @Override
        public void onFirstRemoteAudioFrame(int uid, int elapsed) {
            super.onFirstRemoteAudioFrame(uid, elapsed);
        }

        @Override
        public void onNetworkTypeChanged(int type) {
            Log.e("onNetworkTypeChanged  ",""+type);
            super.onNetworkTypeChanged(type);
            if (type == 0){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.alert(VideoChatViewActivity.this,getString(R.string.network_retrying));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if (!NetworkUtils.isConnected(VideoChatViewActivity.this)) {
                                    onRemoteUserLeft();
                                }
                            }
                        }, 10000);
                    }
                });
            }
        }


        @Override
        public void onVideoStopped() {
            Log.e("onVideoStopped", "Stopped");
            super.onVideoStopped();
        }

        @Override
        public void onUserMuteVideo(int uid, boolean muted) {
            super.onUserMuteVideo(uid, muted);
        }

        @Override
        public void onUserJoined(int uid, int elapsed) {
            Log.e("onUserJoined", "joined" + elapsed);
            super.onUserJoined(uid, elapsed);
        }

        @Override
        public void onNetworkQuality(int uid, int txQuality, int rxQuality) {
            super.onNetworkQuality(uid, txQuality, rxQuality);
        }

        @Override
        public void onConnectionLost() {
            Log.e("onConnectionLost", "lost");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Connection Lost",Toast.LENGTH_SHORT).show();
                }
            });

            super.onConnectionLost();
        }

        @Override
        public void onError(int err) {
            Log.e("viDEOError", "ERROR" + err);
            super.onError(err);
        }

        @Override
        public void onUserMuteAudio(int uid, boolean muted) {
            Log.e("onUserMuteAudio", "Muted" + muted);
            if (muted) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "User Audio Muted", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            super.onUserMuteAudio(uid, muted);
        }
    };

    private void setupRemoteVideo(int uid) {
        // Only one remote video view is available for this
        // tutorial. Here we check if there exists a surface
        // view tagged as this uid.
        int count = mRemoteContainer.getChildCount();
        View view = null;
        for (int i = 0; i < count; i++) {
            View v = mRemoteContainer.getChildAt(i);
            if (v.getTag() instanceof Integer && ((int) v.getTag()) == uid) {
                view = v;
            }
        }

        if (view != null) {
            return;
        }

        mRemoteView = RtcEngine.CreateRendererView(getBaseContext());
        mRemoteContainer.addView(mRemoteView);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(mRemoteView, VideoCanvas.RENDER_MODE_HIDDEN, uid));
        mRemoteView.setTag(uid);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        mContext = VideoChatViewActivity.this;
        mMuteBtn = findViewById(R.id.btn_mute);

        Intent intent = getIntent();
        if (intent != null) {
            token = intent.getStringExtra("token");
            channelName = intent.getStringExtra("channelName");
            userName = intent.getStringExtra("userName");
            userImage = intent.getStringExtra("userImage");

            if (userName != null && userImage != null){
                tvRemoteUserName.setText(userName);
                Glide.with(getApplicationContext())
                        .load(Constants.PROFILE_IMAGE_EXTENTION + userImage)
                        .error(R.drawable.test_ic_user)
                        .placeholder(R.drawable.test_ic_user).into(ivRemoteUserImage);
            }
            if (token != null && channelName != null) {

                if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                        checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID) &&
                        checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)) {
                    initEngineAndJoinChannel(token, channelName);
                }
            }
        }

        ivTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void initEngineAndJoinChannel(String token, String channelName) {
        Log.e("agora channel name : ", channelName);
        Log.e("agora token : ", token);
        // This is our usual steps for joining
        // a channel and starting a call.
        initializeEngine();
        setupVideoConfig();
        setupLocalVideo();
        joinChannel(token, channelName);
    }

    private void initializeEngine() {
        try {
            mRtcEngine = RtcEngine.create(getBaseContext(), getString(R.string.agora_app_id), mRtcEventHandler);
            int q = mRtcEngine.getConnectionState();
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    private void setupVideoConfig() {
        // In simple use cases, we only need to enable video capturing
        // and rendering once at the initialization step.
        // Note: audio recording and playing is enabled by default.
        mRtcEngine.enableVideo();

        Log.e("agora", "setupVideoConfig");

        // Please go to this page for detailed explanation
        // https://docs.agora.io/en/Video/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_rtc_engine.html#af5f4de754e2c1f493096641c5c5c1d8f
        mRtcEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
                VideoEncoderConfiguration.VD_1280x720,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30,
                VideoEncoderConfiguration.STANDARD_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
    }

    private void setupLocalVideo() {
        // This is used to set a local preview.
        // The steps setting local and remote view are very similar.
        // But note that if the local user do not have a uid or do
        // not care what the uid is, he can set his uid as ZERO.
        // Our server will assign one and return the uid via the event
        // handler callback function (onJoinChannelSuccess) after
        // joining the channel successfully.
        mLocalView = RtcEngine.CreateRendererView(getBaseContext());
        mLocalView.setZOrderMediaOverlay(false);
        mLocalContainer.addView(mLocalView);
        mRtcEngine.setupLocalVideo(new VideoCanvas(mLocalView, VideoCanvas.RENDER_MODE_HIDDEN, 0));
        mRtcEngine.switchCamera();
    }

    private void joinChannel(String token, String channelName) {
        // 1. Users can only see each other after they join the
        // same channel successfully using the same app id.
        // 2. One token is only valid for the channel name that
        // you use to generate this token.

        mRtcEngine.joinChannel(token, channelName, "Extra Optional Data", 0);
    }

    private void removeLocalVideo() {
        if (mLocalView != null) {
            mLocalContainer.removeView(mLocalView);
        }
        mLocalView = null;
    }

    private void onRemoteUserLeft() {
        removeRemoteVideo();
    }

    private void removeRemoteVideo() {
        if (mRemoteView != null) {
            mRemoteContainer.removeView(mRemoteView);
        }
        mRemoteView = null;
        Constants.IN_A_CALL = 0;
        userLeft = true;
        Toast.makeText(getApplicationContext(), "Call Disconnected", Toast.LENGTH_LONG).show();
        VideoChatViewActivity.this.finish();
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!VideoChatViewActivity.this.isFinishing()) {
                    if (mRemoteView != null) {
                        mRemoteContainer.removeView(mRemoteView);
                    }
                    mRemoteView = null;
                    Constants.IN_A_CALL = 0;
                    userLeft = true;

                    VideoChatViewActivity.this.finish();
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        }, 10000);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_video_chat_view;
    }

   /* @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }*/

    public void onLocalAudioMuteClicked(View view) {
        mMuted = !mMuted;
        if (mRtcEngine != null) {
            mRtcEngine.muteLocalAudioStream(mMuted);
        }
        int res = mMuted ? R.drawable.microphone_mute : R.drawable.ic_audio_unmute;
        mMuteBtn.setImageResource(res);
    }

    public void onSwitchCameraClicked(View view) {
        if (mRtcEngine != null) {
            mRtcEngine.switchCamera();
        }
    }

    private void leaveChannel() {
        if (mRtcEngine != null) {
            mRtcEngine.leaveChannel();
        }

        if (!VideoChatViewActivity.this.isFinishing()) {
            VideoChatViewActivity.this.finish();
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQ_ID) {
            if (grantResults.length > 0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                        grantResults[1] != PackageManager.PERMISSION_GRANTED ||
                        grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                    if (!VideoChatViewActivity.this.isFinishing()) {

                        Toast.makeText(VideoChatViewActivity.this, "Need permissions " + Manifest.permission.RECORD_AUDIO +
                                "/" + Manifest.permission.CAMERA + "/" + Manifest.permission.WRITE_EXTERNAL_STORAGE, Toast.LENGTH_LONG).show();
                    /*Utils.alert(VideoChatViewActivity.this, "Need permissions " + Manifest.permission.RECORD_AUDIO +
                            "/" + Manifest.permission.CAMERA + "/" + Manifest.permission.WRITE_EXTERNAL_STORAGE);*/

                        VideoChatViewActivity.this.finish();
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    return;
                }
            }

            // Here we continue only if all permissions are granted.
            // The permissions can also be granted in the system settings manually.

            if (token != null && channelName != null) {
                initEngineAndJoinChannel(token, channelName);
            }
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.e("userLeaveHint", "Leave");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {

        if (!userLeft) {
            leaveChannel();
        }

        Log.e("weedeoDestroy", "Destroy");
        super.onDestroy();
        RtcEngine.destroy();
    }

    @Override
    public void onBackPressed() {
        Utils.alert(VideoChatViewActivity.this, getString(R.string.on_a_Call));
    }
}