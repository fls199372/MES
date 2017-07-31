package com.ewininfo.mes.fragment.home;

import com.ewininfo.mes.module.menu.MenuList;

/**
 * Created by fulishuang on  2017/7/5.
 * Class Note:登陆事件监听
 */
public interface OnHomeFinishedListener {

    void onNetworkUtils();

    void onFail(String error);
    void onSuccess(MenuList menuList);
}