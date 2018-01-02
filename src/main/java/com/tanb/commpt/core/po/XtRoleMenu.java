package com.tanb.commpt.core.po;

public class XtRoleMenu extends XtRoleMenuKey {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}