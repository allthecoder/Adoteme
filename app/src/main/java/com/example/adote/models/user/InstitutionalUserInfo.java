package com.example.adote.models.user;

import java.io.Serializable;

public class InstitutionalUserInfo extends UserInfo implements Serializable {
    private String site;

    public InstitutionalUserInfo(String name, String contact, String address,
                           String site) {
        super(name, contact, address);
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
