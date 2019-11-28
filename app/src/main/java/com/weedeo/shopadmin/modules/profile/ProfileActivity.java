package com.weedeo.shopadmin.modules.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
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
import com.google.gson.Gson;
import com.hypertrack.hyperlog.HyperLog;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.soundcloud.android.crop.Crop;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.NetworkUtils;
import com.weedeo.shopadmin.Utils.OnItemClickListener;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.base.BaseActivity;
import com.weedeo.shopadmin.model.response.Profile_info_responds;
import com.weedeo.shopadmin.modules.DashBoard.NavigationDrawerActivity;
import com.weedeo.shopadmin.modules.map.MapActivity;
import com.weedeo.shopadmin.modules.phonesigninpage.PhoneSignInActivity;
import com.weedeo.shopadmin.modules.shopTimming.ShopTimming;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

import static com.weedeo.shopadmin.Utils.Constants.KEY_DEVICE_ID;
import static com.weedeo.shopadmin.Utils.Constants.KEY_FCM_UID;


public class ProfileActivity extends BaseActivity implements iProfile.ActivityView, OnItemClickListener {

    @BindView(R.id.changePhonenumber)
    ImageView changeNumber;

    @BindView(R.id.chosePrimaryImage)
    ImageView image_pickup;

    @BindView(R.id.addImageTobucket)
    ImageView addImageTobucket;

    @BindView(R.id.loadPrimaryImage)
    ImageView loadPrimaryImage;

    @BindView(R.id.backToDash)
    ImageView backToDash;

    @BindView(R.id.shopLogout)
    TextView log_out;

    @BindView(R.id.changeLocation)
    CircularImageView manage_address;

    @BindView(R.id.shopName)
    TextView shop_name_field;

    @BindView(R.id.shopPhone)
    TextView shop_number_field;

    @BindView(R.id.shopLocation)
    TextView shopLocation;

    @BindView(R.id.recyc)
    RecyclerView recyclerView;

    @BindView(R.id.primaryImageStatus)
    TextView primaryImageStatus;


    /*@BindView(R.id.shoptimgBack)
    TextView shop_timing;*/

    public static final int REQUEST_IMAGE_CAPTURE = 553;
    public static final int REQUEST_GALLERY_IMAGE = 1;
    AlertDialog alert;
    Pinview pinview;
    Button buttonSignIn;
    private String fileName;
    private FirebaseAuth mAuth;
    private String mobile, shop_id, user_id, shop_name;
    private String mVerificationId;
    private String MobilePattern = "[0-9]{10}";
    static final int OPEN_MEDIA_PICKER = 1;
    private ProfilePresenter profilePresenter;
    private View button;
    private ArrayList<Image> images;
    private ArrayList<File> files;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Dialog progressDialog;
    Context context;

    private String code;
    private String TAG = "profileActivity";
    String fcmDeviceId;
    private int primaryPickUp = 0;
    private String profileImagePath;
    private ProfileAdapterClass profileAdapterClass;

    private List<String> profile_info_responds;

