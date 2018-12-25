package org.abigfish.shiro.jwt.service;

import org.abigfish.shiro.jwt.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

public interface UserService extends CrudRepository<User,Long> {
    /**通过username查找用户信息;*/
    public User findByUsername(String username);

}
