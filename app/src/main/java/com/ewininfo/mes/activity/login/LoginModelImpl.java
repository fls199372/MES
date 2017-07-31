package com.ewininfo.mes.activity.login;

import android.content.Context;
import android.text.TextUtils;

import com.ewininfo.mes.db.database.UserUtilityDao;
import com.ewininfo.mes.manager.UserManager;
import com.ewininfo.mes.module.user.User;
import com.ewininfo.mes.module.user.UserContent;
import com.ewininfo.mes.network.http.RequestCenter;
import com.ewininfo.mes.util.NetworkUtils;
import com.ewininfo.mes.util.Utils;
import com.mes.okhttp.listener.DisposeDataListener;

/**
 * Created by fulishuang on 2017/7/5.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final String number, final String password, final OnLoginFinishedListener listener, final Context context) {

        boolean error = false;
        if (TextUtils.isEmpty(number)) {
            listener.onUsernameError();//model层里面回调listener
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }
        if (!NetworkUtils.isNetAvailable(context.getApplicationContext())) {
            listener.onNetworkUtils();
            error = true;
        }
        if (!error) {
            RequestCenter.login(number, password, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    //完成我们的逻辑
                    /**
                     * 这部分可以封装起来，封装为到一个登陆流程类中
                     */
                    User user = (User) responseObj;
                    if (user.code.equals("10000")) {

                        UserContent userContent = (UserContent) Utils.JSONToObj(user.results, UserContent.class);
                        userContent.setNumber(number);
                        UserManager.getInstance().setUser(userContent);//保存当前用户单例对象

                        /**
                         * 还应该将用户信息存入数据库，这样可以保证用户打开应用后总是登陆状态
                         * 只有用户手动退出登陆时候，将用户数据从数据库中删除。
                         */
                        insertUserInfoIntoDB(number,password);
                        listener.onSuccess();

                    } else {
                        listener.onFail(user.message);
                    }
                }

                @Override
                public void onFailure(Object reasonObj) {
                    listener.onFail("");
                    //提示错误

                }
            });
        }

    }

    private void addDate(String number,String password) {
        com.ewininfo.mes.bean.User shop = new com.ewininfo.mes.bean.User();
        shop.setId(shop.getId());
        shop.setNumber(number);
        shop.setPassword(password);
        UserUtilityDao.updateLove(shop);
    }

    /**
     * 用户信息存入数据库，以使让用户一打开应用就是一个登陆过的状态
     */
    private void insertUserInfoIntoDB(String number,String password) {
        addDate(number,password);
    }
}
