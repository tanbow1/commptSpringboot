package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmIndustry extends Base {
    private String industryId;

    private String industryName;

    private String mlbz;

    private String dlbz;

    private String zlbz;

    private String xlbz;

    private String parentId;

    private String status;

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }

    public String getMlbz() {
        return mlbz;
    }

    public void setMlbz(String mlbz) {
        this.mlbz = mlbz == null ? null : mlbz.trim();
    }

    public String getDlbz() {
        return dlbz;
    }

    public void setDlbz(String dlbz) {
        this.dlbz = dlbz == null ? null : dlbz.trim();
    }

    public String getZlbz() {
        return zlbz;
    }

    public void setZlbz(String zlbz) {
        this.zlbz = zlbz == null ? null : zlbz.trim();
    }

    public String getXlbz() {
        return xlbz;
    }

    public void setXlbz(String xlbz) {
        this.xlbz = xlbz == null ? null : xlbz.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}