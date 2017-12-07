package com.tanb.commpt.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tanb.commpt.core.exception.BizLevelException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/9/4.
 */
public interface IAuthService {

    ConcurrentHashMap<String, String> saveJwt(String userId) throws Exception;

    ConcurrentHashMap<String, String> refreshToken(String accessToken, String refreshToken) throws BizLevelException, JsonProcessingException;

    ConcurrentHashMap<String, String> selectByAccessToken(String accessToken);

    ConcurrentHashMap<String, String> checkToken(String accessToken, String refreshToken) throws JsonProcessingException, BizLevelException;

}
