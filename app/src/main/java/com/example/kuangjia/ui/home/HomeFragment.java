package com.example.kuangjia.ui.home;


import com.example.kuangjia.R;
import com.example.kuangjia.base.BaseFragment;
import com.example.kuangjia.interfaces.home.HomeConstract;
import com.example.kuangjia.moudls.bean.IndexBean;
import com.example.kuangjia.persenter.home.HomePersenter;

public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View {
    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getHomeData();
    }

    @Override
    protected HomeConstract.Persenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void getHomeDataReturn(IndexBean result) {

    }
}