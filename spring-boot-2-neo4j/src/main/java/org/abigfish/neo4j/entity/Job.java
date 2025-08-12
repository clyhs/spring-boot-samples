package org.abigfish.neo4j.entity;
@Data
@Node("Job")
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Property
    private String type;

    @Relationship("dep")
    private List<TableRelationship> tables;

    @Relationship("dep")
    private List<JobRelationship> jobs;
}

