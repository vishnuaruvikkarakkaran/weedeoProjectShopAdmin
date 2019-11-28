package com.weedeo.shopadmin.data;

import com.weedeo.shopadmin.Utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static long connectTimeout = 50;
    private static long readTimeout = 2;
    private static long writeTimeout = 5;
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(chain -> {
                        Request request = chain.request().newBuilder().addHeader("header", "").build();
                        return chain.proceed(request);
                    })
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.MINUTES)
                    .writeTimeout(writeTimeout, TimeUnit.MINUTES)
                    .build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }
}
