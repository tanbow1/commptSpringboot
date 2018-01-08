package com.tanb.commpt.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.global.SystemContext;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 特定异常处理
 * <p>
 * 将作用在所有注解了@RequestMapping的Controller中抛出异常
 */
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
    public Object runtimeExceptionHandler(RuntimeException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(SysConstant.RUNTIME_EXCEPTION_CODE);
        jsonResponse.setMsg(SysConstant.RUNTIME_EXCEPTION);
        jsonResponse.setDetailMsg("运行异常:[" + ex + "]");
        return returnError(request, response);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public Object ioExceptionHandler(RuntimeException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(SysConstant.IO_EXCEPTION_CODE);
        jsonResponse.setMsg(SysConstant.IO_EXCEPTION);
        jsonResponse.setDetailMsg("输出异常:[" + ex + "]");
        return returnError(request, response);
    }

    //上传文件过大
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Object maxUploadSizeExceededExceptionHandler(RuntimeException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        jsonResponse.setCode(SysConstant.MAXUPLOADSIZE_EXCEPTION_CODE);
        jsonResponse.setMsg(SysConstant.MAXUPLOADSIZE_EXCEPTION);
        jsonResponse.setDetailMsg("文件过大(文件累计最大支持" + Integer.parseInt(config.FILE_MAXUPLOADSIZE) / (1024 * 1024) + "M)[" + ex + "]");
        return returnError(request, response);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object exception(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        if (ex instanceof NoHandlerFoundException) {
            jsonResponse.setCode(SysConstant.NOHANDLERFOUND_EXCEPTION_CODE);
            jsonResponse.setMsg(SysConstant.NOHANDLERFOUND_EXCEPTION);
            jsonResponse.setDetailMsg("404:[" + ex + "]");
        } else {
            jsonResponse.setCode(SysConstant.SERVER_EXCEPTION_CODE);
            jsonResponse.setMsg(SysConstant.SERVER_EXCEPTION);
            jsonResponse.setDetailMsg("500:[" + ex + "]");
        }
        return returnError(request, response);
    }

    @ExceptionHandler(value = {BizLevelException.class, SystemLevelException.class})
    public Object sysException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        modelAndView = new ModelAndView();
        jsonResponse = new JsonResponse();
        if (ex instanceof BizLevelException) {
            BizLevelException bzEx = (BizLevelException) ex;
            jsonResponse.setCode(bzEx.getCode());
            jsonResponse.setMsg(bzEx.getMessage());
            jsonResponse.setDetailMsg("应用异常:[" + ex + "]");
        }
        if (ex instanceof SystemLevelException) {
            SystemLevelException sysEx = (SystemLevelException) ex;
            jsonResponse.setCode(sysEx.getCode());
            jsonResponse.setMsg(sysEx.getMessage());
            jsonResponse.setDetailMsg("系统异常:[" + ex + "],请联系系统管理员!");
        }
        return returnError(request, response);
    }

    private Object returnError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("-------------------------出现异常------------------------");
        logger.error(jsonResponse.getDetailMsg());
        if (CommonUtil.isAjaxRequest(request)) {
            try {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(objectMapper.writeValueAsString(jsonResponse));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                logger.error("异常输出出错", e);
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("jsonResponse", jsonResponse);
            modelAndView.setViewName("common/error");
            return modelAndView;
        }
        return null;
    }
}
