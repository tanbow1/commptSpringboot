package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmNationality extends Base {
    private String nationalityId;

    private String nationalityNameZh;

    private String nationalityNameEn;

    private String nationalityEnSimple;

    private String alphabetic;

    private String status;

    private String nationalityZhSimple;

    private String formalDm;

    private String reserveDm;

    public String getFormalDm() {
        return formalDm;
    }

    public void setFormalDm(String formalDm) {
        this.formalDm = formalDm;
    }

    public String getReserveDm() {
        return reserveDm;
    }

    public void setReserveDm(String reserveDm) {
        this.reserveDm = reserveDm;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId == null ? null : nationalityId.trim();
    }

    public String getNationalityNameZh() {
        return nationalityNameZh;
    }

    public void setNationalityNameZh(String nationalityNameZh) {
        this.nationalityNameZh = nationalityNameZh == null ? null : nationalityNameZh.trim();
    }

    public String getNationalityNameEn() {
        return nationalityNameEn;
    }

    public void setNationalityNameEn(String nationalityNameEn) {
        this.nationalityNameEn = nationalityNameEn == null ? null : nationalityNameEn.trim();
    }

    public String getNationalityEnSimple() {
        return nationalityEnSimple;
    }

    public void setNationalityEnSimple(String nationalityEnSimple) {
        this.nationalityEnSimple = nationalityEnSimple == null ? null : nationalityEnSimple.trim();
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

    public String getNationalityZhSimple() {
        return nationalityZhSimple;
    }

    public void setNationalityZhSimple(String nationalityZhSimple) {
        this.nationalityZhSimple = nationalityZhSimple == null ? null : nationalityZhSimple.trim();
    }
}