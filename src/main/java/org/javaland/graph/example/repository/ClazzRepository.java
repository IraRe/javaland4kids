package org.javaland.graph.example.repository;

import org.javaland.graph.example.entity.Clazz;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClazzRepository extends Neo4jRepository<Clazz, Long> {

    Clazz findByName(String name);

    @Query("MATCH (p:Pupil)-[:TAKES]->(c:Class) where id(p) = {attendeeId} RETURN c")
    List<Clazz> findByAttendee(@Param("attendeeId") Long attendeeId);
}
