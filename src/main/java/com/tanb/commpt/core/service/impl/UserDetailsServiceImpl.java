package com.tanb.commpt.core.service.impl;

import com.tanb.commpt.core.mapper.XtUserMapper;
import com.tanb.commpt.core.po.XtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanbo on 2017/12/26.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    XtUserMapper xtUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        XtUser user = xtUserMapper.selectByPrimaryKey(userId);

        if(user != null){
            List<GrantedAuthority> authorities = new ArrayList<>();
        }
        return null;
    }
}
