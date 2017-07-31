package com.ewininfo.mes.activity.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.allenliu.versionchecklib.AVersionService;
import com.allenliu.versionchecklib.VersionParams;
import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.home.HomeActivity;
import com.ewininfo.mes.activity.upgrade.CustomVersionDialogActivity;
import com.ewininfo.mes.base.BaseActivity;
import com.ewininfo.mes.base.BaseApplication;
import com.ewininfo.mes.bean.User;
import com.ewininfo.mes.db.database.UserUtilityDao;
import com.ewininfo.mes.server.UpgradeServer;
import com.ewininfo.mes.util.ButtonUtils;
import com.ewininfo.mes.util.StatusBarUtil;
import com.ewininfo.mes.widget.ClearEditText;
import com.mes.loader.LoaderProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fulishuang on 2017/7/12.
 * 登录界面
 */

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {
    private List<User> shops;
    private Button login_button;
    private ClearEditText login_input_number, login_input_password;
    private LoginPresenter presenter;
    Dialog mDialog;
    private Long id = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        StatusBarUtil.transparencyBar(this);

        initView();
        queryDate();
        checkVersion();
    }

    public void initView() {
        login_input_number = (ClearEditText) findViewById(R.id.login_input_number);
        login_input_password = (ClearEditText) findViewById(R.id.login_input_password);

        login_button = (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);

    }

    private void queryDate() {
        shops = new ArrayList<>();
        shops = UserUtilityDao.queryLove();
        if (shops.size() >= 1) {
            for (int i = shops.size() - 1; i < shops.size(); i++) {
                id = shops.get(i).getId();
                login_input_number.setText(shops.get(i).getNumber());
                login_input_password.setText(shops.get(i).getPassword());
            }
        }
    }

    @Override
    public void showProgress() {
        //显示等待转圈
    }

    @Override
    public void hideProgress() {
        //关闭等待转圈
    }

    @Override
    public void setUsernameError() {
        login_input_number.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        login_input_password.setError(getString(R.string.username_error));
    }

    @Override
    public void setNetworkUtils() {
        Toast.makeText(this.getApplicationContext(), R.string.network, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoginFail(String error) {
        mDialog.dismiss();
        postMainHandler(error);

    }

    @Override
    public void navigateToHome() {
        mDialog.dismiss();
        queryDate();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == login_button) {
            if (!ButtonUtils.isFastDoubleClick(R.id.login_button)) {
                mDialog = LoaderProgress.show(this, "", true, null);
                presenter.validateCredentials(login_input_number.getText().toString(),
                        login_input_password.getText().toString(), this);
            }
        }
    }


    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出MES", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                BaseApplication.GetInstance().exit();
                System.exit(0);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void checkVersion() {
        // CommonDialog dialog=new CommonDialog(this,"检测到最新版本！是否更新","更新","OK","NO",);
        //you can add your request params and request method
        //eg.
//        HttpRequestMethod requestMethod=HttpRequestMethod.GET;
//        HttpHeaders httpHeaders=new HttpHeaders();
//        HttpParams httpParams=new HttpParams();

        //只有requsetUrl 是必须值 其他参数都有默认值，可选

        VersionParams versionParams = new VersionParams()
//                .setHttpHeaders(httpHeaders)
//                .setRequestMethod(requestMethod)
//                .setRequestParams(httpParams)
                .setRequestUrl("http://www.baidu.com");
        stopService(new Intent(this, UpgradeServer.class));
        CustomVersionDialogActivity.customVersionDialogIndex = 1;
        versionParams.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
        Intent intent = new Intent(this, UpgradeServer.class);
        intent.putExtra(AVersionService.VERSION_PARAMS_KEY, versionParams);
        startService(intent);

//        Intent intent=new Intent(LoginActivity.this, UpdateService.class);
//        intent.putExtra("apkUrl","http://www.apk3.com/uploads/soft/guiguangbao/UCllq.apk");
//        startService(intent);
    }
}
