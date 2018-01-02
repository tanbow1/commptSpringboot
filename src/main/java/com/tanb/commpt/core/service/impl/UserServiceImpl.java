package com.tanb.commpt.core.service.impl;

import com.tanb.commpt.core.constant.SysConstant;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.mapper.XtUserMapper;
import com.tanb.commpt.core.mapper.XtUserRoleMapper;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.po.XtUserRole;
import com.tanb.commpt.core.service.IUserService;
import com.tanb.commpt.core.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tanbo on 2017/9/4.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private XtUserMapper xtUserMapper;

    @Autowired
    private XtUserRoleMapper xtUserRoleMapper;

    /**
     * 用户名、密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public XtUser selectByUsernameAndPassword(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        XtUser xtUser = xtUserMapper.findByUsername(username);
        if (null != xtUser) {
            if (MD5Util.validateStr(password, xtUser.getPassword())) {
                return xtUser;
            }
        }
        return null;
    }

    /**
     * 注册用户信息
     *
     * @param user
     * @param userRole
     * @return 用户主键
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public String saveUserInfo(XtUser user, XtUserRole userRole) throws UnsupportedEncodingException, NoSuchAlgorithmException, BizLevelException {
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(MD5Util.encryptWithSalt(user.getPassword()));
        } else {
            //默认密码123456
            user.setPassword(MD5Util.encryptWithSalt("123456"));
        }
        XtUser existsUser = xtUserMapper.findByUsername(user.getUserAccount());
        if (null != existsUser) {
            throw new BizLevelException(SysConstant.WARN_MSG_001);
        }
        existsUser = xtUserMapper.findByUsername(user.getCardNumber());
        if (null != existsUser) {
            throw new BizLevelException(SysConstant.WARN_MSG_002);
        }
        existsUser = xtUserMapper.findByUsername(user.getMobile());
        if (null != existsUser) {
            throw new BizLevelException(SysConstant.WARN_MSG_003);
        }

        int insertCount = xtUserMapper.insert2(user);
        if (insertCount > 0) {
            String userId = user.getUserId();
            if (null == userRole) {
                userRole = new XtUserRole();
                userRole.setUserId(userId);
            }
            if (StringUtils.isEmpty(userRole.getRoleId())) {
                //默认普通用户=2
                userRole.setRoleId("2");
            }
            if (StringUtils.isEmpty(userRole.getStatus())) {
                userRole.setStatus("1");
            }
            xtUserRoleMapper.insert(userRole);
            return userId;
        } else
            throw new BizLevelException("注册失败");
    }

    /**
     * 主健查询用户信息
     *
     * @param userId
     * @return
     */
    private XtUser selectByPrimaryKey(String userId) {
        XtUser xtUser = xtUserMapper.selectByPrimaryKey(userId);
        if (null != xtUser) {
            xtUser.setPassword("");
        }
        return xtUser;
    }
}
