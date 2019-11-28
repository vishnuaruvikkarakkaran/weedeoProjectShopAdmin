package com.weedeo.shopadmin.modules.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hypertrack.hyperlog.HyperLog;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.NetworkUtils;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.data.ApiClient;
import com.weedeo.shopadmin.data.WebApiListener;
import com.weedeo.shopadmin.model.request.ProfileAgainOtpRequest;
import com.weedeo.shopadmin.model.request.Profile_info_request;
import com.weedeo.shopadmin.model.request.Shop_profie_Request;
import com.weedeo.shopadmin.model.request.UpdateMobileNumberRequest;
import com.weedeo.shopadmin.model.response.DeleteProfileImageResponds;
import com.weedeo.shopadmin.model.response.LocationResponds;
import com.weedeo.shopadmin.model.response.ProfileAgainOtpSend;
import com.weedeo.shopadmin.model.response.Profile_info_responds;
import com.weedeo.shopadmin.model.response.Shop_image_responds;
import com.weedeo.shopadmin.modules.map.AppUtils;
import com.weedeo.shopadmin.sharedpreference.SharedPreferenceData;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.weedeo.shopadmin.Utils.Constants.KEY_SHOP_ID;
import static com.weedeo.shopadmin.Utils.Constants.KEY_USER_ID;
import static com.weedeo.shopadmin.Utils.Constants.MOBILE_NUMBER;

public class ProfilePresenter implements iProfile.PresenterView {

    private Context context;
    private String TAG = "ProfilePresenter";
    private iProfile.ActivityView activityView;

    public ProfilePresenter(Context context, String TAG, iProfile.ActivityView activityView) {
        this.context = context;
        this.TAG = TAG;
        this.activityView = activityView;
    }

    @Override
    public void changenumberFromProfile(String otp) {

    }

    @Override
    public void logOut() {
        FirebaseAuth.getInstance().signOut();
        // Utils.clearPreference(context,Constants.KEY_TOCKEN);
        Utils.clearPreference(context, KEY_USER_ID);
        activityView.signOutSuccess();

    }

    @Override
    public void profileChangeNumber(String number_for_otp) {

        HyperLog.i(TAG,"onUserLogin - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        ProfileAgainOtpRequest loginRequest = new ProfileAgainOtpRequest();
        loginRequest.setMobile("+91"+number_for_otp);
        loginRequest.setUser_type(Constants.ALL);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(loginRequest));
        Call<String> call =  service.existCheck(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"onUserLogin - Success");
                        Log.e("Login Request : ",new Gson().toJson(loginRequest));
                        if (response.body()!=null){
                            Type listType = new TypeToken<ProfileAgainOtpSend>() {
                            }.getType();
                            ProfileAgainOtpSend loginResponse = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (loginResponse.getStatus().equals(Constants.SUCCESS)){
                                if (loginResponse.getData()!=null){
                                    activityView.onShowProgress();
                                    activityView.phonenumberValid(number_for_otp);
                                }

                            }else {
                                activityView.onShowAlertDialog(loginResponse.getMessage());
                                activityView.onHideProgress();
                            }
                        }

                    }
                    else{
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog("Invalid number");
                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"onUserLogin Error - "+e.getMessage());
                    activityView.onHideProgress();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                activityView.onHideProgress();
                HyperLog.i(TAG,"onUserLogin Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });




    }

    @Override
    public void sendVerificationCode(String number_for_otp) {


    }

    @Override
    public void memberCheck(String mobile, String uid) {

        String tokenAuth =Utils.LoadPreferences(context,Constants.KEY_TOCKEN);
        String user_id =Utils.LoadPreferences(context, KEY_USER_ID);
        String shop_id =Utils.LoadPreferences(context,Constants.KEY_SHOP_ID);

        HyperLog.i(TAG,"onUserLogin - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        UpdateMobileNumberRequest updateMobileNumberRequest = new UpdateMobileNumberRequest();
        updateMobileNumberRequest.setFcm_uid(uid);
        updateMobileNumberRequest.setPrimary_mobile("+91"+mobile);
        updateMobileNumberRequest.setShop_id(shop_id);
        updateMobileNumberRequest.setUser_id(user_id);

        Log.e("Locatio......... : ",new Gson().toJson(updateMobileNumberRequest)+".........."+mobile);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(updateMobileNumberRequest));
        Log.e("RequestBody.....",new Gson().toJson(requestBody));
        Call<String> call =  service.locationSend(requestBody,tokenAuth);
        call.enqueue(new Callback<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"onUserLogin - Success");
//                        Log.e("Location responds..... : ",new Gson().toJson(response));
                        if (response.body()!=null){
                            Type listType = new TypeToken<LocationResponds>() {
                            }.getType();
                            LocationResponds locationResponds = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (locationResponds.getStatus().equals(Constants.SUCCESS)){
                                if (locationResponds.getData()!=null){
                                  //  Log.e("Number update..... : ",new Gson().toJson(locationResponds));

                                    activityView.onShowProgress();
                                    activityView.numberUpdated(locationResponds.getMessage(), String.valueOf(locationResponds.getData().getPrimary_mobile()));
                                    Utils.SavePreferences(context,MOBILE_NUMBER, String.valueOf(locationResponds.getData().getPrimary_mobile()));

                                }

                            }
                        }

                    }
                    else{
                        Log.e("error responds",response.message()+response.code());
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog("Error Occured");
                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"onUserLogin Error - "+e.getMessage());
                    activityView.onHideProgress();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                activityView.onHideProgress();
                HyperLog.i(TAG,"onUserLogin Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });



    }

