package com.tanb.commpt.core.po;


import com.tanb.commpt.core.po.comm.Base;

public class DmLog extends Base {
    private String logType;

    private String logDes;

    private String yxbj;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public String getLogDes() {
        return logDes;
    }

    public void setLogDes(String logDes) {
        this.logDes = logDes == null ? null : logDes.trim();
    }

    public String getYxbj() {
        return yxbj;
    }

    public void setYxbj(String yxbj) {
        this.yxbj = yxbj == null ? null : yxbj.trim();
    }
}