package com.ewininfo.mes.activity.password;


import android.content.Context;

import com.ewininfo.mes.activity.login.OnLoginFinishedListener;


/**
 * Created by Anthony on 2016/2/15.
 * Class Note:模拟登陆的操作的接口，实现类为LoginModelImpl.相当于MVP模式中的Model层
 */
public interface UpdatePasswordModel {
    void login(String username, String password,String confirmPassword,String token
            , OnUpdatePasswordFinishedListener listener, Context context);
}