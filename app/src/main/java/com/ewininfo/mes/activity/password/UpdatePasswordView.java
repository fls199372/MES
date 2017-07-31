package com.ewininfo.mes.activity.password;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:登陆View的接口，实现类也就是登陆的activity
 */
public interface UpdatePasswordView {
    void showProgress();

    void hideProgress();
    void setNetworkUtils();
    void setLoginFail(String error);

    void navigateToHome(String message);
}
