package com.tanb.commpt.miniapp.service;

import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;

/**
 * Created by Tanbo on 2018/1/6.
 */
public interface IWxMiniAppService {

    JsonResponse login(JsonRequest jsonRequest);
}
