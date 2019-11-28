package com.weedeo.shopadmin.modules.otpPage;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hypertrack.hyperlog.HyperLog;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.data.ApiClient;
import com.weedeo.shopadmin.data.WebApiListener;
import com.weedeo.shopadmin.model.request.MemberCheck;
import com.weedeo.shopadmin.model.response.OtpMemberResponds;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerificationPresenter implements iOtpVerification.presenter {

    private iOtpVerification.ActivityView mvpView;
    private String TAG="OtpVerificationPresenter";
    private Context context;
    private DatabaseReference mDatabase;

    public OtpVerificationPresenter(iOtpVerification.ActivityView mvpView, Context mContext) {
        this.mvpView = mvpView;
        this.context=mContext;
    }

    @Override
    public void timerStart() {
        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                mvpView.timerZero(millisUntilFinished);
            }

            @Override
            public void onFinish() {

                mvpView.timerFinish();

            }
        }.start();
    }

    @Override
    public void memberCheck(String mobile, String uid, String fcmDeviceId) {

        HyperLog.i(TAG,"onUserLogin - Executed");
        mvpView.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        MemberCheck memberCheck = new MemberCheck();
        memberCheck.setMobile(mobile);
        memberCheck.setUser_type(Constants.USER_TYPE);
        memberCheck.setDevice_id(fcmDeviceId);
        memberCheck.setFcm_uid(uid);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(memberCheck));
        Call<String> call =  service.memberCheck(requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"onUserLogin - Success");
                        Log.e("memberCheck Request : ",new Gson().toJson(memberCheck));

                        if (response.body()!=null){

                            Type listType = new TypeToken<OtpMemberResponds>() {}.getType();
                            OtpMemberResponds otpMemberResponds = new GsonBuilder().create().fromJson(response.body(), listType);

                            if (otpMemberResponds.getStatus().equals(Constants.SUCCESS)){
                                if (otpMemberResponds.getData()!=null){

                                    otpMemberResponds.getData().setDevice_id(fcmDeviceId);

                                    Utils.SavePreferences(context,Constants.KEY_SHOP_ID,otpMemberResponds.getData().getUser_data().getShop_id());
                                    Utils.SavePreferences(context,Constants.KEY_USER_ID,otpMemberResponds.getData().getUser_data().getId());
                                    Utils.SavePreferences(context,Constants.KEY_TOCKEN,otpMemberResponds.getToken());
                                    Utils.SavePreferences(context,Constants.SHOP_NAME,otpMemberResponds.getData().getShop_data().getShop_name());

                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("shop_admin");
                                    // pushing user to 'users' node using the userId
                                    mDatabase.child(otpMemberResponds.getData().getUser_data().getShop_id()).setValue(otpMemberResponds.getData());

                                    mvpView.onShowProgress();
                                    mvpView.successToProfile(otpMemberResponds.getData().getUser_data().getShop_id(),
                                            otpMemberResponds.getData().getUser_data().getId(),otpMemberResponds.getData().getShop_data().getShop_name(),otpMemberResponds.getData().getShop_data().getPrimary_mobile());
                                }

                            }
                        }

                    }
                    else{
                        mvpView.onHideProgress();
                        mvpView.onShowAlertDialog("Error Occured");
                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"onUserLogin Error - "+e.getMessage());
                    mvpView.onHideProgress();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mvpView.onHideProgress();
                HyperLog.i(TAG,"onUserLogin Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });


    }
}
