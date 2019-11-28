package com.weedeo.shopadmin.modules.otpPage;

import com.weedeo.shopadmin.base.MvpBase;

public interface iOtpVerification {

    // presenter

     interface ActivityView extends MvpBase {

        void timerZero(long millisUntilFinished);
        void timerFinish();
        void successToProfile(String shop_id, String id, String shop_name, long primary_mobile);
     }
    interface  presenter{

         void timerStart();

        void memberCheck(String mobile, String uid, String fcmDeviceId);
    }
}
