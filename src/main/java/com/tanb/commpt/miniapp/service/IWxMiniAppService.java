package com.tanb.commpt.miniapp.service;

import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tanbo on 2018/1/6.
 */
public interface IWxMiniAppService {

    JsonResponse login(JsonRequest jsonRequest) throws BizLevelException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
