package com.tanb.commpt.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.exception.SystemLevelException;
import com.tanb.commpt.core.mapper.XtUserJwtMapper;
import com.tanb.commpt.core.po.XtUserJwt;
import com.tanb.commpt.core.service.IAuthService;
import com.tanb.commpt.core.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/9/4.
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private XtUserJwtMapper xtJwtMapper;


    /**
     * 新增用户token
     *
     * @param userId
     * @return 返回用户id
     * jwt创建的accessToken，refreshToken
     * jwt新增记录数，成功插入则1
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {BizLevelException.class, SystemLevelException.class})
    public ConcurrentHashMap<String, String> saveJwt(String userId) throws JsonProcessingException {
        xtJwtMapper.deleteByUserId(userId);
        String subject = JwtUtil.generalSubject(userId);
        String accessToken = jwt.createJWT(ConsCommon.JWT_ID, subject, ConsCommon.JWT_TTL);
        String refreshToken = jwt.createJWT(ConsCommon.JWT_ID, subject, ConsCommon.JWT_REFRESH_TTL);
        XtUserJwt xtJwt = new XtUserJwt();
        xtJwt.setUserId(userId);
        xtJwt.setAccessToken(accessToken);
        xtJwt.setRefreshToken(refreshToken);
        int insertCount = xtJwtMapper.insert2(xtJwt);
        ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<String, String>();
        resultMap.put("insertCount", String.valueOf(insertCount));
        resultMap.put("userId", userId);
        resultMap.put("accessToken", accessToken);
        resultMap.put("refreshToken", refreshToken);
        return resultMap;
    }

    /**
     * 主动刷新token
     *
     * @param accessToken
     * @param refreshToken
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {BizLevelException.class, SystemLevelException.class})
    public ConcurrentHashMap<String, String> refreshToken(String accessToken, String refreshToken) throws BizLevelException, JsonProcessingException {
        if (StringUtils.isEmptyOrWhitespace(accessToken)) {
            throw new BizLevelException(ConsCommon.WARN_MSG_004);
        }
        if (StringUtils.isEmptyOrWhitespace(refreshToken)) {
            throw new BizLevelException(ConsCommon.WARN_MSG_005);
        }
        String userId = xtJwtMapper.selectByRefreshToken(accessToken, refreshToken);
        if (null == userId) {
            throw new BizLevelException(ConsCommon.WARN_MSG_006);
        } else {
            return saveJwt(userId);
        }

    }

    /**
     * 获取该token及有效性
     *
     * @param accessToken
     * @return key:[USER_ID, ACCESS_TOKEN, REFRESH_TOKEN,
     * ACCESS_TOKEN_TIMEOUT, REFRESH_TOKEN_TIMEOUT, NEED_REFRESH]
     */
    @Override
    public ConcurrentHashMap<String, String> selectByAccessToken(String accessToken) {
        return xtJwtMapper.selectByAccessToken(accessToken);
    }

    /**
     * 调用关键业务时token校验
     *
     * @param accessToken
     * @param refreshToken
     * @return 校验通过必返字段：userId，accessToken，refreshToken
     * 可选insertCount，token更新时返回
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ConcurrentHashMap<String, String> checkToken(String accessToken, String refreshToken) throws JsonProcessingException, BizLevelException {
        Map<String, String> tokenMap = selectByAccessToken(accessToken);
        if (null != tokenMap) {
            ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<String, String>();
            if ("N".equals(tokenMap.get("ACCESS_TOKEN_TIMEOUT"))) {
                //accessToken未失效

                if ("Y".equals(tokenMap.get("NEED_REFRESH"))) {
                    //达到需要刷新时间点
                    if (!StringUtils.isEmptyOrWhitespace(refreshToken)) {
                        //主动刷新token
                        return refreshToken(accessToken, refreshToken);
                    }
                    //无refreshToken，无法主动刷新token
                } else {
                    resultMap.put("userId", tokenMap.get("USER_ID"));
                    resultMap.put("accessToken", accessToken);
                    resultMap.put("refreshToken", tokenMap.get("REFRESH_TOKEN"));
                    return resultMap;
                }

            } else {
                //accessToken失效
                if ("N".equals(tokenMap.get("REFRESH_TOKEN_TIMEOUT"))) {
                    //refreshToken未失效
                    if (!StringUtils.isEmptyOrWhitespace(refreshToken)) {
                        //主动刷新token
                        return refreshToken(accessToken, refreshToken);
                    }
                    //无refreshToken，无法主动刷新token
                }

                //refreshToken失效

            }
        }
        return null;
    }


}
