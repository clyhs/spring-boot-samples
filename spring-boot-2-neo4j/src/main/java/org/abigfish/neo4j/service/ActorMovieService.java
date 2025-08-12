package org.abigfish.neo4j.service;
import java.util.List;

import org.abigfish.neo4j.entity.Actor;
import org.abigfish.neo4j.entity.Movie;

public interface ActorMovieService {

    List<Movie> findMoviesByActorName(String name);

    List<Actor> findActorsByNamePrefix(String prefix);

    List<Actor> findActorsByNamePrefixWithQueryByExample(String prefix);

    void updateMovie(Movie movie);

}