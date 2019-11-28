package com.weedeo.shopadmin.Utils;

public class Constants {

    public static final String BASE_URL = "https://y3ifkxtem7.execute-api.ap-south-1.amazonaws.com/dev/api/" ;
    //public static final String BASE_URL = "http://www.mocky.io/v2/" ;
    public static final String PROFILE_IMAGE_PATH_S3 = "shop/";
    public static final String KEY_PRIMARY_IMAGE ="profilePrimary" ;

    public static final int PROFILE_ASPECT_RATIO_X = 1;
    public static final int PROFILE_ASPECT_RATIO_Y = 1;
    public static final int IMAGE_COMPRESSION = 80;
    public static final String EXTENSION_PNG = ".png";
    public static final String SHOP_LOCATION ="shopLocation" ;
    public static final String PRIMARY_IMAGE_STRING ="primary_image_string" ;
    public static final String AWS_BUCKET_NAME = "weedeo-dev-ap-south-1";
    public static final String LOG_PATH = "logs/shop_admin/";
   // public static final String PRIMARY_IMAGE_URL = "https://y3ifkxtem7.execute-api.ap-south-1.amazonaws.com/dev/shop/";
    public static final String PRIMARY_IMAGE_URL = "https://weedeo-dev-ap-south-1.s3.ap-south-1.amazonaws.com/shop/";
    public static final String PROFILE_IMAGE_EXTENTION = "https://weedeo-dev-ap-south-1.s3.ap-south-1.amazonaws.com/profile_image/";

    public static final String SHOP_PROFILE = "shop_profile/";


    public static String LOGGING_URL = "https://eneviq771l4b7.x.pipedream.net/";
    public static String AWS_ACCESS_KEY = "AKIASPZI7LEKM2IMBQMQ";
    public static String AWS_SECRET_KEY = "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";


    /*Intent Keys*/
    public static String PLACE = "place";


    public static String USER_TYPE = "shop_admin";


    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 400;

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    public static final String KEY_USER_ID = "userId";
    public static final String KEY_SHOP_ID = "shopId";
    public static final String KEY_TOCKEN = "token";
    public static final String SHOP_NAME ="shop_name" ;
    public static final String MOBILE_NUMBER ="primary_number";
    public static final String ALL ="all" ;

    public static final String KEY_FCM_UID = "fcmUid";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_EMAIL = "userEmail";
    public static final String KEY_USER_PHONE_NUMBER = "userNumber";
    public static final String KEY_USER_IMAGE = "userImage";
    public static final String KEY_DEVICE_ID = "deviceId";
    public static final String KEY_ACCESS_TOKEN = "accessToken";



    // sharedpreference data
    public static final String PREFS_NAME = "admin_data";

    public static int IN_A_CALL = 0;
}
