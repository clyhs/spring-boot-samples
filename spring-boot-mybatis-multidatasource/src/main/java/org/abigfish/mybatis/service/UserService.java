package org.abigfish.mybatis.service;

import org.abigfish.mybatis.entity.UserEntity;

import com.github.pagehelper.PageInfo;

public interface UserService {
	
	public void save(UserEntity u);
	
	PageInfo queryPage(int offset, int limit);

}
