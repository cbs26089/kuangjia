package com.example.kuangjia.moudls.api;



import com.example.kuangjia.moudls.bean.IndexBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ShopApi {

    @GET("index")
    Flowable<IndexBean> getIndexData();

}
