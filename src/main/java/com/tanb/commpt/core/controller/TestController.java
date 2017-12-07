package com.tanb.commpt.core.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Tanbo on 2017/12/6.
 */
@Controller
@RequestMapping("test")
public class TestController {

    private static final Logger LOGGER = Logger.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        LOGGER.debug("=====TEST=====");
        return "Hello CommPt!!";
    }

    @RequestMapping("index")
    public String index() {
        LOGGER.debug("=====INDEX=====");
        return "index";
    }

}
