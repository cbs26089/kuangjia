package com.example.kuangjia.interfaces.home;

import com.example.kuangjia.interfaces.IBasePersenter;
import com.example.kuangjia.interfaces.IBaseView;
import com.example.kuangjia.moudls.bean.IndexBean;

;

public interface HomeConstract {

    interface View extends IBaseView {
        void getHomeDataReturn(IndexBean result);
    }

    interface Persenter extends IBasePersenter<View> {
        void getHomeData();
    }

}
