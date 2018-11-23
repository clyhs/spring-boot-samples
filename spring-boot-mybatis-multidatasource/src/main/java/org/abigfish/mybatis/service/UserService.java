package org.abigfish.mybatis.service;

import com.github.pagehelper.PageInfo;

public interface UserService {
	
	
	PageInfo queryPage(int offset, int limit);

}
