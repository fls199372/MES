package com.ewininfo.mes.fragment.home;


import android.content.Context;



/**
 * Created by Anthony on 2016/2/15.
 */
public interface HomeModel {
    void login(String username, String password,String toke
            , OnHomeFinishedListener listener, Context context);
}