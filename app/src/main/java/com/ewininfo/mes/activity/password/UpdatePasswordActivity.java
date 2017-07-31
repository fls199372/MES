package com.ewininfo.mes.activity.password;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ewininfo.mes.R;
import com.ewininfo.mes.base.BaseActivity;
import com.ewininfo.mes.manager.UserManager;
import com.ewininfo.mes.util.ButtonUtils;
import com.ewininfo.mes.widget.ClearEditText;
import com.mes.loader.LoaderProgress;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fulishuang on 2017/7/26.
 * 修改密码
 */

public class UpdatePasswordActivity extends BaseActivity implements View.OnClickListener,UpdatePasswordView {
    private TextView tv_title, tv_edit;
    private ImageView img_view;
    private ClearEditText tv_new_password, tv_confirm_password;
    private Button bt_ok;
    private UpdatePasswordPresenter presenter;
     Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password_layout);
        initView();
    }

    //初始化控件
    public void initView() {
        presenter = new UpdatePasswordPresenterImpl(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(R.string.update_password);

        img_view = (ImageView) findViewById(R.id.img_view);
        img_view.setOnClickListener(this);

        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setVisibility(View.GONE);
        /**
         * 新密码
         */
        tv_new_password = (ClearEditText) findViewById(R.id.tv_new_password);
        tv_new_password.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /**
                 * 判断是否输入参数
                 */
                if (s.length() > 5) {
                    if (tv_confirm_password.getText().length() > 5) {
                        bt_ok.setEnabled(true);
                        bt_ok.setBackgroundColor(ContextCompat.getColor(UpdatePasswordActivity.this,
                                R.color.color_ff7231));
                    } else {
                        bt_ok.setEnabled(false);
                        bt_ok.setBackgroundColor(ContextCompat.getColor(UpdatePasswordActivity.this,
                                R.color.color_dcdcdc));
                    }
                } else {
                    bt_ok.setEnabled(false);
                    bt_ok.setBackgroundColor(ContextCompat.getColor(UpdatePasswordActivity.this,
                            R.color.color_dcdcdc));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        /**
         * 确认密码
         */
        tv_confirm_password = (ClearEditText) findViewById(R.id.tv_confirm_password);
        tv_confirm_password.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5) {
                    if (tv_new_password.getText().length() > 5) {
                        bt_ok.setEnabled(true);
                        bt_ok.setBackgroundColor(ContextCompat.getColor(UpdatePasswordActivity.this,
                                R.color.color_ff7231));
                    } else {
                        bt_ok.setEnabled(false);
                        bt_ok.setBackgroundColor(ContextCompat.getColor(UpdatePasswordActivity.this,
                                R.color.color_dcdcdc));
                    }
                } else {
                    bt_ok.setEnabled(false);
                    bt_ok.setBackgroundColor(ContextCompat.getColor(UpdatePasswordActivity.this,
                            R.color.color_dcdcdc));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bt_ok = (Button) findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (img_view == v) {
            finish();
        } else if (v == bt_ok) {
            if (!ButtonUtils.isFastDoubleClick(R.id.bt_ok)) {
                mDialog= LoaderProgress.show(UpdatePasswordActivity.this, "", true, null);

                presenter.validateCredentials(UserManager.getInstance().getUser().getNumber(),
                        tv_confirm_password.getText().toString(),
                        tv_new_password.getText().toString(),
                        UserManager.getInstance().getUser().getToken(),this);

            }

        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setNetworkUtils() {

    }

    @Override
    public void setLoginFail(String error) {
        mDialog.dismiss();
        postMainHandler(error);
    }

    @Override
    public void navigateToHome(String message) {
        mDialog.dismiss();
        postMainHandler(message);
        //使用Java定时器实现延时跳转
        Timer timer = new Timer();
        timer.schedule(new Task(), 1500);//定时器延时执行任务的方法
    }
    class Task extends TimerTask {

        @Override
        public void run() {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
