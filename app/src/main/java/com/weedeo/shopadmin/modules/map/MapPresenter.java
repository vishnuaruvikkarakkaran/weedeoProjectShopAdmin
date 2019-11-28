package com.weedeo.shopadmin.modules.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hypertrack.hyperlog.HyperLog;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.data.ApiClient;
import com.weedeo.shopadmin.data.WebApiListener;
import com.weedeo.shopadmin.model.request.LocationModel;
import com.weedeo.shopadmin.model.request.LocationParams;
import com.weedeo.shopadmin.model.response.LocationResponds;
import com.weedeo.shopadmin.model.response.OtpMemberResponds;
import com.weedeo.shopadmin.modules.shopTimming.ShopTimming;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresenter implements iMap.PresenterView {

   private Context mContext;
   private MapActivity mapActivity;
   String TAG="MapPresenter";

    public MapPresenter(Context mContext, MapActivity mapActivity) {
        this.mContext = mContext;
        this.mapActivity = mapActivity;
    }


    @Override
    public void locationSaving(String shop_id, double latitude, double longitude) {

        String tokenAuth =Utils.LoadPreferences(mContext,Constants.KEY_TOCKEN);
        String shopId =Utils.LoadPreferences(mContext,Constants.KEY_SHOP_ID);
        HyperLog.i(TAG,"onUserLogin - Executed");
        mapActivity.onShowProgress();
        WebApiListener service = ApiClient.getRetrofitInstance().create(WebApiListener.class);
        LocationParams locationParams = new LocationParams();
        LocationParams.GpsLocationBean gpsLocationBean= new LocationParams.GpsLocationBean();
        gpsLocationBean.setLat(String.valueOf(latitude));
        gpsLocationBean.setLng(String.valueOf(longitude));
        locationParams.setGps_location(gpsLocationBean);
        locationParams.setShop_id(shopId);

        Log.e("Locatio......... : ",new Gson().toJson(locationParams)+".........."+tokenAuth);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(locationParams));
        Log.e("RequestBody.....",new Gson().toJson(requestBody));
        Call<String> call =  service.locationSend(requestBody,tokenAuth);
        call.enqueue(new Callback<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                try {
                    if(response.code()==Constants.SUCCESS_CODE){
                        HyperLog.i(TAG,"onUserLogin - Success");
                       // Log.e("Location responds..... : ",new Gson().toJson(response));
                        if (response.body()!=null){
                            Type listType = new TypeToken<LocationResponds>() {
                            }.getType();
                            LocationResponds locationResponds = new GsonBuilder().create().fromJson(response.body(), listType);
                            if (locationResponds.getStatus().equals(Constants.SUCCESS)){

                               // Log.e("Losa,jfvmksha,iml.... : ",new Gson().toJson(response));
                                mapActivity.onShowProgress();
                                mapActivity.successToShopTiming(locationResponds.getMessage());

                            }
                        }

                    }
                    else{

                        Log.e("error responds",response.message()+response.code());
                        mapActivity.onHideProgress();
                        mapActivity.onShowAlertDialog("Error Occured");


                    }

                } catch (JsonSyntaxException e) {
                    Log.e("Exception : ", e.getMessage());
                    HyperLog.i(TAG,"onUserLogin Error - "+e.getMessage());
                    mapActivity.onHideProgress();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mapActivity.onHideProgress();
                HyperLog.i(TAG,"onUserLogin Fails - "+t.getMessage());
                Log.e("Failure Message : ",t.getMessage());
            }

        });




    }
}
