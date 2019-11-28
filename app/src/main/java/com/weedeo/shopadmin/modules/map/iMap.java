package com.weedeo.shopadmin.modules.map;

import com.weedeo.shopadmin.base.MvpBase;

public interface iMap {

    interface AtivityView extends MvpBase{


        void successToShopTiming(String message);
    }

    interface PresenterView{

        void locationSaving(String shop_id, double latitude, double longitude);
    }
}
