package org.abigfish.jpa.dao;
 

import org.abigfish.jpa.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    // 名字优于配置  @Quert vlaue 写jpql  ，且@Query优先级大于名字
//    @Query(value = "select u from UserInfo u where u.name like ?1")
//     List<UserInfo> findByName(String name);
     List<UserInfo> findByNameAndSex(String name,String sex);
     // 模糊查询
     // List<UserInfo> findByNameLike(String name);
     Page<UserInfo> findByNameLike(String name, Pageable pageable);
}
