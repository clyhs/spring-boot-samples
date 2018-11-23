package org.abigfish.mybatis.mapper;

import java.util.List;

import org.abigfish.mybatis.entity.UserEntity;

public interface UserMapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);
	
	

}