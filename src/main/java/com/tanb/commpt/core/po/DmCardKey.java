package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmCardKey extends Base {
    private String cardId;

    private String cardType;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }
}