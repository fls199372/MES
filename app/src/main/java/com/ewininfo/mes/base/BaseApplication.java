package com.ewininfo.mes.base;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.ewininfo.mes.bean.DaoMaster;
import com.ewininfo.mes.bean.DaoSession;
import com.squareup.leakcanary.LeakCanary;

import java.util.LinkedList;

/**
 * Created by fulishuang on 2017/6/12.
 */

public class BaseApplication extends Application {
    private static BaseApplication mApplication = null;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        LeakCanary.install(this);
        setupDatabase();
        // Normal app init code...
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "mes.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }
    public static BaseApplication GetInstance() {
        return mApplication;
    }
    /**
     * 使用集合类统一理Activity实例
     */
    private LinkedList<Activity> activityList = new LinkedList<Activity>();
    /**
     * 设备的宽
     */
    private int screenWidth = 0;
    /**
     * 设备的高
     */
    private int screenHeight = 0;

    /**
     * 得到屏幕的宽
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * 得到屏幕的高
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * 添加Activity到容器，在每个Activity的onCreate调用
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 从Activity到容器中移除，在每个Activity的onDestroy调用
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 遍历Activity并finish，在主界面当点击back键或者在要退出程序的时候调用
     */
    public void exit() {
        // 遍历所有存在的Activity实例，挨个finish
        for (Activity activity : activityList) {
            if (activity != null)
                activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());// 获取当前进程PID，并�?��
    }

}
