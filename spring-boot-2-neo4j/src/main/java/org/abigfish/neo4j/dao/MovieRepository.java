package org.abigfish.neo4j.dao;
import org.abigfish.neo4j.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.support.CypherdslConditionExecutor;

import java.util.List;

public interface MovieRepository
        extends Neo4jRepository<Movie, Long>, CypherdslConditionExecutor<Movie> {

    List<Movie> findByName(String name);

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Actor) WHERE a.name = $name RETURN m.name ")
    List<String> findMovieNamesByActorName(String name);

}