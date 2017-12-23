package com.tanb.commpt.core.po;

public class xtRoleMenu extends xtRoleMenuKey {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}