package com.ewininfo.mes.activity.password;

/**
 * Created by fulishuang on 2017/7/5.
 */

import android.content.Context;

import com.ewininfo.mes.activity.login.LoginModel;
import com.ewininfo.mes.activity.login.LoginModelImpl;
import com.ewininfo.mes.activity.login.LoginPresenter;
import com.ewininfo.mes.activity.login.LoginView;
import com.ewininfo.mes.activity.login.OnLoginFinishedListener;


/**
 * Created by Anthony on 2016/2/15.
 * Class Note:
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2  presenter里面还有个OnLoginFinishedListener，
 * 其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，
 * LoginPresenterImpl只 有View和Model的引用那么Model怎么把结果告诉View呢？
 */
public class UpdatePasswordPresenterImpl implements UpdatePasswordPresenter, OnUpdatePasswordFinishedListener {
    private UpdatePasswordView View;
    private UpdatePasswordModel Model;

    public UpdatePasswordPresenterImpl(UpdatePasswordView loginView) {
        this.View = loginView;
        this.Model = new UpdatePasswordModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password,String confirmPassword,String token,Context context) {
        if (View != null) {
            View.showProgress();
        }
        Model.login(username, password,confirmPassword,token, this,context);
    }
    @Override
    public void onDestroy() {
        View = null;
    }

    @Override
    public void onNetworkUtils() {
        if (View != null) {
            View.setNetworkUtils();
            View.hideProgress();
        }
    }

    @Override
    public void onFail(String error) {
        if (View != null) {
            View.setLoginFail(error);
            View.hideProgress();
        }
    }

    @Override
    public void onSuccess(String message) {
        if (View != null) {
            View.navigateToHome(message);
        }
    }
}
