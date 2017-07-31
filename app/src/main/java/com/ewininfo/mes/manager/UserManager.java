package com.ewininfo.mes.manager;

import com.ewininfo.mes.module.user.User;
import com.ewininfo.mes.module.user.UserContent;

/**
 * @description 单例管理登陆用户信息
 * @author lsfu
 * @date 2017年7月11日
 */
public class UserManager {

    private static UserManager userManager = null;
    private UserContent user = null;

    public static UserManager getInstance() {

        if (userManager == null) {

            synchronized (UserManager.class) {

                if (userManager == null) {

                    userManager = new UserManager();
                }
                return userManager;
            }
        } else {

            return userManager;
        }
    }

    /**
     * init the user
     *
     * @param user
     */
    public void setUser(UserContent user) {

        this.user = user;
    }
    //判断是否登录过
    public boolean hasLogined() {

        return user == null ? false : true;
    }

    /**
     * has user info
     *
     * @return
     */
    public UserContent getUser() {

        return this.user;
    }

    /**
     *退出
     */
    public void removeUser() {

        this.user = null;
    }
}

