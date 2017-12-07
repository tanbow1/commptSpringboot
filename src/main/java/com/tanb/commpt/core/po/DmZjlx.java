package com.tanb.commpt.core.po;

import com.tanb.commpt.core.po.comm.Base;

public class DmZjlx extends Base {
    private String cardType;

    private String cardName;

    private String yxbj;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getYxbj() {
        return yxbj;
    }

    public void setYxbj(String yxbj) {
        this.yxbj = yxbj == null ? null : yxbj.trim();
    }
}