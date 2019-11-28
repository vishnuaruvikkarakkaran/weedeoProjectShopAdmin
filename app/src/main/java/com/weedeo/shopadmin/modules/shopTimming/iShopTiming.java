package com.weedeo.shopadmin.modules.shopTimming;

import com.weedeo.shopadmin.base.MvpBase;
import com.weedeo.shopadmin.model.request.ShopTimingModel;

public interface iShopTiming {


    interface ActivityView extends MvpBase {
        void shopTimingResponse(ShopTimingModel shopTimingModel);
    }

    interface PresenterView {

        void getShopTiming();

        void updateShopTiming(ShopTimingModel shopTiming);
    }
}
