package com.ewininfo.mes.module.user;


import com.ewininfo.mes.module.BaseModel;

/**
 * Created by renzhiqiang on 15/11/20.
 */
public class UserContent extends BaseModel {


    /**
     * token : 4f7b9260-f238-402b-ab10-bd6eb2bb0f2e
     * email (string, optional): 邮箱 ,
     mobile (string, optional): 手机号 ,
     name (string, optional): 中文名称 ,
     nameEn (string, optional): 英文文名称 ,
     * email : //邮箱
     * sex ://性别
     * mobile : //备注
     * status : 启用状态 (0-不启用， 1-启用)
     * resources : null
     */

    private String token;
    private int expireDate;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;
    private String no;
    private String name;
    private String nameEn;
    private String email;
    private String sex;
    private String mobile;
    private String remark;
    private int status;
    private Object resources;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(int expireDate) {
        this.expireDate = expireDate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResources() {
        return resources;
    }

    public void setResources(Object resources) {
        this.resources = resources;
    }
}
