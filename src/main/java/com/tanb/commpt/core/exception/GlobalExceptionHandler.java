package com.tanb.commpt.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常特殊处理，先于SimpleMappingExceptionResolver
 */
//将作用在所有注解了@RequestMapping的控制器的方法上
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);
    private JsonResponse jsonResponse;

    private ModelAndView modelAndView;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    SystemConfiguration config;


    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeExceptionHandler(RuntimeException ex, HttpServletRequest request, HttpServletResponse response) {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(ConsCommon.RUNTIME_EXCEPTION_CODE);
        jsonResponse.setMsg(ConsCommon.RUNTIME_EXCEPTION);
        jsonResponse.setDetailMsg(ex.getMessage());
        if (ajaxReturn(request, response)) return null;
        modelAndView.addObject("jsonResponse", jsonResponse);
        modelAndView.setViewName("common/error");
        logger.error(jsonResponse.getMsg(), ex);
        return modelAndView;
    }

    //上传文件过大
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadSizeExceededExceptionHandler(RuntimeException ex, HttpServletRequest request, HttpServletResponse response) {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(ConsCommon.MAXUPLOADSIZE_EXCEPTION_CODE);
        jsonResponse.setMsg(ConsCommon.MAXUPLOADSIZE_EXCEPTION + "(文件累计最大支持" + Integer.parseInt(config.FILE_MAXUPLOADSIZE) / (1024 * 1024) + "M)");
        jsonResponse.setDetailMsg(ex.getMessage());
        if (ajaxReturn(request, response)) return null;
        modelAndView.addObject("jsonResponse", jsonResponse);
        modelAndView.setViewName("common/error");
        logger.error(jsonResponse.getMsg(), ex);
        return modelAndView;
    }

    //404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView NoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response) {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(ConsCommon.NOHANDLERFOUND_EXCEPTION_CODE);
        jsonResponse.setMsg(ConsCommon.NOHANDLERFOUND_EXCEPTION);
        jsonResponse.setDetailMsg(ex.getMessage());
        if (ajaxReturn(request, response)) return null;
        modelAndView.addObject("jsonResponse", jsonResponse);
        modelAndView.setViewName("common/error");
        logger.error(jsonResponse.getMsg(), ex);
        return modelAndView;
    }

    //500
    @ExceptionHandler(Exception.class)
    public ModelAndView Exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(ConsCommon.SERVER_EXCEPTION_CODE);
        jsonResponse.setMsg(ConsCommon.SERVER_EXCEPTION);
        jsonResponse.setDetailMsg(ex.getMessage());
        if (ajaxReturn(request, response)) return null;
        modelAndView.addObject("jsonResponse", jsonResponse);
        modelAndView.setViewName("common/error");
        logger.error(jsonResponse.getMsg(), ex);
        return modelAndView;
    }

    private boolean ajaxReturn(HttpServletRequest request, HttpServletResponse response) {
        if (CommonUtil.isAjaxRequest(request)) {
            try {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(objectMapper.writeValueAsString(jsonResponse));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                logger.error("异常输出出错", e);
            }
            return true;
        }
        return false;
    }
}
