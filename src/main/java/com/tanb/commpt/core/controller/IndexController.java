package com.tanb.commpt.core.controller;

import com.tanb.commpt.core.exception.BizLevelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Tanbo on 2017/12/8.
 */
@Controller
public class IndexController {
    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"", "/", "login"})
    public ModelAndView error() throws BizLevelException {
        return new ModelAndView("login");
    }

}
