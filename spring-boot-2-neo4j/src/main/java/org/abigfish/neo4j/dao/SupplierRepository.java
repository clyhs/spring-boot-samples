package org.abigfish.neo4j.dao;

import java.util.List;

import org.abigfish.neo4j.entity.Supplier;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface SupplierRepository extends Neo4jRepository<Supplier, Long> {
    
    // 1. 自动生成查询：根据名称查找供应商
    Supplier findByName(String name);
    
    // 2. 自定义Cypher查询：查找提供某产品的供应商
    @Query("MATCH (s:Supplier)-[:PROVIDES]->(p:Product) WHERE p.name = $productName RETURN s")
    List<Supplier> findSuppliersByProductName(String productName);
}