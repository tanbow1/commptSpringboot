package com.tanb.commpt.core.po;

public class DmCard extends DmCardKey {
    private String cardName;

    private String status;

    private String parentId;

    private String cardSimple;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
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

    public String getCardSimple() {
        return cardSimple;
    }

    public void setCardSimple(String cardSimple) {
        this.cardSimple = cardSimple == null ? null : cardSimple.trim();
    }
}