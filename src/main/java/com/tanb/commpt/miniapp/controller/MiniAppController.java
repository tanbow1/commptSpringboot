package com.tanb.commpt.miniapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.global.SpringContext;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/12/17.
 * <p>
 * 小程序接口调用主方法，尽量不要抛出异常并将错误信息统一返回给调用端
 */
@CrossOrigin
@RestController
@RequestMapping("miniApp")
public class MiniAppController {
    private static final Logger LOGGER = Logger.getLogger(MiniAppController.class);

    /**
     * 微信通用请求
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("wx")
    public JsonResponse wxMiniAppCommRequest(HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) {
        JsonResponse jsonResponse = new JsonResponse();
        JsonRequest jsonRequest = new JsonRequest();
        jsonRequest.getReqData().put("request", httpServletRequest);
        jsonRequest.getReqData().put("response", httpServletResponse);
        jsonRequest.setAccessToken(httpServletRequest.getParameter("accessToken"));
        jsonRequest.setAccessToken(httpServletRequest.getParameter("refreshToken"));
        String serviceName = httpServletRequest.getParameter("serviceName");
        jsonRequest.setServiceName(serviceName);
        String methodName = httpServletRequest.getParameter("methodName");
        jsonRequest.setMethodName(methodName);
        String reqData = httpServletRequest.getParameter("reqData");
        if (!StringUtils.isEmpty(reqData)) {
            ObjectMapper objectMapper = new ObjectMapper();
            ConcurrentHashMap reqData1;
            try {
                reqData1 = objectMapper.readValue(reqData.getBytes(), ConcurrentHashMap.class);
            } catch (IOException e) {
                LOGGER.error(e.getMessage() + "[" + SysConstant.IO_EXCEPTION + "]");
                jsonResponse.setCode(SysConstant.IO_EXCEPTION_CODE);
                jsonResponse.setMsg(SysConstant.IO_EXCEPTION);
                jsonResponse.setDetailMsg("请求参数[" + reqData + "]解析异常");
                return jsonResponse;
            }
            jsonRequest.getReqData().put("data", reqData1);
        }

        if (StringUtils.isEmpty(serviceName) || StringUtils.isEmpty(methodName)) {
            LOGGER.error("请求参数serviceName或methodName不存在");
            jsonResponse.setCode(SysConstant.WARN_CODE_014);
            jsonResponse.setMsg(SysConstant.WARN_MSG_014);
            jsonResponse.setDetailMsg("请求参数serviceName或methodName不存在");
            return jsonResponse;
        }

        try {
            LOGGER.info("========开始调用服务：" + serviceName + "，方法：" + methodName + "========");
            Object serviceBean = SpringContext.getBean(serviceName);
            Method method = serviceBean.getClass().getMethod(methodName, JsonRequest.class);
            jsonResponse = (JsonResponse) method.invoke(serviceBean, jsonRequest);
            LOGGER.info("========结束调用服务：" + serviceName + "，方法：" + methodName + "========");
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_008 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_008);
            jsonResponse.setMsg(SysConstant.WARN_MSG_008);
            jsonResponse.setDetailMsg("方法[" + methodName + "]不存在");
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_009 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_009);
            jsonResponse.setMsg(SysConstant.WARN_MSG_009);
            jsonResponse.setDetailMsg("方法[" + methodName + "]调用权限不足");
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_012 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_012);
            jsonResponse.setMsg(SysConstant.WARN_MSG_012);
            jsonResponse.setDetailMsg("方法[" + methodName + "]调用时返回类型有误");
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_014 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_014);
            jsonResponse.setMsg(SysConstant.WARN_MSG_014);
            jsonResponse.setDetailMsg("方法[" + methodName + "]调用时传入参数有误");
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_010 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_010);
            jsonResponse.setMsg(SysConstant.WARN_MSG_010);
            jsonResponse.setDetailMsg("方法[" + methodName + "]不存在");
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_011 + "]，目标位置：[" + e.getTargetException() + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_011);
            jsonResponse.setMsg(SysConstant.WARN_MSG_011);
            jsonResponse.setDetailMsg("方法[" + methodName + "]调用时内部发生异常");
        }

        return jsonResponse;
    }

}
