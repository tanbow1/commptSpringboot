package com.tanb.commpt.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.exception.SystemLevelException;
import com.tanb.commpt.core.global.SpringContext;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.service.IAuthService;
import com.tanb.commpt.core.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/8/22.
 */
@Controller
@RequestMapping("/comm")
public class CommController {
    private static Logger LOGGER = LoggerFactory.getLogger(CommController.class);

    @Autowired
    private SystemConfiguration systemConfiguration;

    @Autowired
    private IAuthService authService;

    /**
     * 统一页面跳转
     *
     * @param page
     * @return
     */
    @RequestMapping("/toPage/{page}")
    public ModelAndView toPage(@PathVariable String page) {
        return new ModelAndView(page);
    }

    //error page
    @RequestMapping("/error")
    public ModelAndView error() throws BizLevelException {
        return new ModelAndView("common/error");
    }

    //刷新token
    @ResponseBody
    @RequestMapping("/refreshToken")
    public JsonResponse refreshToken(@ModelAttribute JsonRequest jsonRequest, HttpServletRequest httpServletRequest) throws JsonProcessingException, BizLevelException, SystemLevelException {
        JsonResponse jsonResponse = new JsonResponse();
        String accessToken = jsonRequest.getAccessToken();
        if (StringUtils.isEmptyOrWhitespace(accessToken)) {
            accessToken = httpServletRequest.getParameter("accessToken");
        }
        String refreshToken = jsonRequest.getRefreshToken();
        if (StringUtils.isEmptyOrWhitespace(refreshToken)) {
            refreshToken = httpServletRequest.getParameter("refreshToken");
        }
        String userId = String.valueOf(jsonRequest.getReqData().get("userId"));
        if (StringUtils.isEmptyOrWhitespace(userId)) {
            refreshToken = httpServletRequest.getParameter("userId");
        }
        Map<String, String> resultMap = authService.refreshToken(userId, accessToken, refreshToken, true);
        if ("0".equals(resultMap.get("insertCount"))) {
            throw new SystemLevelException(ConsCommon.UNKNOW_ERROR + ":刷新token失败");
        }
        jsonResponse.getData().put("resultData", resultMap);
        return jsonResponse;
    }

    //校验token
    @ResponseBody
    @RequestMapping("/checkToken")
    public JsonResponse checkToken(@ModelAttribute JsonRequest jsonRequest) throws JsonProcessingException, BizLevelException {
        JsonResponse jsonResponse = new JsonResponse();
        String accessToken = jsonRequest.getAccessToken();
        String refreshToken = jsonRequest.getRefreshToken();
        String userId = String.valueOf(jsonRequest.getReqData().get("userId"));
        Map<String, String> resultMap = authService.checkToken(userId, accessToken, refreshToken);
        jsonResponse.getData().put("resultData", resultMap);
        return jsonResponse;
    }

