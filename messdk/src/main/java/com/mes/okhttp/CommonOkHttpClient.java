package com.mes.okhttp;



import com.mes.okhttp.cookie.SimpleCookieJar;
import com.mes.okhttp.https.HttpsUtils;
import com.mes.okhttp.listener.DisposeDataHandle;
import com.mes.okhttp.response.CommonFileCallback;
import com.mes.okhttp.response.CommonJsonCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fulishuang on 2017/6/13.
 * @funciton  用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */

public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;



    //为我们的Client配置参数
    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();


        /**
         *  为所有请求添加请求头，看个人需求
         */
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .addHeader("Cookie", "add cookies here")
                        .build();
                return chain.proceed(request);
            }
        });
        //创建我们的Client对象的构建者
        okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        //是为构建者提供超时时间
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        //允许从定项
        okHttpClientBuilder.followRedirects(true);
        //添加支持所有的https支持
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        /**
         * 信任所有HTTPS点
         */
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        //生成我们的Client对象
        mOkHttpClient = okHttpClientBuilder.build();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

//    /**
//     * 指定cilent信任指定证书
//     *
//     * @param certificates
//     */
//    public static void setCertificates(InputStream... certificates) {
//        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
//    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @
     */
    public static Call sendRequest(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}

