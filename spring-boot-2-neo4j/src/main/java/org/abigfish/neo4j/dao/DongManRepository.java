package org.abigfish.neo4j.dao;
import org.abigfish.neo4j.entity.DongMan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:
 * @author: zhaopeng
 * @description: TODO(......)
 * @date: 2021年12月27日 9:09
 */
@Repository
public interface DongManRepository extends Neo4jRepository<DongMan,Long> {

    //一对一手动指定关系
    @Query("match (n:dongMan {name:{0}}),(m:dongMan {name:{2}})"+
    "create (n)-[:动漫人物关系{relation:{1}}]->(m)")
    void createRelation(String from,String relation, String to);

    //根据关系数据进行当前用户的所有关系生成
    @Query("match (n:dongMan {name:{0}}),(m:dmRelation),(s:dongMan) where m.from={0} and s.name=m.to create(n)-[:动漫人物关系 {relation:m.relation}]->(s)")
    void createRelationByName(String fromName);

    //根据关系数据进行当前用户的所有关系生成
    @Query("CALL db.relationshipTypes()")
    List<String> getAllRealationTypes();

    //修改
    @Query("MATCH (n) WHERE id(n) = :#{#dongMan.id} SET n.name = :#{#dongMan.name},n.age = :#{#dongMan.age},n.sex = :#{#dongMan.sex} RETURN n")
    DongMan updateById(@Param("dongMan") DongMan dongMan);

    @Query("match (n:dongMan {name:{name}})-[r:`动漫人物关系`]->(m:dongMan) where r.relation={relation} return m")
    List<DongMan> getRelationsByName(@Param("name")String name,@Param("relation")String relation);

    @Query("MATCH (n:dongMan {name:'冯宝宝'}) RETURN n")
    DongMan getTest();
}

