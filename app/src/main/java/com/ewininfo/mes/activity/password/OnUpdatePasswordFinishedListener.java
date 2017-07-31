package com.ewininfo.mes.activity.password;

/**
 * Created by fulishuang on  2017/7/5.
 * Class Note:登陆事件监听
 */
public interface OnUpdatePasswordFinishedListener {

    void onNetworkUtils();

    void onFail(String error);
    void onSuccess(String message);
}