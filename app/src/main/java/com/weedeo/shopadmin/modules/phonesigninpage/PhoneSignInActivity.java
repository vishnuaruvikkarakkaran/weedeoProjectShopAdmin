package com.weedeo.shopadmin.modules.phonesigninpage;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.base.BaseActivity;
import com.weedeo.shopadmin.modules.otpPage.OtpverificationActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

public class PhoneSignInActivity extends BaseActivity implements iPhoneSignIn.ActivityView {

    @BindView(R.id.inputnumber)
    EditText numberInput;
    @BindView(R.id.continu)
    Button submit;
    private String MobilePattern = "[0-9]{10}";
    private PhoneSigninPresenter phoneSigninPresenter;

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private boolean mVerificationInProgress;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;
    private Context context;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=PhoneSignInActivity.this;
        progressDialog = Utils.showProgressDialog(this);
        phoneSigninPresenter=new PhoneSigninPresenter(this,context);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(PhoneSignInActivity.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("code send to phone", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                testPhoneAutoRetrieve(verificationId,token);

                /*Intent otpverify=new Intent(PhoneSignInActivity.this, OtpverificationActivity.class);
                otpverify.putExtra("verificationId",verificationId);
                otpverify.putExtra("token",token);
                startActivity(otpverify);*/


            }

        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("signInWithCredential", "signInWithCredential:success");

                    FirebaseUser user = task.getResult().getUser();

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("signInWithCredential", "signInWithCredential:failure", task.getException());
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                    }

                }
            }
        });

    }


    @Override
    public int getLayout() {
        return R.layout.activity_phone_sign_in_page;
    }

    @Override
    public void onShowProgress() {

        if (progressDialog!=null)
            progressDialog.show();

    }

    @Override
    public void onHideProgress() {
        if (progressDialog!=null&&progressDialog.isShowing())
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

        Utils.Toast(this,message);

    }

    @Override
    public void onRetry() {

    }

    @OnClick(R.id.continu)
    void submitButton(View view) {
        String number_for_otp = (numberInput.getText().toString().trim());
        if (number_for_otp.matches(MobilePattern)) {
            number_for_otp = "+91"+number_for_otp;
            //startPhoneNumberVerification("+91" + number_for_otp);
            numberInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
            phoneSigninPresenter.memberExist(number_for_otp);

        } else {
            Toast.makeText(this, "Invalid Number  " + number_for_otp, Toast.LENGTH_SHORT).show();
        }

    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                callbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    public void testPhoneAutoRetrieve(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
        // [START auth_test_phone_auto]
        // The test phone number and code should be whitelisted in the console.
        String phoneNumber = ("+919497572861");
        String smsCode = verificationId;

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

        // Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode);

        PhoneAuthProvider phoneAuthProvider = PhoneAuthProvider.getInstance();
        phoneAuthProvider.verifyPhoneNumber(
                phoneNumber,
                60L,
                TimeUnit.SECONDS,
                this, /* activity */
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        // Instant verification is applied and a credential is directly returned.
                        // ...
                      Log.e("onVerificationCompleted","onVerificationCompleted");
                    }

                    // [START_EXCLUDE]
                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Log.e("onVerificationFailed","onVerificationFailed");
                    }
                    // [END_EXCLUDE]
                });
        // [END auth_test_phone_auto]
    }

    @Override
    public void phonenumberValid(String numberForOtp) {

        onHideProgress();

        Intent intent = new Intent(PhoneSignInActivity.this, OtpverificationActivity.class);
        intent.putExtra("mobile", numberForOtp);
        startActivity(intent);


    }
}
