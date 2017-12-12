package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmLocation extends Base {
    private String locationId;

    private String locationName;

    private String locationSimple;

    private String alphabetic;

    private String status;

    private String parentId;

    private String typeId;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId == null ? null : locationId.trim();
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getLocationSimple() {
        return locationSimple;
    }

    public void setLocationSimple(String locationSimple) {
        this.locationSimple = locationSimple == null ? null : locationSimple.trim();
    }

    public String getAlphabetic() {
        return alphabetic;
    }

    public void setAlphabetic(String alphabetic) {
        this.alphabetic = alphabetic == null ? null : alphabetic.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }
}