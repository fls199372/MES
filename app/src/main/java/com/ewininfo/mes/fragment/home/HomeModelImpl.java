package com.ewininfo.mes.fragment.home;

import android.content.Context;

import com.ewininfo.mes.module.menu.MenuList;
import com.ewininfo.mes.network.http.RequestCenter;
import com.ewininfo.mes.util.NetworkUtils;
import com.mes.okhttp.listener.DisposeDataListener;

/**
 * Created by fulishuang on 2017/7/5.
 */

public class HomeModelImpl implements HomeModel {
    @Override
    public void login(final String username, final String password,final String toke, final OnHomeFinishedListener listener, final Context context) {

        boolean error = false;
        if (!NetworkUtils.isNetAvailable(context.getApplicationContext())) {
            listener.onNetworkUtils();
            error = true;
        }
        if (!error) {
            RequestCenter.getMenu(username, password,toke, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    //完成我们的逻辑
                    /**
                     * 这部分可以封装起来，封装为到一个登陆流程类中
                     */
                    MenuList user = (MenuList) responseObj;
                    if (user.code.equals("10000")) {

                        /**
                         * 还应该将用户信息存入数据库，这样可以保证用户打开应用后总是登陆状态
                         * 只有用户手动退出登陆时候，将用户数据从数据库中删除。
                         */
                        listener.onSuccess(user);

                    } else {
                        listener.onFail(user.message);
                    }
                }

                @Override
                public void onFailure(Object reasonObj) {
                    //提示错误
                    listener.onFail("");

                }
            });

        }

    }



}