    @Override
    public void onCropImage(Uri imageUri, ProfileActivity profileActivity) {

        Log.e("image cropper",""+imageUri);

        Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), AppUtils.queryName(context.getContentResolver(), imageUri)));
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(Constants.IMAGE_COMPRESSION);

        // applying UI theme
        options.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.white));
        options.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setActiveWidgetColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.withAspectRatio(Constants.PROFILE_ASPECT_RATIO_X, Constants.PROFILE_ASPECT_RATIO_Y);
        options.withMaxResultSize(500, 500);
        UCrop.of(imageUri, destinationUri)
                .withOptions(options)
                .start(profileActivity);


    }

    @Override
    public String onCreateProfileFileName() {

        SharedPreferenceData preferenceData = new SharedPreferenceData(context);
        return  preferenceData.getString(KEY_SHOP_ID)+".jpg";
    }

    @Override
    public void uploadFileToS3(File newFile, String type, int profileOrNot, String fileName, int typeOfDoc) {

        if (NetworkUtils.isConnected(context)) {
            activityView.onShowProgress();
            Log.e("uploading to s3 bucket", "uploading goin on");

            Log.e("file name : ", fileName);

            BasicAWSCredentials credentials = new BasicAWSCredentials(Constants.AWS_ACCESS_KEY, Constants.AWS_SECRET_KEY);
            AmazonS3Client s3Client = new AmazonS3Client(credentials);

            TransferUtility transferUtility =
                    TransferUtility.builder()
                            .context(context)
                            .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                            .s3Client(s3Client)
                            .build();
            TransferObserver uploadObserver =
                    transferUtility.upload(Constants.AWS_BUCKET_NAME, Constants.PROFILE_IMAGE_PATH_S3 + Utils.LoadPreferences(context, KEY_SHOP_ID) + type + newFile.getName(), newFile);
            uploadObserver.setTransferListener(new TransferListener() {


                @Override
                public void onStateChanged(int id, TransferState state) {
                    if (TransferState.COMPLETED == state) {
                        activityView.onSetUserImage(newFile.getPath());
                        HyperLog.i(TAG, "image upload...");
                        Log.e("path : ", new Gson().toJson(state));
                        // Handle a completed download.

                        if (profileOrNot == 1) {
                            activityView.onShowProgress();
                            activityView.primaryImageUploadSuccesfully(newFile.toString(), fileName);

                        } else {
                            activityView.onShowProgress();
                            activityView.pofileData(newFile.toString(), fileName, typeOfDoc);
                        }


                    }
                    activityView.onHideProgress();
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                    Log.e("progress : ", String.valueOf(id));
                }

                @Override
                public void onError(int id, Exception ex) {
                    // Handle errors
                    HyperLog.i(TAG, "Failed to upload log file : " + ex.getMessage());
                    activityView.onHideProgress();
                }

            });

        }else {
            activityView.onHideProgress();
            Utils.Toast(context,"Please connect to internet");
        }

    }

    @Override
    public void uploadPrimaryImage(String imageName) {

        String tokenAuth =Utils.LoadPreferences(context,Constants.KEY_TOCKEN);
        String user_id =Utils.LoadPreferences(context, KEY_USER_ID);
        String shop_id =Utils.LoadPreferences(context,Constants.KEY_SHOP_ID);

        HyperLog.i(TAG,"PrimaryImage Uploading - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        UpdateMobileNumberRequest updateMobileNumberRequest = new UpdateMobileNumberRequest();
        updateMobileNumberRequest.setShop_id(shop_id);
        updateMobileNumberRequest.setPrimary_image(imageName);


        Log.e("PrimaryImag......... : ",new Gson().toJson(updateMobileNumberRequest)+".........."+tokenAuth);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(updateMobileNumberRequest));
        Log.e("RequestBody.....",new Gson().toJson(requestBody));
        Call<String> call =  service.add_profile_image(requestBody,tokenAuth);
        call.enqueue(new Callback<String>() {
            @SuppressLint("PrimaryImag")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"PrimaryImag - Success");
                        if (response.body()!=null){
                            Type listType = new TypeToken<LocationResponds>() {
                            }.getType();
                            LocationResponds locationResponds = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (locationResponds.getStatus().equals(Constants.SUCCESS)){
                                if (locationResponds.getData()!=null){

                                    activityView.onShowProgress();
                                    activityView.primaryUploaded(locationResponds.getData().getPrimary_image(),shop_id);

                                }

                            }
                        }

                    }
                    else{

                        Log.e("error responds",response.message()+response.code());
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog(response.message());




                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"PrimaryImag Error - "+e.getMessage());
                    activityView.onHideProgress();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                activityView.onHideProgress();
                HyperLog.i(TAG,"PrimaryImag Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });




    }

    @Override
    public void profileUploadingImage(String fileName, int typeOfDoc) {

        String tokenAuth =Utils.LoadPreferences(context,Constants.KEY_TOCKEN);
        String user_id =Utils.LoadPreferences(context, KEY_USER_ID);
        String shop_id =Utils.LoadPreferences(context,Constants.KEY_SHOP_ID);

        HyperLog.i(TAG,"Profiledata Uploading - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        Shop_profie_Request shop_profie_request = new Shop_profie_Request();
        shop_profie_request.setShop_id(shop_id);
        shop_profie_request.setItem_path(fileName);
        shop_profie_request.setItem_type(typeOfDoc);

        Log.e("Profiledata....... : ",new Gson().toJson(shop_profie_request)+".........."+tokenAuth);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(shop_profie_request));
        Log.e("profiploadingImage.....",new Gson().toJson(requestBody));
        Call<String> call =  service.shop_Profile(requestBody,tokenAuth);
        call.enqueue(new Callback<String>() {
            @SuppressLint("Profiledata")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"Profiledata - Success");
                        if (response.body()!=null){
                            Type listType = new TypeToken<Shop_image_responds>() {
                            }.getType();
                            Shop_image_responds Shop_image_responds = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (Shop_image_responds.getStatus().equals(Constants.SUCCESS)){
                                if (Shop_image_responds.getData()!=null){
                                    activityView.onShowProgress();
                                    activityView.profileDataSuccess(fileName);
                                }
                            }
                        }
                    }
                    else{

                        Log.e("error responds",response.message()+response.code());
                        activityView.onHideProgress();
                        HyperLog.i(TAG,"ProfiledataError - "+"his shop is already exceeded the limit");
                        activityView.onShowAlertDialog("This shop is already exceeded the limit");


                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"ProfiledataError - "+e.getMessage());
                    activityView.onHideProgress();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                activityView.onHideProgress();
                HyperLog.i(TAG,"Profiledata Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });


    }

    @Override
    public void ProfileInfoget(String shopId,Context context) {

        String tokenAuth =Utils.LoadPreferences(context,Constants.KEY_TOCKEN);
        String shop_id =Utils.LoadPreferences(context,Constants.KEY_SHOP_ID);

        HyperLog.i(TAG,"ProfileInformation receving - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        Profile_info_request profile_info_request = new Profile_info_request();
        profile_info_request.setShop_id(shop_id);

        Log.e("Profiledata....... : ",new Gson().toJson(profile_info_request)+".........."+tokenAuth);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(profile_info_request));
        Call<String> call =  service.shop_info(requestBody,tokenAuth);
        call.enqueue(new Callback<String>() {
            @SuppressLint("Profiledata")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"ProfileInformation receving - Success");
                        if (response.body()!=null){
                            Type listType = new TypeToken<Profile_info_responds>() {
                            }.getType();
                            Profile_info_responds profile_info_responds = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (profile_info_responds.getStatus().equals(Constants.SUCCESS)){
                                if (profile_info_responds.getData()!=null){
                                    activityView.onShowProgress();

                                    activityView.ProfileData(profile_info_responds,context);

                                }
                            }
                        }

                    }
                    else{

                        Log.e("error responds",response.message()+response.code());
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog("Error Occured");

                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"ProfileInformation receving - "+e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                HyperLog.i(TAG,"ProfileInformation receving Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });


    }

    @Override
    public void deleteProfileData(String fileName, String token, String shopId, Context context) {

        String tokenAuth =token;
        HyperLog.i(TAG,"deleting profileimage - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);

       // RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"));
        //Log.e("RequestBody.....",new Gson().toJson(requestBody));
        Call<String> call =  service.deleteProfileData(fileName,tokenAuth);
        call.enqueue(new Callback<String>() {
            @SuppressLint("Profiledata")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"deleting profileimage - Success");
                        if (response.body()!=null){
                            Type listType = new TypeToken<DeleteProfileImageResponds>() {
                            }.getType();
                            DeleteProfileImageResponds deleteProfileImageResponds = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (deleteProfileImageResponds.getStatus().equals(Constants.SUCCESS)){
                                if (deleteProfileImageResponds.getData()!=null){

                                    if (deleteProfileImageResponds.getData().getCount()==1){
                                        Log.e("deleted ","Presenter Image deleted");
                                        activityView.profileSuccefullyDeleted(deleteProfileImageResponds.getData().getCount(),context);
                                    }else{
                                        activityView.onHideProgress();
                                        Utils.Toast(context,"Please try later");
                                    }
                                }
                            }
                        }

                    }
                    else{

                        Log.e("error responds",response.message()+response.code());
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog("Error Occured");

                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"deleting profileimage - "+e.getMessage());
                    activityView.onHideProgress();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                activityView.onHideProgress();
                HyperLog.i(TAG,"deleting profileimage Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });

    }


}



