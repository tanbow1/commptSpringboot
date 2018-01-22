package com.tanb.commpt.miniapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.controller.IndexController;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.mapper.XtUserMapper;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.service.IUserService;
import com.tanb.commpt.core.util.CommonUtil;
import com.tanb.commpt.core.util.HttpUtil;
import com.tanb.commpt.miniapp.mapper.BzWxUserMapper;
import com.tanb.commpt.miniapp.po.BzWxUser;
import com.tanb.commpt.miniapp.service.IWxMiniAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2018/1/6.
 */
@Service("wxMiniService")
public class WxMiniAppServiceImpl implements IWxMiniAppService {
    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    SystemConfiguration systemConfiguration;

    @Autowired
    BzWxUserMapper bzWxUserMapper;

    @Autowired
    XtUserMapper xtUserMapper;

    @Autowired
    IUserService userService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public JsonResponse login(JsonRequest jsonRequest) throws BizLevelException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LOGGER.info("========>微信小程序用户登录中");
        JsonResponse jsonResponse = new JsonResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        ConcurrentHashMap reqData = (ConcurrentHashMap) jsonRequest.getReqData().get("data");
        String code = (String) reqData.get("code");
        String wxLoginUrl = systemConfiguration.WX_BASEPATH + "sns/jscode2session?appid=" + systemConfiguration.WX_APPID + "&secret=" + systemConfiguration.WX_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpUtil.doGet(wxLoginUrl);
        Map<String, String> resultMap;
        try {
            resultMap = objectMapper.readValue(result.getBytes(), Map.class);
        } catch (IOException e) {
            jsonResponse.setCode(SysConstant.IO_EXCEPTION_CODE);
            jsonResponse.setMsg(SysConstant.IO_EXCEPTION);
            jsonResponse.setDetailMsg("调用微信小程序登录API时返回参数[" + result + "]解析异常");
            return jsonResponse;
        }
        String errcode = resultMap.get("errcode");
        String errmsg = resultMap.get("errmsg");
        String openid = resultMap.get("openid");
        String session_key = resultMap.get("session_key");
        if (!StringUtils.isEmpty(errcode) || !StringUtils.isEmpty(errmsg)) {
            jsonResponse.setCode(errcode);
            jsonResponse.setMsg(errmsg);
            return jsonResponse;
        }
        if (StringUtils.isEmpty(openid)) {
            jsonResponse.setCode(SysConstant.UNKNOW_CODE);
            jsonResponse.setMsg(SysConstant.UNKNOW_ERROR);
            jsonResponse.setDetailMsg("调用微信小程序登录API时未获取到openid");
            return jsonResponse;
        }
        BzWxUser wxUser = bzWxUserMapper.selectByPrimaryKey(openid);
        if (wxUser == null) {
            //新用户注册
            LOGGER.info("========>微信小程序用户：" + openid + " 未注册");
            XtUser xtUser = new XtUser();
            xtUser.setUserAccount(openid);
            xtUser.setRegistWay("2");
            userService.saveUserInfo(xtUser, null);
            LOGGER.info("========>用户主表已注册");
            wxUser = new BzWxUser();
            wxUser.setOpenId(openid);
            wxUser.setSessionKey(session_key);
            wxUser.setStatus("1");
            wxUser.setUserId(xtUser.getUserId());
            int insertCount = bzWxUserMapper.insert(wxUser);
            if (insertCount < 1) {
                throw new BizLevelException(SysConstant.UNKNOW_CODE, SysConstant.UNKNOW_ERROR + ":注册微信小程序用户时出现未知错误(微信用户表注册异常)");
            }
            LOGGER.info("========>微信小程序用户：" + openid + " 注册成功");
            jsonResponse.setDetailMsg("注册成功");
        } else {
            if (StringUtils.isEmpty(wxUser.getUserId())) {
                LOGGER.info("========>查找到微信小程序用户：" + openid + " ，未绑定本系统(userId不存在)");
            } else {
                XtUser xtUser = xtUserMapper.selectByPrimaryKey(wxUser.getUserId());
                if (null != xtUser) {
                    LOGGER.info("========>查找到微信小程序用户：" + openid + " ，已绑定本系统");
                } else {
                    LOGGER.info("========>查找到微信小程序用户：" + openid + " ，未绑定本系统(userId无效)");
                }
            }
            jsonResponse.setDetailMsg("登录成功");
        }
        return jsonResponse;
    }
}
