package org.abigfish.neo4j.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import lombok.Data;

@Data
@RelationshipProperties
public class TableRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private Table table;
}

