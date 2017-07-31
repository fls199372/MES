package com.ewininfo.mes.activity.login;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:登陆View的接口，实现类也就是登陆的activity
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();
    void setNetworkUtils();
    void setLoginFail(String error);

    void navigateToHome();
}
