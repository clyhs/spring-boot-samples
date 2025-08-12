package org.abigfish.neo4j.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.abigfish.neo4j.dao.ActorRepository;
import org.abigfish.neo4j.dao.MovieRepository;
import org.abigfish.neo4j.entity.Actor;
import org.abigfish.neo4j.entity.Movie;
import org.neo4j.cypherdsl.core.Condition;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.Node;
import org.neo4j.cypherdsl.core.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActorMovieServiceImpl implements ActorMovieService {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Neo4jTemplate neo4jTemplate;

    @Override
    public List<Movie> findMoviesByActorName(String name) {
        String cypher = "MATCH (a:Actor)-[:ACTED_IN]->(m:Movie) WHERE a.name = $name RETURN m";

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        return neo4jTemplate.findAll(cypher, params, Movie.class);
    }

    @Override
    public List<Actor> findActorsByNamePrefix(String prefix) {
        Node actor = Cypher.node("Actor").named("actor");
        Property name = actor.property("name");
        Property yearOfBirth = actor.property("yearOfBirth");

        Condition condition = name.startsWith(Cypher.anonParameter(prefix));

        return actorRepository.findAll(condition, yearOfBirth.descending()).stream().toList();
    }

    @Override
    public List<Actor> findActorsByNamePrefixWithQueryByExample(String prefix) {
        Actor exampleActor = new Actor();
        exampleActor.setName(prefix);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("yearOfBirth")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

        return actorRepository.findAll(Example.of(exampleActor, matcher), Sort.by("yearOfBirth").descending());
    }

    @Transactional
    @Override
    public void updateMovie(Movie movie) {
        movieRepository.findById(movie.getId())
                .map(m -> {
                    m.setName(movie.getName());
                    if (movie.getReleasedAt() > 0) {
                        m.setReleasedAt(movie.getReleasedAt());
                    }
                    return movieRepository.save(m);
                })
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

}