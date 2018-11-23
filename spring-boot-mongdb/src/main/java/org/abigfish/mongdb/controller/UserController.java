package org.abigfish.mongdb.controller;

import org.abigfish.mongdb.dao.UserDao;
import org.abigfish.mongdb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
    private UserDao m_userDao;
 
    @GetMapping(value="/test1")
    public void saveTest() throws Exception {
        User u=new User();
        u.setId(1);
        u.setUsername("123");
        u.setPassword("123");
        m_userDao.saveUser(u);
    }
    
    @GetMapping(value="/test2")
    public User findTestByName(){
    	User u= m_userDao.findUserByUserName("123");
        System.out.println("u is "+u);
        return u;
    }


}
