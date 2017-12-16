package com.tanb.commpt.miniapp.po;

import com.tanb.commpt.core.po.comm.Base;

public class BzUserLoggerKey extends Base {
    private String loggerId;

    private String userId;

    public String getLoggerId() {
        return loggerId;
    }

    public void setLoggerId(String loggerId) {
        this.loggerId = loggerId == null ? null : loggerId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}