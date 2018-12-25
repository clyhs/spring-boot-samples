package org.abigfish.shiro.jwt.web;

import org.abigfish.shiro.jwt.exception.UnauthorizedException;
import org.abigfish.shiro.jwt.model.ResponseBean;
import org.abigfish.shiro.jwt.model.User;
import org.abigfish.shiro.jwt.service.UserService;
import org.abigfish.shiro.jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("IndexController相关api")
public class IndexController {

	@Autowired
	private UserService userService;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	/*
	 * 
	 * @PostMapping("/login") public ResponseBean
	 * login(@RequestParam("username") String username,
	 * 
	 * @RequestParam("password") String password) { User userBean =
	 * userService.findByUserName(username); if
	 * (userBean.getPassword().equals(password)) { return new ResponseBean(200,
	 * "Login success", JwtUtil.sign(username, password)); } else { throw new
	 * UnauthorizedException(); } }
	 */

	@ApiOperation(value = "根据用户名和密码登录",notes = "会员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String",paramType = "path",example = "admin"),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String",paramType = "path",example = "123456")
    })
    @ApiResponses({
    	@ApiResponse(code=401,message = "权限出错"),
        @ApiResponse(code=404,message = "路径找不到")  
    })
	@GetMapping("/login/{username}/{password}")
	public ResponseBean login(@PathVariable("username") String username, @PathVariable("password") String password) {
		User userBean = userService.findByUserName(username);
		if (userBean.getPassword().equals(password)) {
			return new ResponseBean(200, "Login success", JwtUtil.sign(username, password));
		} else {
			throw new UnauthorizedException();
		}
	}

	@RequestMapping(path = "/401")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseBean unauthorized() {
		return new ResponseBean(401, "Unauthorized", null);
	}
}
