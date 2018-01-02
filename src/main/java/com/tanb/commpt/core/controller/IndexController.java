package com.tanb.commpt.core.controller;

import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.exception.SystemLevelException;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.service.IAuthService;
import com.tanb.commpt.core.service.IDmService;
import com.tanb.commpt.core.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @Autowired
    private IDmService dmService;

    @RequestMapping("loginPage")
    public ModelAndView toLogin() throws BizLevelException {
        return new ModelAndView("login");
    }

    @RequestMapping(value = {"/", "index"})
    public ModelAndView toIndex() throws BizLevelException {
        return new ModelAndView("index");
    }

    @RequestMapping(value = {"home"})
    public ModelAndView toHome() throws BizLevelException {
        return new ModelAndView("home");
    }

    /**
     * 默认登录方式
     * 账户重新登录后token也更新
     */
    @ResponseBody
    @RequestMapping("doLogin")
    public JsonResponse login(@ModelAttribute JsonRequest jsonRequest) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();

        XtUser user = userService.selectByUsernameAndPassword(String.valueOf(jsonRequest.getReqData().get("username")),
                String.valueOf(jsonRequest.getReqData().get("password")));

        if (null != user) {
            Map<String, String> resultMap = authService.saveJwt(user.getUserId());
            if ("0".equals(resultMap.get("insertCount"))) {
                throw new SystemLevelException(SysConstant.UNKNOW_ERROR + ":插入token失败");
            }

            jsonResponse.getData().put(SysConstant.ACCESS_TOKEN, resultMap.get("accessToken"));
            jsonResponse.getData().put(SysConstant.REFRESH_TOKEN, resultMap.get("refreshToken"));
        } else {
            jsonResponse.setCode(SysConstant.FAILED_CODE);
            jsonResponse.setMsg(SysConstant.WARN_MSG_007);
        }

        return jsonResponse;
    }

    /**
     * token登录
     *
     * @param jsonRequest
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("loginToken")
    public JsonResponse loginToken(@ModelAttribute JsonRequest jsonRequest) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        String accessToken = jsonRequest.getAccessToken();
        String refreshToken = jsonRequest.getRefreshToken();
        String userId = String.valueOf(jsonRequest.getReqData().get("username"));
        ConcurrentHashMap retMap = authService.checkToken(userId, accessToken, refreshToken);
        if (retMap != null) {
            if ("false".equals(retMap.get("valid"))) {
                //token校验失败
                jsonResponse.setCode(SysConstant.FAILED_CODE);
                jsonResponse.setMsg(retMap.get("errorMsg").toString());
            } else {
                //token校验成功
                jsonResponse.getData().put(SysConstant.ACCESS_TOKEN, retMap.get("accessToken"));
                jsonResponse.getData().put(SysConstant.REFRESH_TOKEN, retMap.get("refreshToken"));
            }
        } else {
            jsonResponse.setCode(SysConstant.UNKNOW_CODE);
            jsonResponse.setMsg(SysConstant.UNKNOW_ERROR);
        }
        return jsonResponse;
    }


    //根据父节点获取主菜单
    @ResponseBody
    @RequestMapping("/getMaintree")
    public List<ConcurrentHashMap<String, Object>> getMaintree() {
        return dmService.getMenuTree("0");
    }
}
