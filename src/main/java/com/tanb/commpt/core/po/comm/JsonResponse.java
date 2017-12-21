package com.tanb.commpt.core.po.comm;


import com.tanb.commpt.core.constant.ConsCommon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonResponse extends Base {

    private String code = ConsCommon.SUCCESS_CODE;
    private String msg = ConsCommon.SUCCESS;
    private String detailMsg = "";
    private ConcurrentHashMap<String, Object> data = new ConcurrentHashMap<String, Object>();

    public JsonResponse() {
        super();
    }

    public JsonResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public JsonResponse(String msg, String code, ConcurrentHashMap<String, Object> repData) {
        this.msg = msg;
        this.code = code;
        this.data = repData;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(ConcurrentHashMap<String, Object> data) {
        this.data = data;
    }
}
