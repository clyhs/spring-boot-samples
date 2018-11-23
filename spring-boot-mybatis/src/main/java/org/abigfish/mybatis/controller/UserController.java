package org.abigfish.mybatis.controller;

import java.util.List;

import org.abigfish.mybatis.entity.UserEntity;
import org.abigfish.mybatis.entity.UserSexEnum;
import org.abigfish.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users=userMapper.getAll();
		return users;
	}
	
    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
    	UserEntity user=userMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save() {
    	UserEntity u = new UserEntity();
    	u.setId(1l);
    	u.setNickName("111");
    	u.setPassWord("111");
    	u.setUserName("111");
    	u.setUserSex(UserSexEnum.MAN);
    	userMapper.insert(u);
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }
    
    
    @ResponseBody
    @GetMapping("/all")
    public PageInfo findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
    	
    	PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userDomains = userMapper.getAll();
        PageInfo result = new PageInfo(userDomains);
        return result;

    }

    
    
    
}