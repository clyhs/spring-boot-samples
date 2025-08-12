package org.abigfish.neo4j.entity;

import java.time.LocalDate;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Data;

@Data
@RelationshipProperties
public class SuppliesRelationship {
    @Id
    @GeneratedValue
    private Long id;
    
    @TargetNode  // 定义关系的目标节点
    private Product product;
    
    @Property("since")
    private LocalDate sinceDate;
    
    // Getters & Setters
}