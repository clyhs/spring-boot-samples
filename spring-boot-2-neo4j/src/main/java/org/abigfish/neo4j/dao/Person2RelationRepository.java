package org.abigfish.neo4j.dao;

import org.abigfish.neo4j.entity.Person2Relation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface Person2RelationRepository  extends Neo4jRepository<Person2Relation,Long> {
 
}