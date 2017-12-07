package com.tanb.commpt.core.service;


import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.po.XtUser;
import com.tanb.commpt.core.po.XtUserAccount;
import com.tanb.commpt.core.po.XtUserAddress;
import com.tanb.commpt.core.po.XtUserRole;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tanbo on 2017/9/4.
 */
public interface IUserService {

    XtUser selectByUsernameAndPassword(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    String saveUserInfo(XtUser user, XtUserAccount userAccount, XtUserAddress userAddress, XtUserRole userRole) throws UnsupportedEncodingException, NoSuchAlgorithmException, BizLevelException;

}
