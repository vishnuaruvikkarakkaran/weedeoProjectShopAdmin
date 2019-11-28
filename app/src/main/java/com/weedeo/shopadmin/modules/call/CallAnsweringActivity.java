package com.weedeo.shopadmin.modules.call;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.gauravbhola.ripplepulsebackground.RipplePulseLayout;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;

public class CallAnsweringActivity extends AppCompatActivity {

    private String token;
    private String channelName;
    private String userName;
    private String userImage;
    RipplePulseLayout mRipplePulseLayout;
    MediaPlayer mediaPlayer;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private int previousX;
    private int previousY;
    RelativeLayout mainlayout;
    private Rect rect;
    private boolean ignore = false;

    public TextView tvCustomerName;
    public CircularImageView cIvCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_answering);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        initiUi();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initiUi() {


        mainlayout = findViewById(R.id.rl);
        tvCustomerName = findViewById(R.id.tvCustomerName);
        cIvCustomer = findViewById(R.id.cIvCustomer);

        Intent intent = getIntent();
        if (intent != null) {
            token = intent.getStringExtra("token");
            channelName = intent.getStringExtra("channelName");
            userName = intent.getStringExtra("userName");
            userImage = intent.getStringExtra("userImage");

            if (userName != null) {
                tvCustomerName.setText(userName);
            }

            if (userImage != null) {
                Glide.with(getApplicationContext())
                        .load(Constants.PROFILE_IMAGE_EXTENTION + userImage)
                        .error(R.drawable.test_ic_user)
                        .placeholder(R.drawable.test_ic_user)
                        .into(cIvCustomer);
            }
        }

        playRingtone();

        mRipplePulseLayout = findViewById(R.id.layout_ripplepulse);
        mRipplePulseLayout.startRippleAnimation();
        mRipplePulseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopRingtone();

                if (token != null && channelName != null) {
                    Constants.IN_A_CALL = 1;
                    unRegisterMessageReceiver(CallAnsweringActivity.this);
                    CallAnsweringActivity.this.finish();
                    Intent intent = new Intent(getApplicationContext(), VideoChatViewActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("channelName", channelName);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userImage", userImage);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    startActivity(intent);
                }
            }
        });

        /*mRipplePulseLayout.setOnTouchListener(new OnSwipeTouchListener(CallAnsweringActivity.this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(CallAnsweringActivity.this, "left", Toast.LENGTH_SHORT).show();
                Animation RightSwipe = AnimationUtils.loadAnimation(CallAnsweringActivity.this, R.anim.right_swipe);
                mRipplePulseLayout.startAnimation(RightSwipe);
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(CallAnsweringActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f,
                0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(5000);  // animation duration
        animation.setRepeatCount(5);  // animation repeat count
        animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
        //animation.setFillAfter(true);

        mRipplePulseLayout.startAnimation(animation);*/

        //mRipplePulseLayout.setOnTouchListener(onTouchListener());

    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                if (ignore && event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams)
                                view.getLayoutParams();

                        previousX = x - lParams.leftMargin;
                        previousY = y - lParams.topMargin;

                        rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                        ignore = false;
                        return false;

                    case MotionEvent.ACTION_MOVE:
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - previousX;
                        layoutParams.topMargin = y - previousY;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                        if (!rect.contains(view.getLeft() + (int) event.getX(), view.getTop() + (int) event.getY())) {
                            ignore = true;

                        } else {

                        }

                        return false;

                    default:
                        return true;
                }
            }
        };
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {

            case MotionEvent.ACTION_MOVE:

                float dx = x - previousX;
                float dy = y - previousY;

                // Here you can try to detect the swipe. It will be necessary to
                // store more than the previous value to check that the user move constantly in the same direction
                detectSwipe(dx, dy);

            case MotionEvent.ACTION_UP:
                mIsDown = false;
                break;
        }

        previousX = x;
        previousY = y;
        return true;
    }*/

    private void playRingtone() {

        stopRingtone();

        Uri tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        try {
            mediaPlayer = MediaPlayer.create(this, tone);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        } catch (Exception e) {
            Log.e("ringtoneErrorr", "" + e);
        }

    }

    private void stopRingtone() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            Log.e("ringtoneStopError", "" + e);
        }

    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            Utils.alert(CallAnsweringActivity.this, "You are on a call. Please wait");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRingtone();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null){
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    playRingtone();
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    stopRingtone();
                }
            }
            if (intent.getExtras() != null) {
                boolean isCallActive = intent.getExtras().getBoolean("status");

                if (!isCallActive) {
                    Toast.makeText(getApplicationContext(), "User Call Disconnected", Toast.LENGTH_LONG).show();
                    unRegisterMessageReceiver(CallAnsweringActivity.this);
                    finishAndRemoveTask();
                }
            }
        }
    };

    private void unRegisterMessageReceiver(Context context) {
        if (context != null && mMessageReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (action == KeyEvent.ACTION_DOWN) {
                stopRingtone();
            }

            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        //check deveice, it is locked
        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);

        // it is locked
        if (keyguardManager.inKeyguardRestrictedInputMode()) {
            playRingtone();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("finishActivity")
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopRingtone();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRingtone();
    }
}
