package com.tanb.commpt.miniapp.controller;

import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tanbo on 2017/12/17.
 */
@RestController
@RequestMapping("miniApp")
public class MiniAppController {

    @RequestMapping("wx")
    public JsonResponse wxMiniAppCommRequest(@ModelAttribute JsonRequest jsonRequest,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) {
        JsonResponse jsonResponse = new JsonResponse();

        return jsonResponse;
    }


}
