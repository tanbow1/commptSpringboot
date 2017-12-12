package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmCodeKey extends Base {
    private String code;

    private String codeType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }
}