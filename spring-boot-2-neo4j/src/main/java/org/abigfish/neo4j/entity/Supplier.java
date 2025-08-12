package org.abigfish.neo4j.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Data;

@Data
@Node("Supplier")  // 对应Neo4j中的节点标签
public class Supplier {
    @Id
    @GeneratedValue  // 自动生成ID
    private Long id;
    
    @Property("name")  // 映射到节点的属性
    private String name;
    
    @Relationship(type = "PROVIDES", direction = Relationship.Direction.OUTGOING)
    private List<Product> products = new ArrayList<>();
    
    // Getters & Setters
}