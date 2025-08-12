package org.abigfish.neo4j.service;
import org.abigfish.neo4j.entity.DongMan;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @className:
 * @author: zhaopeng
 * @description: TODO(......)
 * @date: 2021年12月27日 9:46
 */
public interface DongManService {

   /**
    * TODO(添加对象)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param dongMan
    * @return work.order.system.entity.dongman.DongMan
    */
   DongMan addDongMan(DongMan dongMan);

   /**
    * TODO(根据id查询对象)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param id
    * @return work.order.system.entity.dongman.DongMan
    */
   DongMan getInfoById(long id);

   /**
    * TODO(根据id删除对象)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param id
    * @return void
    */
   void delById(long id);

   /**
    * TODO(指定已存在的2个对象间的关系)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param from
    * @param relation
    * @param to
    * @return void
    */
   void createRelation(String from,String relation, String to);

   /**
    * TODO(生成指定节点的关系，基于关系数据生成)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param fromName
    * @return void
    */
   void createRelationByName(String fromName);

   /**
    * TODO(分页查询动漫人物信息[0为第一页])
    * @author zhaoPeng
    * @date 2021/12/28
    * @param current
    * @param pageSize
    * @param Name
    * @return org.springframework.data.domain.Page<work.order.system.entity.dongman.DongMan>
    */
   Page<DongMan> getListByPage(int current, int pageSize, String Name);

   /**
    * TODO(获取数据库中所有的关系类型)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param
    * @return java.util.List<java.lang.String>
    */
   List<String> getAllRealationTypes();

   /**
    * TODO(判定是否存在该用户)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param id
    * @return java.lang.Boolean
    */
   Boolean existById(long id);

   /**
    * TODO(修改节点信息)
    * @author zhaoPeng
    * @date 2021/12/28
    * @param dongMan
    * @return work.order.system.entity.dongman.DongMan
    */
   DongMan updateById(DongMan dongMan);

   //查询指定用户的所有关系
   List<DongMan> getRelationsByName(String name,String relation);

}

