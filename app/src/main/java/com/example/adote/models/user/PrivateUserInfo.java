package com.example.adote.models.user;

import java.io.Serializable;

final public class PrivateUserInfo extends UserInfo implements Serializable {
    private String nickname;
    private String birthday;

    public PrivateUserInfo(String name, String contact, String address,
                           String nickname, String birthday) {
        super(name, contact, address);
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
