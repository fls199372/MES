package com.ewininfo.mes.activity.start;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ewininfo.mes.R;
import com.ewininfo.mes.activity.login.LoginActivity;
import com.ewininfo.mes.base.BaseActivity;
import com.ewininfo.mes.util.BaseTimerTask;
import com.ewininfo.mes.util.ITimerListener;
import com.ewininfo.mes.util.LattePreference;
import com.ewininfo.mes.util.StatusBarUtil;

import java.text.MessageFormat;

import java.util.Timer;

/**
 * Created by fulishuang on 2017/7/27.
 */

public class StartActivity extends BaseActivity implements ITimerListener, View.OnClickListener {
    private AppCompatTextView tv_launcher_timer;
    private Timer mTimer = null;
    private int mCount = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_layout);
        StatusBarUtil.transparencyBar(this);

        initView();
        initTimer();
    }

    public void initView() {
        tv_launcher_timer = (AppCompatTextView) findViewById(R.id.tv_launcher_timer);
        tv_launcher_timer.setOnClickListener(this);
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        //  0 是立即开始  1000  是1秒
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTimer() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tv_launcher_timer != null) {
                    tv_launcher_timer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(StartActivity.this, StartGuideActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (tv_launcher_timer == v) {
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
                checkIsShowScroll();
            }
        }
    }
}
