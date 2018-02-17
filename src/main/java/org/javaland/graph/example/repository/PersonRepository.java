package org.javaland.graph.example.repository;

import org.javaland.graph.example.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    List<Person> findByFamilyName(String familyName);

}
