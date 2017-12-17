package com.tanb.commpt.core.controller;

import com.tanb.commpt.core.dao.impl.BaseDao;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.mapper.DmCodeMapper;
import com.tanb.commpt.core.po.DmCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Tanbo on 2017/12/6.
 */
@Controller
@RequestMapping("test")
public class TestController {
    private static final Logger LOGGER = Logger.getLogger(TestController.class);

    @Autowired
    private SystemConfiguration systemConfiguration;

    @Autowired
    BaseDao baseDao;

    @Autowired
    DmCodeMapper dmCodeMapper;

    @ResponseBody
    @RequestMapping("hello")
    public String hello() {
        LOGGER.debug("=====TEST=====");
        System.out.println(systemConfiguration);
        System.out.println(baseDao.getNowLocal());
        return "Hello CommPt!!";
    }

    @RequestMapping("index")
    public String index() {
        List<DmCode> dmCodeList = dmCodeMapper.selectAll();
        return "index";
    }

}
