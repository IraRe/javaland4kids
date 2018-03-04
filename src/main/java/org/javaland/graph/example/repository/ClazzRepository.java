package org.javaland.graph.example.repository;

import org.javaland.graph.example.entity.Clazz;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ClazzRepository extends Neo4jRepository<Clazz, Long> {

    Clazz findByName(String name);
}
