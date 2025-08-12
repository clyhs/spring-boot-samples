package org.abigfish.neo4j.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@NoArgsConstructor
@Data
@Node
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int releasedAt;

    @Relationship(type = "ACTED_IN", direction = Relationship.Direction.INCOMING)
    private List<Role> rolesAndActors;

    public Movie(String name, int releasedAt, List<Role> rolesAndActors) {
        this.name = name;
        this.releasedAt = releasedAt;
        this.rolesAndActors = rolesAndActors;
    }
}