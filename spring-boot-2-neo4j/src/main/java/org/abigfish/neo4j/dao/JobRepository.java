package org.abigfish.neo4j.dao;

import java.util.List;

import org.abigfish.neo4j.entity.Job;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends Neo4jRepository<Job, Long> {
    /**
     * 根据名称查找
     */
    Job findByName(String name);

    /**
     * 节点name是否包含指定的值
     */
    boolean existsByNameContaining(String name);

    /**
     * 强制删除节点
     */
    Long deleteByName(String name);
    
    @Query("match(a:Job{name:$name}) return a")
    Job findByName2(@Param("name") String name);

    @Query("match(a:Job{name: $0) return a")
    List<Job> findByName3(@Param("name") String name);

}

