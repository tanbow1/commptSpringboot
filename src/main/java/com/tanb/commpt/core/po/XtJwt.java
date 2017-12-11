package com.tanb.commpt.core.po;

import java.util.Date;

public class XtJwt extends XtJwtKey {
    private String refreshToken;

    private Date jwtTtl;

    private Date jwtRefreshTtl;

    private Date jwtRefreshInterval;

    private Date createTime;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public Date getJwtTtl() {
        return jwtTtl;
    }

    public void setJwtTtl(Date jwtTtl) {
        this.jwtTtl = jwtTtl;
    }

    public Date getJwtRefreshTtl() {
        return jwtRefreshTtl;
    }

    public void setJwtRefreshTtl(Date jwtRefreshTtl) {
        this.jwtRefreshTtl = jwtRefreshTtl;
    }

    public Date getJwtRefreshInterval() {
        return jwtRefreshInterval;
    }

    public void setJwtRefreshInterval(Date jwtRefreshInterval) {
        this.jwtRefreshInterval = jwtRefreshInterval;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}