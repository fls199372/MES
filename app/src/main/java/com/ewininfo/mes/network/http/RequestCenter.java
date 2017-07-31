package com.ewininfo.mes.network.http;

import com.ewininfo.mes.module.menu.MenuList;
import com.ewininfo.mes.module.user.User;
import com.mes.okhttp.CommonOkHttpClient;
import com.mes.okhttp.listener.DisposeDataHandle;
import com.mes.okhttp.listener.DisposeDataListener;
import com.mes.okhttp.request.CommonRequest;
import com.mes.okhttp.request.RequestParams;

/**
 * Created by fulishuang on 2017/6/16.
 * @author: fulishuang
 * @function: 封装请求
 */

public class RequestCenter {

    //不需要toke的get请求
    public static void getRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.sendRequest(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }
    //不需要toke的post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));
    }
    //需要toke的post请求
    public static void postRequestToke(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz,RequestParams toke) {
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params,toke), new DisposeDataHandle(listener, clazz));
    }
    /**
     * 应用版本号请求
     *
     * @param listener
     */
   /** public static void checkVersion(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.CHECK_UPDATE, null, listener, UpdateModel.class);
    }*/


    /**
     * 用户登陆请求
     *
     * @param listener
     * @param userName 账号
     * @param password 密码
     */
    public static void login(String userName, String password, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("no", userName);
        params.put("password", password);

        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }
    /**
     * 修改密码
     *
     * @param listener
     * @param userName 账号
     * @param password 密码
     */
    public static void updatePassword(String userName, String password,String toke, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("no", userName);
        params.put("password", password);
        RequestParams tokeParams = new RequestParams();
        tokeParams.put("Authorization", toke);
        tokeParams.put("Accept", "*/*");

        RequestCenter.postRequestToke(HttpConstants.UPDATE_PASSWORD, params, listener, User.class,tokeParams);
    }

    /**
     *  获取权限菜单
     * @param userName
     * @param password
     * @param toke
     * @param listener
     */
    public static void getMenu(String userName, String password,String toke, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("no", userName);
        params.put("password", password);
        RequestParams tokeParams = new RequestParams();
        tokeParams.put("Authorization", toke);
        tokeParams.put("Accept", "*/*");

        RequestCenter.postRequestToke(HttpConstants.MENU, params, listener, MenuList.class,tokeParams);
    }

}
