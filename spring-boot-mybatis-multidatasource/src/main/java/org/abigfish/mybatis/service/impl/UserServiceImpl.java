package org.abigfish.mybatis.service.impl;

import java.util.List;

import org.abigfish.mybatis.dao.UserOneMapper;
import org.abigfish.mybatis.dao.UserTwoMapper;
import org.abigfish.mybatis.entity.UserEntity;
import org.abigfish.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserOneMapper userOneMapper;

	@Autowired
	private UserTwoMapper userTwoMapper;

	@Override
	public PageInfo queryPage(int offset, int limit) {
		// TODO Auto-generated method stub
		PageHelper.offsetPage(offset, limit);
		List<UserEntity> list = userOneMapper.getAll();
		return new PageInfo(list);
	}

	@Transactional
	@Override
	public void save(UserEntity u) {
		// TODO Auto-generated method stub
		try {
			// 同时往不同的数据库插入数据
			userOneMapper.insert(u);
			userTwoMapper.insert(u);
			//int a = 10 / 0;
		} catch (Exception e) {
			throw new RuntimeException("添加数据失败");
		}

	}

}
