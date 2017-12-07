package com.tanb.commpt.core.service.impl;

import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.mapper.XtUserMapper;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.po.XtUserAccount;
import com.tanb.commpt.core.po.XtUserAddress;
import com.tanb.commpt.core.po.XtUserRole;
import com.tanb.commpt.core.service.IUserService;
import com.tanb.commpt.core.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tanbo on 2017/9/4.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private XtUserMapper xtUserMapper;

    /**
     * 用户名、密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public XtUser selectByUsernameAndPassword(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        XtUser xtUser = xtUserMapper.selectExistsUser(username);
        if (null != xtUser) {
            if (MD5Util.validateStr(password, xtUser.getPassEnc())) {
                return xtUser;
            }
        }
        return null;
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @param userAccount
     * @param userAddress
     * @param userRole
     * @return 用户主键
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public String saveUserInfo(XtUser user, XtUserAccount userAccount, XtUserAddress userAddress, XtUserRole userRole) throws UnsupportedEncodingException, NoSuchAlgorithmException, BizLevelException {
        if (null != user.getPass()) {
            user.setPassEnc(MD5Util.getEncryptedStr(user.getPass()));
            user.setPass("");
        }
        XtUser existsUser = xtUserMapper.selectExistsUser(user.getUserAccount());
        if (null != existsUser) {
            throw new BizLevelException(ConsCommon.WARN_MSG_001);
        }
        existsUser = xtUserMapper.selectExistsUser(user.getCardNumber());
        if (null != existsUser) {
            throw new BizLevelException(ConsCommon.WARN_MSG_002);
        }
        existsUser = xtUserMapper.selectExistsUser(user.getMobile());
        if (null != existsUser) {
            throw new BizLevelException(ConsCommon.WARN_MSG_003);
        }

        int insertCount = xtUserMapper.insert2(user);
        if (insertCount > 0)
            return user.getUserId();
        return null;
    }

    /**
     * 主健查询用户信息
     *
     * @param userId
     * @return
     */
    private XtUser selectByPrimaryKey(String userId) {
        return xtUserMapper.selectByPrimaryKey(userId);
    }
}
