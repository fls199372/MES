package com.ewininfo.mes.fragment.home;

import com.ewininfo.mes.module.menu.MenuList;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:登陆View的接口，实现类也就是登陆的activity
 */
public interface HomeView {
    void showProgress();

    void hideProgress();

    void setNetworkUtils();
    void setLoginFail(String error);

    void navigateToHome(MenuList menuList);
}
