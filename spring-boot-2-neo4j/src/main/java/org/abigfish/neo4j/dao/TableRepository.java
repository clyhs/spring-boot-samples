package org.abigfish.neo4j.dao;

import org.abigfish.neo4j.entity.Table;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends Neo4jRepository<Table, Long> {
}
