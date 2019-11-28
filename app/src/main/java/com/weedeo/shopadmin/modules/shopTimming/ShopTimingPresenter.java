package com.weedeo.shopadmin.modules.shopTimming;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.data.ApiClient;
import com.weedeo.shopadmin.data.WebApiListener;
import com.weedeo.shopadmin.model.request.Profile_info_request;
import com.weedeo.shopadmin.model.request.ShopTimingModel;
import com.weedeo.shopadmin.model.response.ShopTimingUpdationResponse;

import org.jetbrains.annotations.NotNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopTimingPresenter implements iShopTiming.PresenterView {

    private iShopTiming.ActivityView activityView;
    private Context context;
    WebApiListener webApiListener;

    public ShopTimingPresenter(iShopTiming.ActivityView activityView, Context context) {
        this.activityView = activityView;
        this.context = context;
        webApiListener = ApiClient.getRetrofitInstance().create(WebApiListener.class);
    }

    @Override
    public void getShopTiming() {

        String tokenAuth = Utils.LoadPreferences(context, Constants.KEY_TOCKEN);
        String shop_id = Utils.LoadPreferences(context, Constants.KEY_SHOP_ID);

        Profile_info_request profile_info_request = new Profile_info_request();
        profile_info_request.setShop_id(shop_id);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(profile_info_request));
        Call<ShopTimingModel> call = webApiListener.getShopTiming(requestBody, tokenAuth);
        call.enqueue(new Callback<ShopTimingModel>() {
            @Override
            public void onResponse(@NotNull Call<ShopTimingModel> call, @NotNull Response<ShopTimingModel> response) {

                ShopTimingModel shopTimingModel = response.body();

                assert shopTimingModel != null;
                activityView.shopTimingResponse(shopTimingModel);
            }

            @Override
            public void onFailure(@NotNull Call<ShopTimingModel> call, @NotNull Throwable t) {

            }
        });

    }

    @Override
    public void updateShopTiming(ShopTimingModel shopTiming) {

        String tokenAuth = Utils.LoadPreferences(context, Constants.KEY_TOCKEN);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(shopTiming));
        Call<ShopTimingUpdationResponse> call = webApiListener.updateShopTiming(requestBody, tokenAuth);
        call.enqueue(new Callback<ShopTimingUpdationResponse>() {
            @Override
            public void onResponse(@NotNull Call<ShopTimingUpdationResponse> call, @NotNull Response<ShopTimingUpdationResponse> response) {

                if (response.code() == Constants.SUCCESS_CODE) {

                    if (response.isSuccessful()) {

                        Log.e("updateTimingResponse", "" + response);
                        Toast.makeText(context,response.message(),Toast.LENGTH_SHORT).show();

                    } else {

                        Log.e("UpdateTimeError", "" + response.errorBody());
                    }

                } else if (response.code() == Constants.ERROR_CODE) {

                    Utils.Toast(context, response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ShopTimingUpdationResponse> call, @NotNull Throwable t) {
                Log.e("updaTimingResponseError", "" + t);
                Log.e("updaResponseErrorcall", "" + call);

            }
        });
    }
}
