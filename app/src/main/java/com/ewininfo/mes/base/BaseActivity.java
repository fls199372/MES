package com.ewininfo.mes.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ewininfo.mes.util.StatusBarUtil;



/**
 * Created by fulishuang on 2017/6/12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public String TAG;
    public Handler mainHandler;
    public Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.GetInstance().addActivity(this);// 添加自己到activity管理集合
        TAG = getComponentName().getShortClassName();
        mainHandler = new Handler();
        context = this;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        BaseApplication.GetInstance().removeActivity(this);// 移除自己在activity管理集合
        super.onDestroy();
    }
    /**
     * 改变状态栏颜色
     *
     * @param color
     */
    public void changeStatusBarColor(@ColorRes int color) {
        StatusBarUtil.setStatusBarColor(this, color);
    }

    /**
     * 调整状态栏为亮模式，这样状态栏的文字颜色就为深模式了。
     */
    private void reverseStatusColor() {
        StatusBarUtil.statusBarLightMode(this);
    }
    public void postMainHandler(final String message){
        mainHandler.post(new Runnable() {

            @SuppressWarnings("unused")
            @Override
            public void run() {
                Toast toast=new Toast(context);
                if (toast != null)
                {
                    toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                } else
                {
                    toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
