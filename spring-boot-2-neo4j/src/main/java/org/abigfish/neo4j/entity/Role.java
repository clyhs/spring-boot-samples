package org.abigfish.neo4j.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@NoArgsConstructor
@Data
@RelationshipProperties
public class Role {

    @Id
    private Long id;
    private String name;

    @TargetNode
    private Actor actor;

    public Role(String name, Actor actor) {
        this.name = name;
        this.actor = actor;
    }
}