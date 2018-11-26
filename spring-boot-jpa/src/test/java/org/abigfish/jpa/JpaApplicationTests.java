package org.abigfish.jpa;
 

import org.abigfish.jpa.dao.UserInfoDao;
import org.abigfish.jpa.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
 
import java.util.List;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {
 
	@Autowired
	private UserInfoDao userInfoDao;
	@Test
	public void contextLoads() {
	}
 
	@Test
	public void insertTest(){
		UserInfo userInfo = new UserInfo();
		userInfo.setName("张三");
		userInfo.setSex("1");
		userInfoDao.save(userInfo);
	}
 
	@Test
	public void updateTest(){
		UserInfo userInfo = new UserInfo();
		userInfo.setName("李四");
		userInfo.setSex("0");
		userInfo.setId(1);
		userInfoDao.save(userInfo);
	}
 
	@Test
	public void deleteTest(){
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfoDao.delete(userInfo);
	}
 
	@Test
	public void queryTest(){
//		UserInfo userInfo = new UserInfo();
//		userInfo.setName("张三");
//		userInfo.setSex("1");
//		List<UserInfo>  list= userInfoDao.findByName("%李%");
////		List<UserInfo>  list1= userInfoDao.findByNameAndSex(userInfo.getName(),userInfo.getSex());
//
//		for(UserInfo user:list){
//			System.out.println("=="+user.toString());
//		}
 
//		for(UserInfo user:list1){
//			System.out.println("~~~~~"+user.toString());
//		}
	}
 
 
	@Test
	public void queryTest2(){
//
//		List<UserInfo>  list= userInfoDao.findByNameLike("%李%");
//
//		for(UserInfo user:list){
//			System.out.println("=="+user.toString());
//		}
	}
 
	@SuppressWarnings("deprecation")
	@Test
	public void queryTest3(){
		Pageable pageable = new PageRequest(0,2);
		Page<UserInfo> page = userInfoDao.findByNameLike("%张%",pageable);
 
		for(UserInfo user:page.getContent()){
			System.out.println("=="+user.toString());
		}
	}
 
}
