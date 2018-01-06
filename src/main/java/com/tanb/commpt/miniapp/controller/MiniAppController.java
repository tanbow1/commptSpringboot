package com.tanb.commpt.miniapp.controller;

import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.global.SpringContext;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Tanbo on 2017/12/17.
 */
@CrossOrigin
@RestController
@RequestMapping("miniApp")
public class MiniAppController {
    private static final Logger LOGGER = Logger.getLogger(MiniAppController.class);

    @RequestMapping("wx")
    public JsonResponse wxMiniAppCommRequest(@ModelAttribute JsonRequest jsonRequest,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) throws InvocationTargetException {
        JsonResponse jsonResponse = new JsonResponse();
        jsonRequest.getReqData().put("request", httpServletRequest);
        jsonRequest.getReqData().put("response", httpServletResponse);

        String serviceName = jsonRequest.getServiceName();
        String methodName = jsonRequest.getMethodName();

        //主要参数没取到默认从url上取
        if (null == serviceName)
            serviceName = httpServletRequest.getParameter("serviceName");
        if (null == methodName)
            methodName = httpServletRequest.getParameter("methodName");

        if (null == serviceName || null == methodName) {
            LOGGER.error(SysConstant.WARN_MSG_008);
            jsonResponse.setCode(SysConstant.WARN_CODE_008);
            jsonResponse.setMsg(SysConstant.WARN_MSG_008);
            return jsonResponse;
        }

        try {
            Object serviceBean = SpringContext.getBean(serviceName);
            Method method = serviceBean.getClass().getMethod(methodName, JsonRequest.class);
            jsonResponse = (JsonResponse) method.invoke(serviceBean, jsonRequest);
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_008 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_008);
            jsonResponse.setMsg(SysConstant.WARN_MSG_008);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_009 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_009);
            jsonResponse.setMsg(SysConstant.WARN_MSG_009);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_012 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_012);
            jsonResponse.setMsg(SysConstant.WARN_MSG_012);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_014 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_014);
            jsonResponse.setMsg(SysConstant.WARN_MSG_014);
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.error(e.getMessage() + "[" + SysConstant.WARN_MSG_010 + "]");
            jsonResponse.setCode(SysConstant.WARN_CODE_010);
            jsonResponse.setMsg(SysConstant.WARN_MSG_010);
        }

        return jsonResponse;
    }


}
