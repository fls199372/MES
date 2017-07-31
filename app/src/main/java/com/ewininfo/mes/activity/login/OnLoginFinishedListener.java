package com.ewininfo.mes.activity.login;

/**
 * Created by fulishuang on  2017/7/5.
 * Class Note:登陆事件监听
 */
public interface OnLoginFinishedListener {

    void onUsernameError();

    void onPasswordError();
    void onNetworkUtils();

    void onFail(String error);
    void onSuccess();
}