    Profile_info_responds profileResponse;
    private Uri destination;
    private Image imageModel;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        this.context = ProfileActivity.this;
        profilePresenter = new ProfilePresenter(context,
                "ProfileActivity", ProfileActivity.this);
        fileName = profilePresenter.onCreateProfileFileName();
        progressDialog = Utils.showProgressDialog(this);
        profileResponse = new Profile_info_responds();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        HyperLog.i(TAG, "Failed to get fcm token");
                        Log.e("getInstanceId failed",
                                String.valueOf(task.getException()));
                        return;
                    }
                    // Get new Instance ID token
                    fcmDeviceId =
                            Objects.requireNonNull(task.getResult()).getToken();
                    Log.e("id", "..........." + fcmDeviceId);
                });

        Intent intent = getIntent();
        shop_id = intent.getStringExtra(Constants.KEY_SHOP_ID);
        user_id = intent.getStringExtra(Constants.KEY_USER_ID);
        shopLocation.setText(Utils.LoadPreferences(this,
                Constants.SHOP_LOCATION));
        profilePresenter.ProfileInfoget(Utils.LoadPreferences(context,
                Constants.KEY_SHOP_ID), context);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_profile;
    }

    @OnClick(R.id.shopPhone)
    public void toggleBottomSheet() {

        if (NetworkUtils.isConnected(context)) {

            AlertDialog.Builder dialog = new
                    AlertDialog.Builder(ProfileActivity.this);
            View view =
                    getLayoutInflater().inflate(R.layout.fragment_change_number, null);
            dialog.setView(view);
            AlertDialog alert1 = dialog.create();

            alert1.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            alert1.show();


            EditText numberInput = view.findViewById(R.id.inputnumber);
            button = view.findViewById(R.id.signInbtn);
            //numberInput.setText(shop_number_field.getText().toString());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number_for_otp = (String)
                            numberInput.getText().toString().trim();
                    if (number_for_otp.matches(MobilePattern)) {
                        Toast.makeText(ProfileActivity.this, "Loading " + number_for_otp, Toast.LENGTH_SHORT).show();

                        profilePresenter.profileChangeNumber(number_for_otp);
                        alert1.dismiss();


                    } else {
                        Toast.makeText(ProfileActivity.this, "Invalid Number" + number_for_otp, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            onShowToast(getString(R.string.no_internet));
        }
    }

    @OnClick(R.id.llShopTiming)
    public void shopTiming() {
        Intent intent = new Intent(getApplicationContext(), ShopTimming.class);
        startActivity(intent);
    }

    private void otpAlertDiologue(String number_for_otp) {

        View view =
                getLayoutInflater().inflate(R.layout.changenumber_otp, null);
        AlertDialog.Builder dialog = new
                AlertDialog.Builder(ProfileActivity.this);
        dialog.setView(view);
        alert = dialog.create();

        alert.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        alert.show();

        Pinview pinview = view.findViewById(R.id.editTextCode);
        Button verifyotp = view.findViewById(R.id.buttonSignIn);
        TextView count_down = view.findViewById(R.id.autvritext);
        TextView didntget = view.findViewById(R.id.didntget);
        TextView resend = view.findViewById(R.id.resend);

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                code = pinview.getValue().trim();
                if (code.isEmpty() || code.length() < 6) {
                    pinview.requestFocus();
                    return;
                }
                verifyVerificationCode(number_for_otp, code);

            }
        });


        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                count_down.setText("Verification In " +
                        millisUntilFinished / 1000 + " Sec");
                didntget.setVisibility(View.INVISIBLE);
                resend.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                didntget.setVisibility(View.VISIBLE);
                resend.setVisibility(View.VISIBLE);
                count_down.setVisibility(View.INVISIBLE);

            }
        }.start();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.Toast(context, "otp resended");
                new CountDownTimer(60000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        count_down.setText("Verification In " +
                                millisUntilFinished / 1000 + " Sec");
                        didntget.setVisibility(View.INVISIBLE);
                        resend.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFinish() {
                        didntget.setVisibility(View.VISIBLE);
                        resend.setVisibility(View.VISIBLE);
                        count_down.setVisibility(View.INVISIBLE);

                    }
                }.start();

                code = pinview.getValue().trim();
                if (code.isEmpty() || code.length() < 6) {
                    pinview.requestFocus();
                    return;
                }
                verifyVerificationCode(number_for_otp, code);


            }
        });
    }


    private void verifyVerificationCode(String mobileNumber, String code) {

        try {

            PhoneAuthCredential credential =
                    PhoneAuthProvider.getCredential(mVerificationId, code);
            signInWithPhoneAuthCredential(credential, mobileNumber);

        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Verification Code is wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential
                                                       credential, String mobileNumber) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(ProfileActivity.this, new
                        OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user =
                                            Objects.requireNonNull(task.getResult()).getUser();


                                    Utils.SavePreferences(ProfileActivity.this, KEY_FCM_UID,
                                            user.getUid());

                                    Utils.SavePreferences(ProfileActivity.this, KEY_DEVICE_ID,
                                            fcmDeviceId);
                                    String chekke =
                                            Utils.LoadPreferences(ProfileActivity.this, KEY_DEVICE_ID);
                                    String KEY_FCM_UIdfgD =
                                            Utils.LoadPreferences(ProfileActivity.this, KEY_FCM_UID);
                                    Log.e("chekke", "" + chekke + "========="
                                            + KEY_FCM_UIdfgD);

                                    //verification successful we will startthe profile activity
                                    profilePresenter.memberCheck(mobileNumber,
                                            user.getUid());
                                    //  successToProfile();

                                } else {

                                    //verification unsuccessful.. display anerror message
                                    String message = "OTP Fails";
                                    if (task.getException() instanceof
                                            FirebaseAuthInvalidCredentialsException) {
                                        message = "Invalid code entered...";
                                        Utils.Toast(context, message);
                                        Log.e("Invalid code entered...", message);
                                        Log.e("verifon unsue", message);
                                    }
                                }
                            }
                        });
    }

    private void sendVerificationCode(String number_for_otp) {

        otpAlertDiologue(number_for_otp);

        Log.e("Verification code", "send");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + number_for_otp,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential
                                                            phoneAuthCredential) {


                    if (code != null) {
                        //editTextCode.setValue(code);
                        //verifying the code
                        verifyVerificationCode(code, code);
                        String code = phoneAuthCredential.getSmsCode();

                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    // String code = phoneAuthCredential.getSmsCode();
                    Log.e("Verification code", "fail fail fail");
                }

                @Override
                public void onCodeSent(String s,
                                       PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    mVerificationId = s;

                }
            };

    @Override
    public void phoneNumberCheck(String number_for_otp) {

    }

    @Override
    public void signOutSuccess() {
        Utils.Toast(this, "Signed Out");
        Intent intent = new Intent(ProfileActivity.this,
                PhoneSignInActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void phonenumberValid(String number_for_otp) {
        onHideProgress();
        sendVerificationCode(number_for_otp);

    }

    @Override
    public void successToShopTiming(String message) {

    }

    @Override
    public void numberUpdated(String message, String primary_mobile) {
        onHideProgress();
        Utils.Toast(context, message);
        shop_number_field.setText(primary_mobile);
        alert.dismiss();

    }

    @Override
    public void onSetUserImage(String path) {

    }

    @Override
    public void primaryImageUploadSuccesfully(String string, String imageName) {
        onHideProgress();
        Utils.Toast(context, "Updated");
        profilePresenter.uploadPrimaryImage(imageName);


    }

    @Override
    public void pofileData(String path, String fileName, int typeOfDoc) {
        onHideProgress();
        //  Utils.Toast(context, "Updated");
        profilePresenter.profileUploadingImage(fileName, typeOfDoc);
    }


    /// data succes from api call back from sreejith
    @Override
    public void profileDataSuccess(String fileName) {

        Log.e("profileDataSuccess", "data succes from api call back from sreejith");
        onHideProgress();
        Utils.Toast(context, "Updated");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                profilePresenter.ProfileInfoget(Utils.LoadPreferences(context,
                        Constants.KEY_SHOP_ID), context);
            }
        });
    }

    @Override
    public void primaryUploaded(String primaryImage, String shop_id) {

        this.shop_id = shop_id;

        onHideProgress();
        Glide.with(context)
                .load(Constants.PRIMARY_IMAGE_URL + shop_id + "/" +
                        primaryImage)
                .into(loadPrimaryImage);
        Utils.SavePreferences(context, Constants.PRIMARY_IMAGE_STRING,
                Constants.PRIMARY_IMAGE_URL + shop_id + "/" + primaryImage);

        // primaryImageStatus.setText("Approval Pending");


    }

    @Override
    public void setUpgradeadapter(ProfileAdapterClass profileAdapter) {
        try {
            GridLayoutManager gridLayoutManager = new
                    GridLayoutManager(getApplicationContext(), 5);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(profileAdapter);
            profileAdapterClass = profileAdapter;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ProfileData(Profile_info_responds response, Context mContext) {
        this.profileResponse = response;
        this.context = mContext;
        Log.e("ProfileData of info cal", "...." + new
                Gson().toJson(profileResponse));
        Log.e(TAG, "context on profile: " + new
                Gson().toJson(profileResponse.getData().getShopProfiles()));
        onHideProgress();

        try {
            if (profileResponse != null) {


                String status;
                if (profileResponse.getData().getPrimary_image_status() == 1) {
                    status = "Request pending";
                    primaryImageStatus.setVisibility(View.INVISIBLE);

                } else if
                (profileResponse.getData().getPrimary_image_status() == 2) {
                    status = "Request Approved";
                    primaryImageStatus.setVisibility(View.INVISIBLE);
                } else {
                    status = "Profile Image Rejected";
                }

                updateAdapter(profileResponse);

                shop_name_field.setText(String.valueOf(profileResponse.getData().getShop_name()));

                shop_number_field.setText(String.valueOf(profileResponse.getData().getPrimary_mobile()));
                shopLocation.setText(Utils.LoadPreferences(this,
                        Constants.SHOP_LOCATION));
                primaryImageStatus.setText(status);


                if (profileResponse.getData().getPrimary_image() != null) {
                    String primaryImage = Constants.PRIMARY_IMAGE_URL
                            + response.getData().getId() + "/" +
                            profileResponse.getData().getPrimary_image().trim();
                    Glide.with(this.context)
                            .load(primaryImage)
                            .into(loadPrimaryImage);
                }


                if (profileResponse.getData().getShopProfiles().size() == 0) {
                    addImageTobucket.setVisibility(View.VISIBLE);
                } else {
                    addImageTobucket.setVisibility(View.INVISIBLE);
                }
            } else {
                Utils.Toast(this.context, "Can't Load your data now");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void updateAdapter(Profile_info_responds response) {
        profileAdapterClass = new ProfileAdapterClass(this, response);
        profileAdapterClass.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(profileAdapterClass);
        profileAdapterClass.notifyDataSetChanged();


    }

    @Override
    public void deleteProfileImage(String fileName, Context mContext) {

        this.context = mContext;
        if (fileName != null) {
            Log.e("delete profile", "......." + fileName);
            profilePresenter = new ProfilePresenter(context, "tag", this);
            profilePresenter.deleteProfileData(fileName,
                    Utils.LoadPreferences(context, Constants.KEY_TOCKEN),
                    Utils.LoadPreferences(context, Constants.KEY_SHOP_ID), context);
        } else {
            Utils.Toast(this, "Please Retry");
        }

    }

    @Override
    public void profileSuccefullyDeleted(int count, Context mcontext) {
        Log.e(TAG, "profileSuccefullyDeleted: " + count);
        onHideProgress();
        this.context = mcontext;
        if (count == 0) {
            Utils.Toast(this.context, "please try again ");
        } else {
            if (mcontext != null) {
                Utils.Toast(context, "Image Deleted");
                Log.e(TAG, "profileSuccefullyDeleted: " + mcontext);
                //
                profilePresenter.ProfileInfoget(Utils.LoadPreferences(mcontext,
                        Constants.KEY_SHOP_ID), context);

            }
        }
    }

    @Override
    public void closePager() {

    }


    @Override
    public void imagePickUpMethodFromAdapter(Context mContext, int
            primaryPickUp, int limit) {
        this.context = mContext;
        imagePickUpMethod(mContext, primaryPickUp, limit);
    }

    @OnClick({R.id.chosePrimaryImage, R.id.addImageTobucket, R.id.shopLocation})
    public void onClick(View view) {
        if (NetworkUtils.isConnected(context)) {
            switch (view.getId()) {

                case R.id.chosePrimaryImage:
                    Dexter.withActivity(this)

                            .withPermissions(Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .withListener(new MultiplePermissionsListener() {
                                @Override
                                public void
                                onPermissionsChecked(MultiplePermissionsReport report) {
                                    if (report.areAllPermissionsGranted()) {
                                        primaryPickUp = 1;
                                        imagePickUpMethod(context,
                                                primaryPickUp, 1);

                                    } else {
                                        // TODO - handle permission denied case
                                    }
                                }

                                @Override
                                public void
                                onPermissionRationaleShouldBeShown(List<PermissionRequest>
                                                                           permissions, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();

                    break;

                case R.id.addImageTobucket:
                    Dexter.withActivity(this)

                            .withPermissions(Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .withListener(new MultiplePermissionsListener() {
                                @Override
                                public void
                                onPermissionsChecked(MultiplePermissionsReport report) {
                                    if (report.areAllPermissionsGranted()) {
                                        primaryPickUp = 2;

                                        if
                                        (profileResponse.getData().getShopProfiles().size() >= 10) {
                                            Log.e("image count",
                                                    "image Count is 10 ");
                                            Utils.Toast(context,
                                                    "Maximum limit reached ");
                                        } else {
                                            int limit = 10 -
                                                    profileResponse.getData().getShopProfiles().size();
                                            imagePickUpMethod(context,
                                                    primaryPickUp, limit);
                                        }

                                    } else {

                                    }
                                }

                                @Override
                                public void
                                onPermissionRationaleShouldBeShown(List<PermissionRequest>
                                                                           permissions, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();

                    break;


                case R.id.shopLocation:
                    Intent mapIntent = new Intent(this, MapActivity.class);
                    startActivity(mapIntent);

                    break;
            }

        } else {
            onShowToast(getString(R.string.no_internet));
        }
    }

    private void imagePickUpMethod(Context mContext, int
            primaryPickUp, int limit) {

        this.context = mContext;
        ImagePicker imagePicker = new
                ImagePicker.ImagePickerWithActivity((Activity) context);
        imagePicker.create((Activity) context);
        imagePicker.folderMode(true);
        imagePicker.toolbarImageTitle("Tap to select");
        imagePicker.toolbarArrowColor(Color.BLACK);
        imagePicker.single();
        imagePicker.multi();

        imagePicker.limit(limit);
        if (primaryPickUp == 1) {
            imagePicker.includeVideo(false);
        } else {
            imagePicker.includeVideo(true);
        }
        imagePicker.showCamera(true);

        imagePicker.imageDirectory("Camera");
        imagePicker.origin(images);
        imagePicker.exclude(images);
        imagePicker.excludeFiles(files);
        imagePicker.enableLog(true);
        imagePicker.start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {


        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
                List<Image> images = ImagePicker.getImages(data);
                Image image = ImagePicker.getFirstImageOrNull(data);
                if (primaryPickUp == 1) {
                    // profile pic
                    if (requestCode == REQUEST_IMAGE_CAPTURE &&
                            resultCode == RESULT_OK) {
                        Uri imagePath = Uri.parse(String.valueOf(new
                                File(image.getPath())));
                        Utils.SavePreferences(context,
                                Constants.PRIMARY_IMAGE_STRING, imagePath.getPath());
                        File currentFile = new File(imagePath.toString());
                        if (currentFile.exists()) {
                            HyperLog.i(TAG, "image file present");
                            try {

                                Log.e("s3 passing", "Primary image to server");
                                imageModel = images.get(0);
                                Log.e(TAG, "current file............."
                                        + currentFile);
                                destination = Uri.fromFile(new
                                        File(getCacheDir(), imageModel.getName()));
                                Crop.of(Uri.fromFile(new
                                        File(image.getPath())), destination)
                                        .asSquare()
                                        .start(this);

                                //
                                profilePresenter.uploadFileToS3(currentFile, "/", 1,
                                        imageModel.getName(), 2);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("file handling execption", "file handle error");
                            }

                        } else {
                            HyperLog.i(TAG, "No image file present");
                            onHideProgress();
                        }


                    }

                } else {
                    // shop images

                    if (requestCode == REQUEST_IMAGE_CAPTURE &&
                            resultCode == RESULT_OK) {

                        for (int i = 0; i < images.size(); i++) {
                            Uri imagePath =
                                    Uri.parse(String.valueOf(new File(images.get(i).getPath())));

                            File currentFile = new File(imagePath.toString());
                            if (currentFile.exists()) {
                                HyperLog.i(TAG, "image file present");
                                try {
                                    Log.e("s3 passing", ".................");
                                    Image imageModel = images.get(i);
                                    long size =
                                            Integer.parseInt(String.valueOf(currentFile.length() / 1024));
                                    long fileSizeInMB = size / 1024;

                                    if (fileSizeInMB > 25) {
                                        Utils.alert(context, "File size limited to 25mb");
                                    } else {
                                        if
                                        ((imageModel.getName().endsWith(".mp4"))) {

                                            profilePresenter.uploadFileToS3(currentFile, "/shop_profile/", 2,
                                                    imageModel.getName(), 2);
                                        } else if
                                        ((imageModel.getName().endsWith(".avi"))) {

                                            profilePresenter.uploadFileToS3(currentFile, "/shop_profile/", 2,
                                                    imageModel.getName(), 2);
                                        } else if
                                        ((imageModel.getName().endsWith(".3gp"))) {

                                            profilePresenter.uploadFileToS3(currentFile, "/shop_profile/", 2,
                                                    imageModel.getName(), 2);
                                        } else {
                                            Log.e("Type of data",
                                                    "Imagesss..........");

                                            profilePresenter.uploadFileToS3(currentFile, "/shop_profile/", 2,
                                                    imageModel.getName(), 1);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e("file handling execption",
                                            "file handle error");
                                }

                            } else {
                                HyperLog.i(TAG, "No image file present");
                                onHideProgress();
                            }
                        }
                    }
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            Log.e(TAG, "onActivityResult: image out put file" + destination);
            File passimage = new File(destination.getPath());
            Log.e(TAG, "onActivityResult: pass................" + passimage);
            profilePresenter.uploadFileToS3(passimage, "/", 1,
                    imageModel.getName(), 1);
            primaryPickUp = 0;

        }


    }

    @OnClick(R.id.changeLocation)
    public void manageAddress() {
        if (NetworkUtils.isConnected(context)) {

            onShowProgress();
            Intent mapIntent = new Intent(this, MapActivity.class);
            startActivity(mapIntent);
            onHideProgress();
        } else {
            onShowToast(getString(R.string.no_internet));
        }
    }

    /*@OnClick(R.id.shop_timing)
    public void shop_timing() {
        Intent shopTiming = new Intent(this, ShopTimming.class);
        startActivity(shopTiming);

    }*/
    @OnClick(R.id.backToDash)
    public void backToDash() {
        Intent shopTiming = new Intent(this, NavigationDrawerActivity.class);
        startActivity(shopTiming);
    }

    @OnClick(R.id.shopLogout)
    public void log_out() {
        if (NetworkUtils.isConnected(context)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you wand to exit?");
            builder.setPositiveButton("Yes", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            profilePresenter.logOut();
                        }
                    });
            builder.setNegativeButton("No", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.show();


        } else {
            onShowToast(getString(R.string.no_internet));
        }

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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowSnackBar(String message) {

    }

    @Override
    public void onShowAlertDialog(String message) {
        Utils.Toast(context, message);

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void onBackPressed() {
        /*Log.e("navigationDrawer", "opening navigation drawer");
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();*/  // optional depending on your needs
    }

    @Override
    public void onItemClick(int position) {

    }
}
