package com.weedeo.shopadmin.modules.otpPage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hypertrack.hyperlog.HyperLog;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.base.BaseActivity;
import com.weedeo.shopadmin.modules.map.MapActivity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

import static com.weedeo.shopadmin.Utils.Constants.KEY_DEVICE_ID;
import static com.weedeo.shopadmin.Utils.Constants.KEY_FCM_UID;
import static com.weedeo.shopadmin.Utils.Constants.MOBILE_NUMBER;

public class OtpverificationActivity extends BaseActivity implements
        SMSRecever.OTPReceiveListener, iOtpVerification.ActivityView {

    private String mVerificationId;

    //The edittext to input the code
    /*@BindView(R.id.editTextCode)
    EditText editTextCode;*/
    @BindView(R.id.autvritext)
    TextView count_down;
    @BindView(R.id.didntget)
    TextView didntget;
    @BindView(R.id.resend)
    TextView resend;

    @BindView(R.id.editTextCode)
    Pinview editTextCode;
    String TAG = "OtpverificationActivity";


    private OtpVerificationPresenter otpVerificationPresenter;
    String code;
    //firebase auth object
    private FirebaseAuth mAuth;
    public String mobile;
    private String fcmDeviceId;
    private Dialog progressDialog;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        progressDialog = Utils.showProgressDialog(this);
        this.mContext = OtpverificationActivity.this;
        otpVerificationPresenter = new OtpVerificationPresenter(this, mContext);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        HyperLog.i(TAG, "Failed to get fcm token");
                        Log.e("getInstanceId failed", String.valueOf(task.getException()));
                        return;
                    }

                    // Get new Instance ID token
                    fcmDeviceId = Objects.requireNonNull(task.getResult()).getToken();
                    Log.e("id", "..........." + fcmDeviceId);
                });

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");

        sendVerificationCode(mobile);



        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                code = editTextCode.getValue().trim();

                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.requestFocus();
                    editTextCode.requestPinEntryFocus();
                    return;
                }
                verifyVerificationCode(code);
            }
        });

        editTextCode.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser) {
                // Toast.makeText(OtpverificationActivity.this, pinview.getValue(), Toast.LENGTH_SHORT).show();
                code = pinview.getValue();
            }
        });
    }

    @OnClick(R.id.resend)
    public void resend() {
        Utils.Toast(mContext, "otp resend successfuly ");
        sendVerificationCode(mobile);
    }


    private void verifyVerificationCode(String code) {

        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential);

        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OtpverificationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = Objects.requireNonNull(task.getResult()).getUser();


                            Utils.SavePreferences(OtpverificationActivity.this, KEY_FCM_UID, user.getUid());
                            Utils.SavePreferences(OtpverificationActivity.this, KEY_DEVICE_ID, fcmDeviceId);
                            Utils.SavePreferences(OtpverificationActivity.this, MOBILE_NUMBER, mobile);

                            String chekke = Utils.LoadPreferences(OtpverificationActivity.this, KEY_DEVICE_ID);
                            String KEY_FCM_UIdfgD = Utils.LoadPreferences(OtpverificationActivity.this, KEY_FCM_UID);
                            Log.e("chekke", "" + chekke + "=========" + KEY_FCM_UIdfgD);

                            //verification successful we will start the profile activity
                            otpVerificationPresenter.memberCheck(mobile, user.getUid(), fcmDeviceId);
                            //  successToProfile();


                        } else {
                            String message;
                            //verification unsuccessful.. display an error message
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                                Utils.Toast(mContext, message);
                            }

                        }
                    }
                });
    }

    private void sendVerificationCode(String mobile) {

        otpVerificationPresenter.timerStart();
        Log.e("Verification code", "send");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            if (code != null) {
                editTextCode.setValue(code);
                //verifying the code
                verifyVerificationCode(code);
                String code = phoneAuthCredential.getSmsCode();

            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpverificationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            // String code = phoneAuthCredential.getSmsCode();
            Log.e("codeeee", "sdbfvdfail faul fail fail failvszkyjcyghajusy" + code);
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_otpverification;
    }


    @Override
    public void onOTPReceived(String otp) {
        // Toast.makeText(this, "sms receved ;...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOTPTimeOut() {
        // Toast.makeText(this, "onOTPTimeOut...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onOTPReceivedError(String error) {

        // Toast.makeText(this, "onOTPReceivedError.....", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void timerZero(long millisUntilFinished) {
        count_down.setVisibility(View.VISIBLE);
        count_down.setText("Verification In " + millisUntilFinished / 1000 + " Sec");
        didntget.setVisibility(View.INVISIBLE);
        resend.setVisibility(View.INVISIBLE);
    }

    @Override
    public void timerFinish() {
        count_down.setVisibility(View.INVISIBLE);
        didntget.setVisibility(View.VISIBLE);
        resend.setVisibility(View.VISIBLE);
    }

    @Override
    public void successToProfile(String shop_id, String id, String shop_name, long primary_mobile) {
        onHideProgress();
        Utils.Toast(mContext, "Login Success");
        Intent intent = new Intent(OtpverificationActivity.this, MapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.KEY_SHOP_ID, shop_id);
        intent.putExtra(Constants.KEY_USER_ID, id);
        intent.putExtra(Constants.SHOP_NAME, shop_name);
        intent.putExtra(MOBILE_NUMBER, String.valueOf(primary_mobile));
        startActivity(intent);

    }

    @Override
    public void onShowProgress() {
        if (progressDialog != null)
            progressDialog.show();
    }

    @Override
    public void onHideProgress() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
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
}
