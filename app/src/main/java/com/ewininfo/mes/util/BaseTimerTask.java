package com.ewininfo.mes.util;

import java.util.TimerTask;

/**
 * Created by fulishuang on 2017/7/27.
 * 倒计时类
 */


public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}