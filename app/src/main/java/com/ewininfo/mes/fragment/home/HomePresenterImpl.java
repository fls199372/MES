package com.ewininfo.mes.fragment.home;

/**
 * Created by fulishuang on 2017/7/5.
 */

import android.content.Context;

import com.ewininfo.mes.module.menu.MenuList;


public class HomePresenterImpl implements HomePresenter, OnHomeFinishedListener {
    private HomeView HomeView;
    private HomeModel HomeModel;

    public HomePresenterImpl(HomeView HomeView) {
        this.HomeView = HomeView;
        this.HomeModel = new HomeModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password,String toke,Context context) {
        if (HomeView != null) {
            HomeView.showProgress();
        }

        HomeModel.login(username, password,toke, this,context);
    }

    @Override
    public void onDestroy() {
        HomeView = null;
    }



    @Override
    public void onNetworkUtils() {
        if (HomeView != null) {
            HomeView.setNetworkUtils();
            HomeView.hideProgress();
        }
    }

    @Override
    public void onFail(String error) {
        if (HomeView != null) {
            HomeView.setLoginFail(error);
            HomeView.hideProgress();
        }
    }

    @Override
    public void onSuccess(MenuList menuList) {
        if (HomeView != null) {
            HomeView.navigateToHome(menuList);
        }
    }
}
