package com.tanb.commpt.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tanb.commpt.core.exception.BizLevelException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/9/4.
 */
public interface IAuthService {

    ConcurrentHashMap<String, String> saveJwt(String userId) throws Exception;

    ConcurrentHashMap<String, String> refreshToken(String userId, String accessToken, String refreshToken, boolean isCheck) throws BizLevelException, JsonProcessingException;

    ConcurrentHashMap<String, String> selectTokenInfoByUserAccessToken(String userId, String accessToken);

    ConcurrentHashMap<String, String> checkToken(String userId, String accessToken, String refreshToken) throws JsonProcessingException, BizLevelException;

}
