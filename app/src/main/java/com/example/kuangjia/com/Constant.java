package com.example.kuangjia.com;



import com.example.kuangjia.apps.MyApp;

import java.io.File;

public class Constant {

    public static final String BASE_SHOP_URL = "https://cdwan.cn/api/";  //商城的基础地址


    //网络缓存的地址
    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/shop";


}
