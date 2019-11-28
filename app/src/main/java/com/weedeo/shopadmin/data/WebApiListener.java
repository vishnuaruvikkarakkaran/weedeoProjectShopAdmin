package com.weedeo.shopadmin.data;

import com.weedeo.shopadmin.model.request.ShopTimingModel;
import com.weedeo.shopadmin.model.response.ShopTimingUpdationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebApiListener {

    @Headers("Content-Type: application/json")
    @POST("Members/member-check")
    Call<String> memberCheck(@Body RequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("Members/member-exist")
    Call<String> existCheck(@Body RequestBody requestBody);

    @Headers("Content-Type: application/json")
    @POST("Shops/shop-update")
    Call<String> locationSend(@Body RequestBody requestBody,@Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @POST("Shop_profiles/add-profile-image")
    Call<String> shop_Profile(@Body RequestBody requestBody,@Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @POST("Shops/shop-update")
    Call<String> add_profile_image(@Body RequestBody requestBody,@Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @POST("Shops/profile-info")
    Call<String> shop_info(@Body RequestBody requestBody,@Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @DELETE("Shop_profiles/{id}")
    Call<String> deleteProfileData(@Path("id")String id,@Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @POST("Shops/active-times-info")
    Call<ShopTimingModel> getShopTiming(@Body RequestBody requestBody, @Header("Authorization") String authHeader);

    @Headers("Content-Type: application/json")
    @POST("Shops/shop-update")
    Call<ShopTimingUpdationResponse> updateShopTiming(@Body RequestBody requestBody, @Header("Authorization") String authHeader);



}