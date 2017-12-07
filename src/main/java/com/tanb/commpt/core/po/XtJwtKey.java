package com.tanb.commpt.core.po;


import com.tanb.commpt.core.po.comm.Base;

public class XtJwtKey extends Base {
    private String userId;

    private String accessToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }
}