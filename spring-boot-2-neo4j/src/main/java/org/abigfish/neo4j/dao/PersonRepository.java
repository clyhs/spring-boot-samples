package org.abigfish.neo4j.dao;
import org.abigfish.neo4j.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
 
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    // 自定义查询方法示例：根据姓名查询
    Person findByName(String name);
}