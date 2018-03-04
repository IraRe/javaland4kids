package org.javaland.graph.example.repository;

import org.javaland.graph.example.entity.Pupil;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PupilRepository extends Neo4jRepository<Pupil, Long> {

    List<Pupil> findByFamilyName(String familyName);
}
