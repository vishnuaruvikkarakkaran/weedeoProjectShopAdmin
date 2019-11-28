package com.weedeo.shopadmin.modules.phonesigninpage;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hypertrack.hyperlog.HyperLog;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.data.ApiClient;
import com.weedeo.shopadmin.data.WebApiListener;
import com.weedeo.shopadmin.model.request.LoginRequest;
import com.weedeo.shopadmin.model.response.LoginResponds;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneSigninPresenter implements iPhoneSignIn.presenterView {


    private iPhoneSignIn.ActivityView activityView;
    private Context mContext;

    public PhoneSigninPresenter(iPhoneSignIn.ActivityView mvpView, Context mContext) {
        this.activityView = mvpView;
        this.mContext = mContext;
    }


    private static final String TAG ="PhoneSigninPresenter" ;

    @Override
    public void memberExist(String numberForOtp) {

        HyperLog.i(TAG,"onUserLogin - Executed");
        activityView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setMobile(numberForOtp);
        loginRequest.setUser_type(Constants.USER_TYPE);
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
                            Type listType = new TypeToken<LoginResponds>() {
                            }.getType();
                            LoginResponds loginResponse = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (loginResponse.getStatus().equals(Constants.SUCCESS)){
                                if (loginResponse.getData()!=null){
                                    activityView.onShowProgress();
                                    activityView.phonenumberValid(numberForOtp);
                                }

                            }
                        }

                    }else if(response.code()==Constants.ERROR_CODE){
                        HyperLog.i(TAG,"onUserLogin - Not Registerd in backend");
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog("Please register your number first");

                    }else{
                        HyperLog.i(TAG,"onUserLogin -Invalid");
                        activityView.onHideProgress();
                        activityView.onShowAlertDialog("Invalid Number");
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
}
