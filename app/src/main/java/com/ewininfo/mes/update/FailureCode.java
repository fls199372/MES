package com.ewininfo.mes.update;

/**
 * Created by fulishuang on 2017/7/27.
 * 包含了下载过程中出现的所有的异常类型
 */

public  enum FailureCode{
    UnknownHost,SocKet,SocketTimeout,ConnectTimeout,IO,HttpResponse,JSON,Interrupted
}