package org.abigfish.neo4j.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@NoArgsConstructor
@Data
@Node
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String nationality;
    private int yearOfBirth;

    public Actor(String name, String nationality, int yearOfBirth) {
        this.name = name;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
    }
}