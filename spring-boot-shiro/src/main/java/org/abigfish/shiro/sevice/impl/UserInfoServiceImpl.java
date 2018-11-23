package org.abigfish.shiro.sevice.impl;


import javax.annotation.Resource;

import org.abigfish.shiro.dao.UserInfoDao;
import org.abigfish.shiro.entity.UserInfo;
import org.abigfish.shiro.sevice.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}