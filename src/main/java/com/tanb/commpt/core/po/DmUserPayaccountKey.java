package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmUserPayaccountKey extends Base {
    private String userId;

    private String payaccount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPayaccount() {
        return payaccount;
    }

    public void setPayaccount(String payaccount) {
        this.payaccount = payaccount == null ? null : payaccount.trim();
    }
}