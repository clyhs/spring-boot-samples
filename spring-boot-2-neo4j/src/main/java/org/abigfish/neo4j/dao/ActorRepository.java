package org.abigfish.neo4j.dao;
import org.abigfish.neo4j.entity.Actor;
import org.neo4j.driver.types.Path;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.support.CypherdslConditionExecutor;

import java.util.List;

public interface ActorRepository
        extends Neo4jRepository<Actor, Long>, CypherdslConditionExecutor<Actor> {

    @Query("MATCH (a:Actor)-[:ACTED_IN]->(m:Movie) WHERE m.name = $name RETURN a.name")
    List<String> findActorNamesByMovieName(String name);

    @Query("MATCH (a:Actor)-[:ACTED_IN]->(m:Movie) WHERE m.name = $name RETURN COALESCE(AVG(datetime().year - a.yearOfBirth), 0)")
    double findAverageAgeOfActorsByMovieName(String name);

    @Query("MATCH (a1:Actor {name: $actor1}) MATCH (a2:Actor {name: $actor2}) MATCH p = shortestPath((a1)-[*..10]-(a2)) RETURN p ")
    List<Path> findShortestPathBetweenActors(String actor1, String actor2);

    @Query("MATCH (a:Actor) WHERE a.name = $name SET a.yearOfBirth = $yearOfBirth ")
    void updateYearOfBirthByName(String name, int yearOfBirth);

}