package com.ewininfo.mes.db.database;

import com.ewininfo.mes.base.BaseApplication;
import com.ewininfo.mes.bean.User;
import com.ewininfo.mes.bean.UserDao;

import java.util.List;

/**
 * Created by fulishuang on 2017/7/17.
 */

public class UserUtilityDao {
    /**
     * 添加数据
     *
     * @param user
     */
    public static void insertLove(User user) {
        BaseApplication.getDaoInstant().getUserDao().insert(user);
    }

    /**
     * 删除数据
     *
     * @param
     */
    public static void deleteLove(User user) {
        User findUser = BaseApplication.getDaoInstant().getUserDao().queryBuilder()
                .where(UserDao.Properties.Number.eq(user.getNumber())).build().unique();
        if(findUser != null){
            BaseApplication.getDaoInstant().getUserDao().deleteByKey(findUser.getId());
        }
    }

    /**
     * 更新数据
     *
     * @param user
     */
    public static void updateLove(User user) {
        User findUser = BaseApplication.getDaoInstant().getUserDao().queryBuilder()
                .where(UserDao.Properties.Number.eq(user.getNumber())).build().unique();
        if(findUser != null) {
            findUser.setNumber(user.getNumber());
            findUser.setPassword(user.getPassword());
            BaseApplication.getDaoInstant().getUserDao().update(findUser);
        } else {
            BaseApplication.getDaoInstant().getUserDao().insert(user);
        }
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<User> queryLove() {
      // return BaseApplication.getDaoInstant().getUserDao().queryBuilder().where(com.ewininfo.mes.bean.UserUtilityDao.Properties.Nickname.eq("张三")).list();
        return BaseApplication.getDaoInstant().getUserDao().queryBuilder().list();

    }
}
