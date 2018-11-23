package org.abigfish.mybatis.controller;

import org.abigfish.mybatis.entity.UserEntity;
import org.abigfish.mybatis.entity.UserSexEnum;
import org.abigfish.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
    @GetMapping(value = "/page/{offset}/{limit}")
    public PageInfo queryPage(@PathVariable int offset, @PathVariable int limit) {
        return userService.queryPage(offset,limit);
    }

	@ResponseBody
	@GetMapping(value = "/add")
	public String add(){
		UserEntity u = new UserEntity();
		u.setId(2l);
		u.setNickName("222");
		u.setPassWord("222");
		u.setUserName("222");
		u.setUserSex(UserSexEnum.WOMAN);
		userService.save(u);
		return "success";
	}

}