    /**
     * 统一ajax调用，ajax传入参数需写全
     * <p>
     * 支持的method参数类型：int boolean string ，可以补充
     *
     * @param jsonRequest
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws BizLevelException
     */
    @ResponseBody
    @RequestMapping("/getJsonData")
    public JsonResponse getJsonData(@ModelAttribute JsonRequest jsonRequest,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws BizLevelException, InvocationTargetException {
        JsonResponse jsonResponse = new JsonResponse();
        jsonRequest.getReqData().put("request", httpServletRequest);
        jsonRequest.getReqData().put("response", httpServletResponse);

        String serviceName = jsonRequest.getServiceName();
        String methodName = jsonRequest.getMethodName();
        List<ConcurrentHashMap<String, Object>> methodParams = jsonRequest.getMethodParams();

        try {
            Object serviceBean = SpringContext.getBean(serviceName);
            Class[] paramClasses = new Class[]{};
            Object[] paramValues = new Object[]{};
            if (null != methodParams && methodParams.size() > 0) {
                paramClasses = new Class[methodParams.size()];
                paramValues = new Object[methodParams.size()];
                Map<String, Object> tempParamMap;
                int index;
                String type;
                for (int i = 0; i < methodParams.size(); i++) {
                    tempParamMap = methodParams.get(i);
                    index = Integer.parseInt(String.valueOf(tempParamMap.get("index"))) - 1;
                    type = String.valueOf(tempParamMap.get("type")).toLowerCase();
                    paramClasses[index]
                            = CommonUtil.getClassName(type);
                    if ("int".equals(type)) {
                        paramValues[index] = Integer.parseInt(String.valueOf(tempParamMap.get("value")));
                    } else if ("string".equals(type)) {
                        paramValues[index] = String.valueOf(tempParamMap.get("value"));
                    } else if ("boolean".equals(type)) {
                        paramValues[index] = Boolean.valueOf(String.valueOf(tempParamMap.get("value")));
                    } else {
                        throw new BizLevelException(ConsCommon.WARN_MSG_015);
                    }
                }
            }

            Method method = serviceBean.getClass().getMethod(methodName, paramClasses);
            jsonResponse = (JsonResponse) method.invoke(serviceBean, paramValues);

        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_008 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_008);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_008);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_009 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_009);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_009);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_012 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_012);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_012);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_013 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_013);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_013);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_014 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_014);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_014);
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_010 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_010);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_010);
        }

        return jsonResponse;
    }


    /**
     * 统一调用 ajax／url get方式，如果是url必须带参数
     * <p>
     * 请求的service默认入参：JsonRequest，出参：JsonResponse
     *
     * @param jsonRequest
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws InvocationTargetException 该异常需要抛出给异常类统一处理，否则无法明确service中抛出的具体错误原因
     */
    @ResponseBody
    @RequestMapping("/getJsonData2")
    public JsonResponse getJsonData2(@ModelAttribute JsonRequest jsonRequest,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws InvocationTargetException, BizLevelException {
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

        if (null == serviceName || null == methodName)
            throw new BizLevelException(ConsCommon.WARN_MSG_014);

        try {
            Object serviceBean = SpringContext.getBean(serviceName);
            Method method = serviceBean.getClass().getMethod(methodName, JsonRequest.class);
            jsonResponse = (JsonResponse) method.invoke(serviceBean, jsonRequest);
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_008 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_008);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_008);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_009 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_009);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_009);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_012 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_012);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_012);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_014 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_014);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_014);
        } catch (NoSuchBeanDefinitionException e) {
            LOGGER.error(e.getMessage() + "[" + ConsCommon.WARN_MSG_010 + "]");
            jsonResponse.setCode(ConsCommon.WARN_CODE_010);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_010);
        }

        return jsonResponse;
    }


    /**
     * 统一文件上传
     * <p>
     * 请求的service默认入参：（JsonRequest，MultipartFile[]）出参：JsonResponse
     *
     * @param files
     * @param jsonRequest
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     * @throws SystemLevelException
     * @throws BizLevelException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFiles", method = {RequestMethod.POST})
    public JsonResponse uploadFiles(@RequestParam(value = "uploadFile", required = false) MultipartFile[] files,
                                    @ModelAttribute JsonRequest jsonRequest,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws IOException, SystemLevelException, BizLevelException, InvocationTargetException {
        JsonResponse jsonResponse = new JsonResponse();
        jsonRequest.getReqData().put("request", httpServletRequest);
        jsonRequest.getReqData().put("response", httpServletResponse);

        int fileMaxlength = Integer.parseInt(systemConfiguration.FILE_MAXLENGTH);
        if (files.length > fileMaxlength) {
            throw new BizLevelException("文件数过多，最多不能超过" + fileMaxlength + "个，当前文件数：" + files.length);
        }

        String serviceName = jsonRequest.getServiceName();
        String methodName = jsonRequest.getMethodName();
        Object serviceBean = SpringContext.getBean(serviceName);
        try {
            Method method = serviceBean.getClass().getMethod(methodName, JsonRequest.class, MultipartFile[].class);
            jsonResponse = (JsonResponse) method.invoke(serviceBean, jsonRequest, files);
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage());
            jsonResponse.setCode(ConsCommon.WARN_CODE_008);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_008);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage());
            jsonResponse.setCode(ConsCommon.WARN_CODE_009);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_009);
        } catch (ClassCastException e) {
            LOGGER.error(e.getMessage());
            jsonResponse.setCode(ConsCommon.WARN_CODE_012);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_012);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            jsonResponse.setCode(ConsCommon.WARN_CODE_014);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_014);
        }

        return jsonResponse;
    }


}

