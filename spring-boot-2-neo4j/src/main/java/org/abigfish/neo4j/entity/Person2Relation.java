package org.abigfish.neo4j.entity;

import java.io.Serializable;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RelationshipProperties
public class Person2Relation implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Relationship("师")
    private Person2 parent;
    @Relationship("徒")
    private Person2 child;
    @Property
    private String relation;
    
    public Person2Relation(Person2 parent, Person2 child, String relation) {
        this.parent = parent;
        this.child = child;
        this.relation = relation;
    }
}