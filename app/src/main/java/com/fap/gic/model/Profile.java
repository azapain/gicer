package com.fap.gic.model;

/**
 * Created by Darith on 7/8/2016.
 */
public class Profile {


    public static final String USER_ID = "userId";
    public static final String LOGIN_ID = "loginId";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String AVATAR = "avatar";
    String userId;
    String name;
    String phone;
    int loginId;
    String avatar;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
