package com.tanb.commpt.core.po;

public class DmCode extends DmCodeKey {
    private String codeName;

    private String codeDesc;

    private String codeStatus;

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc == null ? null : codeDesc.trim();
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus == null ? null : codeStatus.trim();
    }
}