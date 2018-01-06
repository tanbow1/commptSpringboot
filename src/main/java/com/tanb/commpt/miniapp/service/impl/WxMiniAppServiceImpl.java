package com.tanb.commpt.miniapp.service.impl;

import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.miniapp.service.IWxMiniAppService;
import org.springframework.stereotype.Service;

/**
 * Created by Tanbo on 2018/1/6.
 */
@Service("wxMiniService")
public class WxMiniAppServiceImpl implements IWxMiniAppService{
    @Override
    public JsonResponse login(JsonRequest jsonRequest) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDetailMsg("测试调用");
        return jsonResponse;
    }
}
