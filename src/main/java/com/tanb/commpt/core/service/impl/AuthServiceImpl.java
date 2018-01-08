package com.tanb.commpt.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.exception.SystemLevelException;
import com.tanb.commpt.core.mapper.XtUserJwtMapper;
import com.tanb.commpt.core.po.XtUserJwt;
import com.tanb.commpt.core.service.IAuthService;
import com.tanb.commpt.core.util.JwtUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/9/4.
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

    private static final Logger LOGGER = Logger.getLogger(AuthServiceImpl.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private XtUserJwtMapper xtUserJwtMapper;


    /**
     * 保存用户token：
     * 用户下存在则覆盖
     * jwtUtil创建的accessToken，refreshToken
     * JWT_TTL为accessToken2天有效
     * JWT_REFRESH_TTL为refreshToken1年有效
     * JWT_REFRESH_INTERVAL为token刷新频率每天
     * valid:true有效
     *
     * @param userId
     * @return 返回用户id
     * 记录变更数
     * 新的accessToken，refreshToken
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ConcurrentHashMap<String, String> saveJwt(String userId) throws JsonProcessingException {
        xtUserJwtMapper.deleteByUserId(userId);
        String subject = JwtUtil.generalSubject(userId);
        String accessToken = jwtUtil.createJWT(SysConstant.JWT_ID, subject, SysConstant.JWT_TTL);
        String refreshToken = jwtUtil.createJWT(SysConstant.JWT_ID, subject, SysConstant.JWT_REFRESH_TTL);
        XtUserJwt xtJwt = new XtUserJwt();
        xtJwt.setUserId(userId);
        xtJwt.setAccessToken(accessToken);
        xtJwt.setRefreshToken(refreshToken);
        int insertCount = xtUserJwtMapper.insert2(xtJwt);
        ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<String, String>();
        resultMap.put("insertCount", String.valueOf(insertCount));
        resultMap.put("userId", userId);
        resultMap.put("accessToken", accessToken);
        resultMap.put("refreshToken", refreshToken);
        resultMap.put("valid", "true");
        resultMap.put("errorMsg", "");
        return resultMap;
    }

    /**
     * 刷新token：
     * 调用刷新token时需知道用户下原始accessToken和refreshToken
     * 如果JWT_REFRESH_TTL refreshToken有效则可以刷新token信息
     *
     * @param accessToken
     * @param refreshToken
     * @param isCheckRefreshToken :刷新前是否校验RefreshToken有效性
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ConcurrentHashMap<String, String> refreshToken(String userId, String accessToken, String refreshToken, boolean isCheckRefreshToken) throws BizLevelException, JsonProcessingException {
        if (StringUtils.isEmptyOrWhitespace(accessToken)) {
            LOGGER.info("accessToken为refreshToken方法的必要字段");
            throw new BizLevelException(SysConstant.WARN_CODE_004, SysConstant.WARN_MSG_004);
        }
        if (StringUtils.isEmptyOrWhitespace(refreshToken)) {
            LOGGER.info("refreshToken为refreshToken方法的必要字段");
            throw new BizLevelException(SysConstant.WARN_CODE_005, SysConstant.WARN_MSG_005);
        }
        if (StringUtils.isEmptyOrWhitespace(userId)) {
            LOGGER.info("必要字段userId未获取到，通过accessToken查询。");
            userId = xtUserJwtMapper.selectByAccessToken(accessToken);
            if (null == userId) {
                LOGGER.info("无法通过accessToken获取userId");
                throw new BizLevelException(SysConstant.WARN_CODE_006, "更新失败(" + SysConstant.WARN_MSG_006 + ")");
            }
        }
        if (isCheckRefreshToken) {
            String userIdRet = xtUserJwtMapper.selectByUserAndToken(userId, accessToken, refreshToken);
            if (null == userIdRet) {
                LOGGER.info("记录不存在或refreshToken已失效");
                throw new BizLevelException(SysConstant.WARN_CODE_021 + "/" + SysConstant.WARN_CODE_006, "更新失败(" + SysConstant.WARN_MSG_021 + "或" + SysConstant.WARN_MSG_006 + ")");
            } else {
                return saveJwt(userId);
            }
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
    public ConcurrentHashMap<String, String> selectTokenInfoByUserAccessToken(String userId, String accessToken) {
        return xtUserJwtMapper.selectTokenInfoByUserAccessToken(userId, accessToken);
    }

    /**
     * 调用业务时token校验
     *
     * @param accessToken：必要参数
     * @param refreshToken:如无需自动刷新，可为null或""
     * @return 校验通过返回字段：valid:true, userId，accessToken，refreshToken
     * 校验不通过：valid:false
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ConcurrentHashMap<String, String> checkToken(String userId, String accessToken, String refreshToken) throws JsonProcessingException, BizLevelException {
        ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<String, String>();
        refreshToken = StringUtils.isEmptyOrWhitespace(refreshToken) ? "" : refreshToken;
        resultMap.put("insertCount", "0");
        resultMap.put("valid", "true");
        resultMap.put("errorMsg", "");
        resultMap.put("userId", userId);
        resultMap.put("accessToken", accessToken);
        resultMap.put("refreshToken", refreshToken);
        if (StringUtils.isEmptyOrWhitespace(userId)) {
            LOGGER.info("必要字段userId未获取到，通过accessToken查询。");
            userId = xtUserJwtMapper.selectByAccessToken(accessToken);
            if (null == userId) {
                LOGGER.info("无法通过accessToken获取userId");
                throw new BizLevelException(SysConstant.WARN_CODE_006,"更新失败(" + SysConstant.WARN_MSG_006 + ")");
            }
        }
        ConcurrentHashMap<String, String> tokenMap = selectTokenInfoByUserAccessToken(userId, accessToken);
        if (null != tokenMap) {
            if ("N".equals(tokenMap.get("ACCESS_TOKEN_TIMEOUT"))) {
                //accessToken未失效

                if ("Y".equals(tokenMap.get("NEED_REFRESH"))) {
                    //达到需要刷新时间点
                    if (!StringUtils.isEmptyOrWhitespace(refreshToken)) {
                        //主动刷新token
                        return refreshToken(userId, accessToken, refreshToken, false);
                    }
                    //无refreshToken，无法主动刷新token

                } else {
                    //token完全有效，无需刷新

                }
            } else {
                //accessToken失效

                if ("N".equals(tokenMap.get("REFRESH_TOKEN_TIMEOUT"))) {
                    //refreshToken未失效
                    if (!StringUtils.isEmptyOrWhitespace(refreshToken)) {
                        //主动刷新token
                        return refreshToken(userId, accessToken, refreshToken, false);
                    } else {
                        //无refreshToken，无法主动刷新token
                        resultMap.put("valid", "false");
                        resultMap.put("errorMsg", SysConstant.WARN_MSG_006);
                    }

                } else {
                    //refreshToken也失效
                    resultMap.put("valid", "false");
                    resultMap.put("errorMsg", SysConstant.WARN_MSG_006);
                }
            }
        } else {
            resultMap.put("valid", "false");
            resultMap.put("errorMsg", SysConstant.WARN_MSG_021);
        }
        return resultMap;
    }


}
