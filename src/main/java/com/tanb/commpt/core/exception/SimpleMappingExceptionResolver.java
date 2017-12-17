package com.tanb.commpt.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局异常通用
 *
 * @author Tanbo
 */
//@Component
public class SimpleMappingExceptionResolver implements
        HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SimpleMappingExceptionResolver.class);

    @Autowired
    SystemConfiguration config;

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object object, Exception ex) {
        JsonResponse jsonResponse = new JsonResponse();
        LOGGER.error(ex.getMessage());
        // 判断是否ajax请求
        if (!CommonUtil.isAjaxRequest(request)) {
            // 非ajax请求
            setErrorJsonResponse(ex, jsonResponse);
            Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
            resultMap.put("jsonResponse", jsonResponse);
            return new ModelAndView("common/error", resultMap);
        } else {
            // ajax请求，JSON格式返回
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                setErrorJsonResponse(ex, jsonResponse);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(objectMapper.writeValueAsString(jsonResponse));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void setErrorJsonResponse(Exception ex, JsonResponse jsonResponse) {
        jsonResponse.setCode(ConsCommon.FAILED_CODE);
        if (ex instanceof BizLevelException) {
            jsonResponse.setMsg("出现错误: " + ex.getMessage());
        } else if (ex instanceof SystemLevelException) {
            jsonResponse.setMsg("系统出错: " + ex.getMessage() + ",请联系系统管理员!");
        } else if (ex instanceof InvocationTargetException) {
            Throwable throwable = ((InvocationTargetException) ex).getTargetException();
            jsonResponse.setMsg("出现错误: " + throwable.getMessage());
            jsonResponse.setDetailMsg(ex.getCause().toString());
            //方法调用出错范围太大 ，需获取该异常内部TargetException才能明确哪个异常
        } else if (ex instanceof MaxUploadSizeExceededException) {
            jsonResponse.setMsg("出现错误,文件过大(最大支持" + Integer.parseInt(config.FILE_MAXUPLOADSIZE) / (1024 * 1024) + "M): " + ex.getMessage());
            // 该异常 由于在Controller之前触发，转至GlobalExceptionHandler.maxUploadSizeExceededExceptionHandler处理
        } else {
            jsonResponse.setCode(ConsCommon.UNKNOW_CODE);
            jsonResponse.setMsg(ConsCommon.UNKNOW_ERROR + "：" + ex.getMessage());
        }
    }
}
