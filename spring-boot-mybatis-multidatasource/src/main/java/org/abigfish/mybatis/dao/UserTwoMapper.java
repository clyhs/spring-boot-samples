package org.abigfish.mybatis.dao;

import java.util.List;

import org.abigfish.mybatis.aop.TargetDataSource;
import org.abigfish.mybatis.constants.DataSourceKey;
import org.abigfish.mybatis.entity.UserEntity;

@TargetDataSource(DataSourceKey.TWO)
public interface UserTwoMapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);
	
	

}