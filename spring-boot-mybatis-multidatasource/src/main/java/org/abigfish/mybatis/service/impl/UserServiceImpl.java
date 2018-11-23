package org.abigfish.mybatis.service.impl;

import java.util.List;

import org.abigfish.mybatis.dao.UserOneMapper;
import org.abigfish.mybatis.entity.UserEntity;
import org.abigfish.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserOneMapper userMapper;

	@Override
	public PageInfo queryPage(int offset, int limit) {
		// TODO Auto-generated method stub
		PageHelper.offsetPage(offset,limit);
        List<UserEntity> list = userMapper.getAll();
        return new PageInfo(list);
	}

}
