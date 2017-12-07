package com.tanb.commpt.core.po;


import com.tanb.commpt.core.po.comm.Base;

public class XtUserAddressKey extends Base {
    private String userId;

    private String address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}