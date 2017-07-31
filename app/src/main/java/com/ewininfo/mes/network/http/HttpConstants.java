package com.ewininfo.mes.network.http;

/**
 * @author: qndroid
 * @function: 所有请求相关地址
 * @date: 17-07-07
 */
public class HttpConstants {
    /**
     * 方便我们替换服务器地址
     */
    private static final String ROOT_URL = "http://10.188.2.43:8088/ewap-auth/";

    /**
     * 请求本地产品列表
     */
    public static String PRODUCT_LIST = ROOT_URL + "/fund/search.php";

    /**
     * 本地产品列表更新时间措请求
     */
    public static String PRODUCT_LATESAT_UPDATE = ROOT_URL + "/fund/upsearch.php";

    /**
     * 登陆接口
     */
    public static String LOGIN = ROOT_URL + "system/user/login?";


    public static String MENU = ROOT_URL + "system/user/getMenu?";

    /**
     *
     */
    public static String UPDATE_PASSWORD=ROOT_URL+"system/user/updatePassword?";
    /**
     * 检查更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "/config/check_update.php";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "/product/course_detail.php";

}


