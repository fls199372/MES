package com.ewininfo.mes.activity.password;

import android.content.Context;
import android.text.TextUtils;

import com.ewininfo.mes.db.database.UserUtilityDao;
import com.ewininfo.mes.module.user.User;
import com.ewininfo.mes.module.user.UserContent;
import com.ewininfo.mes.network.http.RequestCenter;
import com.ewininfo.mes.util.NetworkUtils;
import com.ewininfo.mes.util.Utils;
import com.mes.okhttp.listener.DisposeDataListener;

/**
 * Created by fulishuang on 2017/7/5.
 */

public class UpdatePasswordModelImpl implements UpdatePasswordModel {
    @Override
    public void login(final String number, final String password, final String confirmPassword, final String token, final OnUpdatePasswordFinishedListener listener, final Context context) {

        boolean error = false;
        if (TextUtils.isEmpty(number)) {
            listener.onFail("用户名不能为空");
            error = true;
        } else if (TextUtils.isEmpty(password.trim())) {
            listener.onFail("新密码不能为空");
            error = true;
        } else if (TextUtils.isEmpty(confirmPassword.trim())) {
            listener.onFail("确认密码不能为空");
            error = true;
        } else if (!password.equals(confirmPassword)) {
            listener.onFail("新密码和确认密码不一样");
            error = true;
        } else if (!NetworkUtils.isNetAvailable(context.getApplicationContext())) {
            listener.onNetworkUtils();
            error = true;
        } else if (!error) {
            RequestCenter.updatePassword(number, password, token, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    //完成我们的逻辑
                    /**
                     * 这部分可以封装起来，封装为到一个登陆流程类中
                     */
                    User user = (User) responseObj;
                    if (user.code.equals("10000")) {
                        //UserManager.getInstance().setUser(user);//保存当前用户单例对象

                        UserContent userContent = (UserContent) Utils.JSONToObj(user.results, UserContent.class);
                        /**
                         * 还应该将用户信息存入数据库，这样可以保证用户打开应用后总是登陆状态
                         * 只有用户手动退出登陆时候，将用户数据从数据库中删除。
                         */
                        insertUserInfoIntoDB(number, password);
                        listener.onSuccess(user.message);

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
    private void insertUserInfoIntoDB(String number, String password) {
        addDate(number, password);
    }
}
