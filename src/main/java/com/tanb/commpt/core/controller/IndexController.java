package com.tanb.commpt.core.controller;

import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.exception.SystemLevelException;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.service.IAuthService;
import com.tanb.commpt.core.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Tanbo on 2017/12/8.
 */
@Controller
public class IndexController {
    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IUserService userService;

    @Autowired
    IAuthService authService;

    @RequestMapping(value = {"", "/", "login"})
    public ModelAndView error() throws BizLevelException {
        return new ModelAndView("login");
    }

    //登录
    @ResponseBody
    @RequestMapping("doLogin")
    public JsonResponse login(@ModelAttribute JsonRequest jsonRequest) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();

        XtUser user = userService.selectByUsernameAndPassword(String.valueOf(jsonRequest.getReqData().get("username")),
                String.valueOf(jsonRequest.getReqData().get("password")));

        if (null != user) {
            Map<String, String> resultMap = authService.saveJwt(user.getUserId());
            if ("0".equals(resultMap.get("insertCount"))) {
                throw new SystemLevelException(ConsCommon.UNKNOW_ERROR + ":插入token失败");
            }

            jsonResponse.getData().put(ConsCommon.ACCESS_TOKEN, resultMap.get("accessToken"));
            jsonResponse.getData().put(ConsCommon.REFRESH_TOKEN, resultMap.get("refreshToken"));
        } else {
            jsonResponse.setCode(ConsCommon.FAILED_CODE);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_007);
        }

        return jsonResponse;
    }

}
