package com.example.kuangjia.persenter.home;


import com.example.kuangjia.base.BasePersenter;
import com.example.kuangjia.com.CommonSubscriber;
import com.example.kuangjia.interfaces.home.HomeConstract;
import com.example.kuangjia.moudls.HttpManager;
import com.example.kuangjia.moudls.bean.IndexBean;

import com.example.kuangjia.utils.RxUtils;

public class HomePersenter extends BasePersenter<HomeConstract.View> implements HomeConstract.Persenter {

    //获取主页的具体实现
    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getInstance().getShopApi().getIndexData()
        .compose(RxUtils.<IndexBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<IndexBean>(mView){

            @Override
            public void onNext(IndexBean indexBean) {
                mView.getHomeDataReturn(indexBean);
            }
        }));
    }

}
