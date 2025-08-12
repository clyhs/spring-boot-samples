package org.abigfish.neo4j.dao;

import java.util.List;

import org.abigfish.neo4j.entity.Person2;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface Person2Repository extends Neo4jRepository<Person2,Long> {
	 
    /**
     * 查询某个节点的所有子节点
     * @param pId
     * @return
     */
    @Query("Match (p:person) -[*]->(s:person) where id(p)={0} return s")
    List<Person2> findChildList(Long pId);
 
    @Query("Match (p:person {name:{0}}) -[*]->(s:person) return s")
    List<Person2> findChildList(String name);
 
    /**
     * 查询当前节点的父节点
     * @param name
     * @return
     */
    @Query("Match (p:person) -[*]->(s:person {name:{0}}) return p")
    List<Person2> findParentList(String name);
 
    List<Person2> findByName(String name);
 
}
