package org.abigfish.neo4j.entity;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.Data;

@Data
@Node
public class Table {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    @Relationship("dep")
    private List<Job> jobs;
}

