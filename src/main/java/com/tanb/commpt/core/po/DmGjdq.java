package com.tanb.commpt.core.po;


import com.tanb.commpt.core.po.comm.Base;

public class DmGjdq extends Base {
    private String uuid;

    private String gjdqMcZ;

    private String gjdqMcE;

    private String gjdqMcdm;

    private String gjdqDhdm;

    private String yxbj;

    private String gjdqId;

    private String sc;

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        if (null == sc) {
            sc = "";
        }
        this.sc = sc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGjdqMcZ() {
        return gjdqMcZ;
    }

    public void setGjdqMcZ(String gjdqMcZ) {
        this.gjdqMcZ = gjdqMcZ == null ? null : gjdqMcZ.trim();
    }

    public String getGjdqMcE() {
        return gjdqMcE;
    }

    public void setGjdqMcE(String gjdqMcE) {
        this.gjdqMcE = gjdqMcE == null ? null : gjdqMcE.trim();
    }

    public String getGjdqMcdm() {
        return gjdqMcdm;
    }

    public void setGjdqMcdm(String gjdqMcdm) {
        this.gjdqMcdm = gjdqMcdm == null ? null : gjdqMcdm.trim();
    }

    public String getGjdqDhdm() {
        return gjdqDhdm;
    }

    public void setGjdqDhdm(String gjdqDhdm) {
        this.gjdqDhdm = gjdqDhdm == null ? null : gjdqDhdm.trim();
    }

    public String getYxbj() {
        return yxbj;
    }

    public void setYxbj(String yxbj) {
        this.yxbj = yxbj == null ? null : yxbj.trim();
    }

    public String getGjdqId() {
        return gjdqId;
    }

    public void setGjdqId(String gjdqId) {
        this.gjdqId = gjdqId == null ? null : gjdqId.trim();
    }
}