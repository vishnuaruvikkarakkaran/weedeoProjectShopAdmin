package com.weedeo.shopadmin.modules.phonesigninpage;

import com.weedeo.shopadmin.base.MvpBase;

public interface iPhoneSignIn {


    interface presenterView{

        void memberExist(String numberForOtp);

    }
    interface ActivityView extends MvpBase{

       void phonenumberValid(String numberForOtp);

    }

}
