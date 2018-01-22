package com.tanb.commpt.test.controller;

import com.tanb.commpt.core.dao.impl.BaseDao;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.global.SystemConfiguration;
import com.tanb.commpt.core.mapper.DmCodeMapper;
import com.tanb.commpt.core.po.DmCode;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.service.IAuthService;
import com.tanb.commpt.core.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Autowired
    IAuthService authService;

    @Autowired
    IUserService userService;

    @Resource
    @Qualifier("jdbcTemplate2")
    JdbcTemplate jdbcTemplate2;

    @ResponseBody
    @RequestMapping("hello")
    public String hello() throws BizLevelException {
        LOGGER.debug("=====TEST=====");
        System.out.println(systemConfiguration);
        System.out.println(baseDao.getNowLocal());
        System.out.println(jdbcTemplate2.queryForObject("select 111 from dual",String.class));

        baseDao.transactionTest();

        return "Hello CommPt!!";
    }

    @RequestMapping("index")
    public String index() throws Exception {
        List<DmCode> dmCodeList = dmCodeMapper.selectAll();
        //  authService.saveJwt("1");
        //  ConcurrentHashMap<String, String> resultMap =  authService.checkToken("","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJDT01NT05QVF9fSldUIiwiaWF0IjoxNTE0MDA1MTA5LCJzdWIiOiJcIjFcIiIsImV4cCI6MTUxNDE3NzkwOX0.69qChBhaj28WJuDeFzVo5PSSEJMme1eErQ2YyaY8MV4",null);
        return "index";
    }

    @ResponseBody
    @RequestMapping("saveUser")
    public String saveUser() throws Exception {
        XtUser user = new XtUser();
        user.setUserAccount("zhull");
        user.setUserName("明猪");
        user.setAddress("南京");
        user.setBirthday("1990-12-01");
        user.setCardId("100001");
        user.setCardType("身份证");
        user.setCardNumber("320123000000001");
        user.setMobile("13814024692");
        user.setAvatar("http://www.baidu.com");
        user.setNationality("中国");
        user.setNationalityId("156");
        user.setRealName("朱玲玲");
        user.setSex("0");
        user.setRegistWay("9");
//        XtUserRole role  = new XtUserRole();

        String useId = userService.saveUserInfo(user, null);
        return useId;
    }


}
